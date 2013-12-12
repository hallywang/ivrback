import com.emag.gamecms.domain.system.MagSysUser2
import com.emag.gamecms.domain.system.GameCmsLoggingIp
import java.util.regex.Pattern
import java.util.regex.Matcher

/**
 * acegi已经提供了传统的安全验证功能，本过滤器新增以下两种安全验证：
 * 1、手机验证码验证；
 * 2、ip地址验证。
 *
 * 需要特别注意一点：
 * 登录用户信息是在 loginController 的 index 中放入 session 中的，当用户在session失效或直接访问受保护页面时，
 * 会提醒用户登录，但是登录以后 acegi 会访问用户之前访问的页面地址，从而绕过了 loginController 的 index，
 * 所以会造成session中没有用户的登录信息。
 * 为了避免这一情况发生，获取用户信息时最好不要从session中获取，而是通过 authenticateService.userDomain 来获取
 *
 */
class SecurityFilters {
  def authenticateService
  def filters = {
    allURIs(uri:'/**') {
      before = {
        if (controllerName == 'logout') {
          // 登录直接跳过
          return true
        }

        // 先判断用户ip地址是否合法
        def ipAddress = request.getRemoteHost()
        boolean validIp = false
        def ips = GameCmsLoggingIp.findAllByStatus(1)
        for (def ip: ips) {
          if ((ip.supportRegex == 1 && Pattern.compile(ip.ip).matcher(ipAddress).matches()) || ip.ip == ipAddress) {
            // 使用正则匹配或者访问ip地址直接是白名单ip，则允许用户访问
            validIp = true
            break
          }
        }

        if (!validIp) {
          log.info "visit ip:${ipAddress} is not valid"
          response.sendError(403)
          return false
        }

        // 判断用户是否使用短信验证码来登录系统
        MagSysUser2 user = (MagSysUser2) authenticateService.userDomain()
        if (user && user.checkSmsYn && !session.smscode) {
          /**
           * 用户已登录、且该用户需要短信验证码来验证、但会话中找不到该用户输入的短信验证码信息
           * 则直接跳转登录页面
           */
          log.info "user:${user.username} need sms code to check in, but not checked"
          //response.sendRedirect("${request.contextPath}/logout")
          redirect(controller: 'logout', action: 'index')
          return false
        }

        /**
         * 用户已经登录，但是session中不存在登录用户信息
         * 这种情况的发生一般是因为acegi的登录流程导致：
         * 1、一般情况登录后直接跳转到 loginController 的 login 中，这里会设置用户session
         * 2、当用户直接访问受保护页面时 ，页面将先让用户登录，登录后直接跳转到用户之前的访问的页面，也就没有访问 loginController 的 login 中，所以会导致session中没有用户信息
         *
         * 这里对第2种情况做修正，使得用户只要登录后session中就存有用户登录信息
         */
        if (user && !session.loginUser) {
          session.loginUser = user
        }
      }
      after = {

      }
      afterView = {

      }
    }
  }

}
