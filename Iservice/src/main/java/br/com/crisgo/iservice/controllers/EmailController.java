package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.DTO.request.EmailRequestDTO;
import br.com.crisgo.iservice.docs.EmailControllerDocs;
import br.com.crisgo.iservice.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/email")
public class EmailController implements EmailControllerDocs {

    @Autowired
    private EmailService emailService;

    @PostMapping
    @Override
    public ResponseEntity<String> sendEmail( @RequestBody EmailRequestDTO emailRequestDTO) {
        emailService.sendSimpleEmail(emailRequestDTO);
        return new ResponseEntity<>("Email sent successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> sendEmailWithAttachment(EmailRequestDTO emailRequestDTO) {
        return null;
    }
}
