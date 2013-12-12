package com.emag.act.loggingmail

import com.vivame.util.TimeUtil
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowCallbackHandler
import org.codehaus.groovy.grails.commons.ConfigurationHolder

/**
 * 从数据查询后台登录日志
 * User: linguangfa
 * Date: 2012-2-18
 * Time: 15:45:53
 */
class JdbcLoggingAttachment implements LoggingAttachment {
  def List timePeriod
  def dataSource
  def grailsApplication

  def List<File> getAttachments() {
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
}
