package co.uk.eclair.viagami.services;

import co.uk.eclair.viagami.utilities.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * Created by ${Eclair} on 9/4/2018.
 */
@Component
public class EmailServiceImpl implements EmailService{
    private JavaMailSender javaMailSender = new JavaMailSenderImpl();
    final MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
    final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);

    public EmailServiceImpl() throws MessagingException {
    }

    @Override
    public void sendMessage(Mail mail)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        message.setSentDate(new Date());
        javaMailSender.send(message);
    }
    @Override
    public void sendMessageWithAttachment(Mail mail) throws MessagingException {
        mimeMessageHelper.setTo(mail.getTo());
        mimeMessageHelper.setSubject(mail.getSubject());
        mimeMessageHelper.setText(mail.getContent());
        mimeMessageHelper.setSentDate(new Date());

        FileSystemResource fileSystemResource = new FileSystemResource(new File(mail.getAttachementPath()));
        mimeMessageHelper.addAttachment("Attachment", fileSystemResource);
        javaMailSender.send(mimeMailMessage);
    }
}
