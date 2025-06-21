package br.com.crisgo.iservice.mail;

import br.com.crisgo.iservice.config.EmailConfig;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

@Component
public class EmailSender implements Serializable {

    Logger logger = (Logger) LoggerFactory.getLogger(EmailSender.class);

    private final JavaMailSender mailSender;
    private String to;
    private String subject;
    private String body;
    private ArrayList<InternetAddress> recipients = new ArrayList<>();
    private File attachment;

    public EmailSender(JavaMailSender mailSender, UrlPathHelper urlPathHelper) {
        this.mailSender = mailSender;
    }

    public EmailSender to(String to) {
        this.to = to;
        this.recipients = getRecipients(to);
        return this;
    }

    public EmailSender withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailSender withMessage(String body) {
        this.body = body;
        return this;
    }

    public EmailSender attach(File fileDir) {
        this.attachment = fileDir;
        return this;
    }

    public void send(EmailConfig config) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(config.getFrom());
            helper.setTo(recipients.toArray(new InternetAddress[0]));
            helper.setSubject(subject);
            helper.setText(body, true);
            if (attachment != null) {
                helper.addAttachment(attachment.getName(), attachment);
            }
            mailSender.send(mimeMessage);
            logger.info("Email sent to {}, with the subject '{}'", to, subject);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        reset();
    }

    private ArrayList<InternetAddress> getRecipients(String to) {
        if (to == null || to.isBlank()) {
            throw new IllegalArgumentException("Recipient email address must not be null or empty.");
        }

        String toWithoutSpaces = to.replaceAll("\\s ", "");
        StringTokenizer tok = new StringTokenizer(toWithoutSpaces, ";");
        ArrayList<InternetAddress> recipientsList = new ArrayList<>();
        while (tok.hasMoreElements()) {
            try {
                recipientsList.add(new InternetAddress((tok.nextElement().toString())));
            } catch (AddressException e) {
                throw new RuntimeException("Error sendind the email", e);
            }
        }
        return recipientsList;
    }

    private void reset() {
        this.to = null;
        this.subject = null;
        this.body = null;
        this.recipients = null;
        this.attachment = null;
    }
}
