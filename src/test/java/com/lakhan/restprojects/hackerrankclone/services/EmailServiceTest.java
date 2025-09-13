package com.lakhan.restprojects.hackerrankclone.services;

import com.lakhan.restprojects.hackerrankclone.models.NotificationEmail;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("local") // 👈 forces local profile
@Disabled
class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendEmailWithLocalProfile() throws InterruptedException {
        // Arrange
        String to = "hakelakhan@yahoo.com";
        String subject = "Test Email - Local Profile 3";
        String body = "This is a test email sent using the local profile.";

        // Act
        emailService.sendMail(NotificationEmail.builder().body(

                "Hi Lakhan, This is a test email from local profile")
                .subject(subject)
                .recepient(to).build());
        Thread.sleep(10000); // wait for async email to be sent
    }
}
