package com.emag.act.loggingmail

/**
 * 所有登录管理后台的日志，都需要已邮件形式发送给网站的管理员
 * 本类定义网站管理员的信息（可以是多个）
 * User: linguangfa
 * Date: 2012-2-15
 * Time: 11:07:06
 */
class LoggingMailConf {
  String from  // 发件人
  List<String> to // 收件人 
  String subject // 主题
  String text // 邮件正文
  LoggingAttachment loggingAttachment //附件
}
