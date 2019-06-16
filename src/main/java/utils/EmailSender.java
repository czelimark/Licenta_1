package utils;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Send an email.
 */
@Component("emailSender")
@EnableAutoConfiguration
public class EmailSender {

    private final JavaMailSender javaMailSender;

    public EmailSender(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    private void sendEmail(String from, String to) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Invite");
        message.setText("You've been invited to...");
        javaMailSender.send(message);
    }

}
