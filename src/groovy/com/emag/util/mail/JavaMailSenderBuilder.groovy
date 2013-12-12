package com.emag.util.mail

import org.springframework.mail.MailSender
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl

/**
 * Created by IntelliJ IDEA.
 * User: linguangfa
 * Date: 2012-2-24
 * Time: 11:17:12
 */
class JavaMailSenderBuilder implements MailSenderBuilder {
  private String host
  private Integer port
  private String username
  private String password
  private String localhost
  private JavaMailSenderImpl mailSender

  public JavaMailSenderBuilder(String host, Integer port, String username, String password, String localhost) {
    this.host = host
    this.port = port
    this.username = username
    this.password = password
    this.localhost = localhost
    mailSender = new JavaMailSenderImpl()
  }

  def void buildProperties() {
    Properties props = new Properties()
    props.put('mail.smtp.localhost', localhost)
    props.put('mail.smtp.auth', 'true')
    if (this.port == 465) {
      props.put('mail.smtp.starttls.enable', 'true')
      props.put('mail.smtp.debug', 'true')
      props.put('mail.smtp.socketFactory.class', 'javax.net.ssl.SSLSocketFactory')
      props.put('mail.smtp.socketFactory.fallback', 'false')
    }

    mailSender.setJavaMailProperties(props)
  }

  def MailSender buildMailSender() {
    mailSender.setHost(this.host)
    mailSender.setPort(this.port)
    mailSender.setUsername(this.username)
    mailSender.setPassword(this.password)

    return mailSender
  }
}
