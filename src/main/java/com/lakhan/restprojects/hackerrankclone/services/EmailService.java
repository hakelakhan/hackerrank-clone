package com.lakhan.restprojects.hackerrankclone.services;


import com.lakhan.restprojects.hackerrankclone.models.NotificationEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("hakelakhan@gmail.com");
            mimeMessageHelper.setTo(notificationEmail.getRecepient());
            mimeMessageHelper.setSubject(notificationEmail.getSubject());
            mimeMessageHelper.setText(notificationEmail.getBody());
        };

        try {
            mailSender.send(mimeMessagePreparator);
            log.info("Activation email sent");
        }
        catch (MailException ex) {
            log.error("Error while sending email to recipient {} ", notificationEmail.getRecepient(), ex.getMessage());
            throw ex;
        }
    }
}
