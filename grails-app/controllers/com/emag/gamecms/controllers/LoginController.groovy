package com.emag.gamecms.controllers

import com.emag.gamecms.domain.system.MagSysUser2
import com.emag.util.SmsUtil
import org.codehaus.groovy.grails.plugins.springsecurity.RedirectUtils
import org.springframework.security.AuthenticationTrustResolver
import org.springframework.security.AuthenticationTrustResolverImpl
import org.springframework.security.DisabledException
import org.springframework.security.context.SecurityContextHolder
import org.springframework.security.ui.AbstractProcessingFilter
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter
import com.emag.util.MobileSegmentUtil
import org.codehaus.groovy.grails.plugins.codecs.MD5Codec

/**
 * Login Controller (Example).
 */
class LoginController {

  def loggingMailService

  /**
   * Dependency injection for the authentication service.
   */
  def authenticateService

  /**
   * Dependency injection for OpenIDConsumer.
   */
  def openIDConsumer

  /**
   * Dependency injection for OpenIDAuthenticationProcessingFilter.
   */
  def openIDAuthenticationProcessingFilter

  private final AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl()

  def index = {
    if (isLoggedIn()) {
      MagSysUser2 user = (MagSysUser2) authenticateService.userDomain()
      session.loginUser = user
      // System.out.println "user=" + user
      redirect uri: '/'
    }
    else {
      redirect action: auth, params: params
    }
  }

  /**
   * Show the login page.
   */
  def auth = {
    nocache(response)
    if (isLoggedIn()) {
      redirect uri: '/'
    }

    if (authenticateService.securityConfig.security.useOpenId) {
      render view: 'openIdAuth'
    }
    else {
      render view: 'auth'
    }
  }

  /**
   * Form submit action to start an OpenID authentication.
   */
  def openIdAuthenticate = {
    String openID = params['j_username']

    try {
      String returnToURL = RedirectUtils.buildRedirectUrl(
              request, response, openIDAuthenticationProcessingFilter.filterProcessesUrl)
      String redirectUrl = openIDConsumer.beginConsumption(request, openID, returnToURL)
      redirect url: redirectUrl
    }
    catch (org.springframework.security.ui.openid.OpenIDConsumerException e) {
      log.error "Consumer error: ${e.message}", e
      redirect url: openIDAuthenticationProcessingFilter.authenticationFailureUrl
    }
  }

  // Login page (function|json) for Ajax access.
  def authAjax = {
    nocache(response)
    //this is example:
    render """
		<script type='text/javascript'>
		(function() {
			loginForm();
		})();
		</script>
		"""
  }

  /**
   * The Ajax success redirect url.
   */
  def ajaxSuccess = {
    nocache(response)
    render '{success: true}'
  }

  /**
   * Show denied page.
   */
  def denied = {
    if (isLoggedIn()) {
      // have cookie but the page is guarded with IS_AUTHENTICATED_FULLY
      redirect action: full, params: params
    }
  }

  /**
   * Login page for users with a remember-me cookie but accessing a IS_AUTHENTICATED_FULLY page.
   */
  def full = {
    render view: 'auth', params: params,
            model: [hasCookie: authenticationTrustResolver.isRememberMe(SecurityContextHolder.context?.authentication)]
  }

  // Denial page (data|view|json) for Ajax access.
  def deniedAjax = {
    //this is example:
    render "{error: 'access denied'}"
  }

  /**
   * login failed
   */
  def authfail = {

    def username = session[AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY]
    def msg = ''
    def exception = session[AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY]
    if (exception) {
      if (exception instanceof DisabledException) {
        msg = "[$username] is disabled."
      }
      else {
        msg = "[$username] wrong username/password."
      }
    }

    if (isAjax()) {
      render "{error: '${msg}'}"
    }
    else {
      flash.message = msg
      redirect action: auth, params: params
    }
  }

  /**
   * check is need sms
   * 返回值主要包括两部分，使用 _ 进行分割
   * _ 前的数字记录用户名和密码校验结果，验证成功表示用户用户名和密码输入正确
   * _ 后的数字记录短信验证码结果，验证成功表示需要短信验证码
   * 数字 0 统一表示验证失败，数字 1 统一表示验证成功，数字 2 统一表示其他错误（比如用户号码不合法）
   */
  def checkSmsYn = {
    def list = ['0', '0']

    def user = MagSysUser2.findWhere(username: params.username, passwd: MD5Codec.encode(params.password), enabled: true)
    if (user) {
      // 先校验用户名、密码和状态是否正确
      list[0] = '1'
      log.info "用户[username:${user.username}, zh_name:${user.userRealName}, msisdn:${user.getMobile()}]${user.getCheckSmsYn() ? '' : '不'}需要短信验证";

      if (user.getCheckSmsYn()) {
        // 用户需要短信验证码
        list[1] = '1'

        // 进一步判断用户号码是否正确
        Integer alreadySmsCnt = session.getAttribute('alreadySmsCnt') ?: 0
        if (MobileSegmentUtil.isMobile(user.getMobile()) && alreadySmsCnt < 5) {
          alreadySmsCnt++
          def smsRandom = getRandomNumber()
          session.setMaxInactiveInterval(5 * 60)
          session.setAttribute('smsRandom', smsRandom)
          session.setAttribute('msisdn', user.getMobile())
          session.setAttribute('alreadySmsCnt', alreadySmsCnt)
          SmsUtil.sendSms8899("${message(code: 'login.sms.check', args: [smsRandom])}", user.getMobile(), '')
          log.info "系统已给用户[username:${user.username}]下发短信验证码：${smsRandom}"
        } else {
          list[1] = '2'
        }
      } else {
        // 用户不需要短信验证码
        // 用户登录成功以后，将session有效时间设置成 30 分钟
        session.setMaxInactiveInterval(30 * 60)
      }
    }

    render list.join('_')
  }

  /**
   * 重新发送短信验证码
   * 发送成功输出 1，发送失败输出 0
   */
  def sendSmsAgain = {
    // 获取已发送短信数量
    Integer alreadySmsCnt = session.getAttribute('alreadySmsCnt') ?: 0
    def msisdn = session.getAttribute('msisdn')
    if (msisdn && alreadySmsCnt < 5) {
      alreadySmsCnt++
      def smsRandom = getRandomNumber()
      session.setAttribute('smsRandom', smsRandom)
      session.setAttribute('alreadySmsCnt', alreadySmsCnt)
      SmsUtil.sendSms8899("${message(code: 'login.sms.check', args: [smsRandom])}", msisdn, '')
      log.info "系统已给号码[msisdn:${msisdn}]下发短信验证码：${smsRandom}"

      render '1'
    }

    render '0'
  }

  /**
   * 校验用户输入的短信验证码是否正确
   * 正确输出 1，错误输出 0
   */
  def checkSmsRandom = {
    def smsRandom = session.getAttribute("smsRandom")
    def subRandom = params.subRandom
    def valid = (smsRandom == subRandom)
    if (valid) {
      session.smscode = smsRandom

      // 用户登录成功以后，将session有效时间设置成 30 分钟
      session.setMaxInactiveInterval(30 * 60)
    }
    log.info "用户[username:${params.username}]输入的验证码为：${subRandom}，session中保存的验证码为：${smsRandom}，验证通过：${valid}"

    render valid ? '1' : '0'
  }

  /**
   * get random Number
   */
  public String getRandomNumber() {
    int random = Math.random() * 1000000.toInteger();
    String str = "";
    if (random < 10) {
      str = "00000" + random;
    } else if (random < 100) {
      str = "0000" + random;
    } else if (random < 1000) {
      str = "000" + random;
    } else if (random < 10000) {
      str = "00" + random;
    } else if (random < 100000) {
      str = "0" + random;
    } else {
      str = "" + random;
    }
    return str;
  }

  /**
   * Check if logged in.
   */
  private boolean isLoggedIn() {
    return authenticateService.isLoggedIn()
  }

  private boolean isAjax() {
    return authenticateService.isAjax(request)
  }

  /** cache controls                */
  private void nocache(response) {
    response.setHeader('Cache-Control', 'no-cache') // HTTP 1.1
    response.addDateHeader('Expires', 0)
    response.setDateHeader('max-age', 0)
    response.setIntHeader('Expires', -1) //prevents caching at the proxy server
    response.addHeader('cache-Control', 'private') //IE5.x only
  }

}
