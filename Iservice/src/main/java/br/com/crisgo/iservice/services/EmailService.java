package br.com.crisgo.iservice.services;

import br.com.crisgo.iservice.DTO.request.EmailRequestDTO;
import br.com.crisgo.iservice.config.EmailConfig;
import br.com.crisgo.iservice.mail.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private EmailSender emailSender;

    public void sendSimpleEmail(EmailRequestDTO emailRequestDTO){
        emailSender.
                to(emailRequestDTO.getTo())
                .withSubject(emailRequestDTO.getSubject())
                .withMessage(emailRequestDTO.getBody())
                .send(emailConfig);

    }
    // add the setEmailWithAtacchement method to send email with recipes from orders.
}
