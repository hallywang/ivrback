package com.emag.util.mail

import org.springframework.mail.MailSender

/**
 * Created by IntelliJ IDEA.
 * User: linguangfa
 * Date: 2012-2-24
 * Time: 11:28:55
 */
class MailSenderDirector {

  public MailSender constructMailSender(MailSenderBuilder builder) {
       builder.buildProperties()

       return builder.buildMailSender()
  }

}
