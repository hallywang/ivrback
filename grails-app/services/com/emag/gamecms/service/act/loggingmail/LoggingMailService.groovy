package com.emag.gamecms.service.act.loggingmail

import com.emag.gamecms.domain.system.GameCmsMailServer
import com.emag.util.mail.JavaMailSenderBuilder
import com.emag.util.mail.MailSenderDirector
import com.vivame.util.TimeUtil
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.mail.MailSender
import org.springframework.mail.javamail.MimeMessageHelper

import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeUtility

class LoggingMailService {
  boolean transactional = false
  def dataSource
  def grailsApplication
  private static final timePeriod = ['00:00:00~07:59:59', '08:00:00~17:59:59', '18:00:00~23:59:59']

  def sendMail() {
    log.info 'egcms logging logs start to send mails'
    long a = System.currentTimeMillis()

    GameCmsMailServer server = getMailServer()
    log.info "load mail server info[host:${server.serverName}, port:${server.smtpPort}, username:${server.username}, password:${server.password}, localhost:${server.localhost}]"
    MailSenderDirector director = new MailSenderDirector()
    MailSender mailSender = director.constructMailSender(new JavaMailSenderBuilder(server.serverName, server.smtpPort, server.username, server.password, server.localhost))

    if (server.receivers) {
      def attachments = getAttachments()

      def tos = []
      def subject = ''
      def text = ''
      server.receivers.each {receiver ->
        tos << receiver.mailAddress
      }
      MimeMessage msg = mailSender.createMimeMessage()
      MimeMessageHelper helper = new MimeMessageHelper(msg, true, 'utf-8')
      helper.setFrom(server.username)
      helper.setTo(tos as String[])
      helper.setSubject(server.subject)
      helper.setText(server.text)
      attachments.each {file ->
        helper.addAttachment(MimeUtility.encodeWord(file.getName()), file)
      }

      mailSender.send(msg)
      log.info "egcms logging logs stop to send mails, receivers:${tos}, use:${(System.currentTimeMillis() - a) / 1000}s"
    }
  }

  public GameCmsMailServer getMailServer() {
    return GameCmsMailServer.getAll()[0]
  }

  public List<File> getAttachments() {
    def rtnList = []

    String yesterday = TimeUtil.getDate(TimeUtil.getNDateLater(new Date(), 'd', -1), 'yyyy-MM-dd')
    def buff = new StringBuilder()
    buff.append("select action_time, user_name, ip_address")
    buff.append("  from game_cms_action_log")
    buff.append(" where controll_name = '登录'")
    buff.append("   and action_name = 'checkSmsYn'")
    buff.append("   and action_time >= ?")
    buff.append("   and action_time <= ?")

    File path = new File(grailsApplication.config.loggingmail.file.path + yesterday)
    if (!path.exists()) path.mkdirs() //保存路径不存在，则创建目录
    for (int i = 0; i < timePeriod.size(); i++) {
      def period = timePeriod.get(i)
      BufferedWriter bw = null
      File file = null
      try {
        String absoluteFileName = grailsApplication.config.loggingmail.file.path + yesterday + '/' + i + '.txt'
        file = new File(absoluteFileName)
        if (file.exists()) {
          // 文件已经存在，则先删除再写入，防止重复写入登录日志
          file.delete()
        }
        bw = new BufferedWriter(new FileWriter(file))

        def startTime = yesterday + ' ' + period.split('~')[0]
        def stopTime = yesterday + ' ' + period.split('~')[1]

        def rows = new JdbcTemplate(dataSource).queryForList(buff.toString(), [startTime, stopTime] as String[])
        Iterator iter = rows.iterator()
        while (iter.hasNext()) {
          Map actionMap = (Map) iter.next()

          bw.write("${actionMap.get('action_time')}|${actionMap.get('user_name')}|${actionMap.get('ip_address')}")
          bw.newLine()
        }
      }
      finally {
        if (bw != null) {
          bw.close()
        }
      }

      rtnList << file
    }

    return rtnList
  }

  public static void main(String[] args) {
//    String host = 'mail.emagsoftware.cn'
//    int port = 465
//    String username = 'cmsreport@emagsoftware.cn'
//    String password = 'emag123456!@#'
//    String localhost = 'www.emagsoftware.cn'

    String host = 'smtp.139.com'
    int port = 25
    String username = '13675180163@139.com'
    String password = '840301'
    String localhost = 'www.emagsoftware.cn'

    MailSenderDirector director = new MailSenderDirector()
    MailSender mailSender = director.constructMailSender(new JavaMailSenderBuilder(host, port, username, password, localhost))

    MimeMessage msg = mailSender.createMimeMessage()
    MimeMessageHelper helper = new MimeMessageHelper(msg, true, 'utf-8')
    helper.setFrom(username)
    helper.setTo('wanghaili@emagsoftware.cn')
    helper.setSubject('test subject')
    helper.setText('test text')
    helper.addAttachment('d:/ua_2.txt', new File('d:/ua_2.txt'))

    mailSender.send(msg)
  }
}
