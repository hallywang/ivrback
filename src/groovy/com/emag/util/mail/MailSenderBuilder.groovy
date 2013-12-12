package com.emag.util.mail

import org.springframework.mail.MailSender

/**
 * Created by IntelliJ IDEA.
 * User: linguangfa
 * Date: 2012-2-24
 * Time: 11:09:19
 */
public interface MailSenderBuilder {
  public void buildProperties()
  public MailSender buildMailSender()
}