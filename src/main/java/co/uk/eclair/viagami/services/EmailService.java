package co.uk.eclair.viagami.services;

import co.uk.eclair.viagami.utilities.Mail;

import javax.mail.MessagingException;

/**
 * Created by ${Eclair} on 9/4/2018.
 */
public interface EmailService {
     void sendMessage(Mail message);
     void sendMessageWithAttachment(Mail mesage) throws MessagingException;
}
