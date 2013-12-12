package com.emag.gamecms.service

import com.aspire.gentox.phonesect.model.GetPhone
import com.emag.gamecms.domain.actionlog.GameCmsActionLog
import com.emag.gamecms.domain.system.MagSysRequestmap
import com.emag.gamecms.domain.system.MagSysUser2
import com.emag.util.MobileUtil
import com.vivame.util.TimeUtil
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter

class LogService {

  boolean transactional = false
  def uaService
  def authenticateService

  /**
   *  记录前台访问日志
   */
  def logAction(request) {
    //获取用户手机号码
    def mobile = MobileUtil.getMobile(request)

    StringBuilder buff = new StringBuilder()
    buff.append("LOGID:${URLEncoder.encode("-", "utf-8")}")
    buff.append(" LOG_CATEGORY_ID:${URLEncoder.encode("-", "utf-8")}")
    buff.append(" OPTIME:${URLEncoder.encode(TimeUtil.getTime() + "", "utf-8")}")
    buff.append(" MSISDN:${URLEncoder.encode(formatStr(mobile) + "", "utf-8")}")
    buff.append(" REQUEST_METHOD:${URLEncoder.encode(formatStr(request.getMethod()) + "", "utf-8")}")

    buff.append(" REQUEST_URL:${URLEncoder.encode(formatStr(request['javax.servlet.forward.request_uri']) + "", "utf-8")}")
    buff.append(" QUERY_STRING:${URLEncoder.encode(formatStr(request.getQueryString()) + "", "utf-8")}")
    buff.append(" USER_AGENT:${URLEncoder.encode(formatStr(request.getHeader("User-Agent")) + "", "utf-8")}")
    buff.append(" STATUS_CODE:${URLEncoder.encode("200", "utf-8")}")         //错误
    buff.append(" IP_ADDRESS:${URLEncoder.encode(formatStr(request.getRemoteHost()) + "", "utf-8")}")

    buff.append(" REFERER:${URLEncoder.encode(formatStr(request.getHeader("Referer")) + "", "utf-8")}")
    buff.append(" TIME_IN_MILLIS:${URLEncoder.encode("-", "utf-8")}")
    buff.append(" BYTES:${URLEncoder.encode("-", "utf-8")}")
    buff.append(" HTTP_VIA:${URLEncoder.encode(formatStr(request.getHeader("via")) + "", "utf-8")}")
    buff.append(" HTTP_CLIENT_IP:${URLEncoder.encode(formatStr(request.getHeader("HTTP_CLIENT_IP")) + "", "utf-8")}")  // 代理端ip（有可能存在，可伪造）

    buff.append(" HTTP_X_SOURCE_ID:${URLEncoder.encode(formatStr(request.getHeader("x-source-id")) + "", "utf-8")}")
    buff.append(" SERVER_IP_ADDRESS:${URLEncoder.encode(formatStr(request.getLocalAddr()) + "", "utf-8")}")
    buff.append(" TIME_ZONE:${URLEncoder.encode("+0800", "utf-8")}")
    buff.append(" CITY_ID:${URLEncoder.encode(getCityIdByMobile(mobile) + "", "utf-8")}")   //用户城市信息
    buff.append(" PROVINCE_ID:${URLEncoder.encode(getProvinceIdByMobile(mobile) + "", "utf-8")}")  //用户省份信息

    buff.append(" UA_STR:${URLEncoder.encode(formatStr(getUserAgentInfo(request)) + "", "utf-8")}") //手机型号

    log.info(buff.toString())
  }

  /**
   * 将空字符串格式哈
   */
  def formatStr(str) {
    if (!str) {
      str = "-"
    }

    return str
  }

  /**
   * 获取cityId
   */
  def getCityIdByMobile(mobile) {
    def rtnCode = "-"
    if (mobile) {
      rtnCode = GetPhone.getInstance().getCity_id(mobile)
    }

    return rtnCode
  }

  /**
   * 获取provinceId
   */
  def getProvinceIdByMobile(mobile) {
    def rtnCode = "-"
    if (mobile) {
      rtnCode = GetPhone.getInstance().getProv_id(mobile)
    }

    return rtnCode
  }

  /**
   * 获取用户ua信息，先从数据库总获取，如果数据库中没有则在从请求的header中获取
   */
  def getUserAgentInfo(request) {
    def rtnCode = "-"
    if (request) {
      rtnCode = uaService.getUserUa(request)
    }

    return rtnCode
  }

  /**
   * 记录后台操作日志
   */
  def logBackAction(request, session, params) {
    def controllerName = params.controller
    def actionName = params.realAction

    // 后台操作日志主要包括用户的登录登出、菜单操作等
    def controllers = [:]
    MagSysRequestmap.executeQuery("from MagSysRequestmap a where a.father is not null")?.each {
      controllers.put(it.realUrl, it.name)
    }
    controllers.put("login", "登录")
    controllers.put("logout", "退出")
    controllers.remove("gameCmsActionLog") //操作日志的查看，不记录

    if (controllers.containsKey(controllerName) && params.size() > 0) {
      MagSysUser2 user = (MagSysUser2) authenticateService.userDomain()
      def username = session[AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY] ?: params.username

      // 对于获取不到用户名的操作，我觉得记录日志也没有任何意义了
      if (username) {
        def actionlog = new GameCmsActionLog()
        actionlog.controllName = controllers[controllerName]
        actionlog.actionName = actionName
        actionlog.actionTime = new Date()
        actionlog.actionId = params.id ? params.id : 0L
        actionlog.userName = user?.username ? user?.username : username
        actionlog.status = 0
        actionlog.ipAddress = request.getRemoteHost() ? request.getRemoteHost() : "-"
        actionlog.save(flush: true)
      }
    }
  }
}
