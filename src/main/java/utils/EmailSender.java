package utils;

import org.springframework.beans.factory.annotation.Autowired;
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

    public void sendEmail(String from, String to) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Invite");
        message.setText("You've been invited by " + from + " to register on this website in order to manage your projects more efficiently.\nhttp://localhost:8080/#!/register");
        javaMailSender.send(message);
    }

}
