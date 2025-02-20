package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.DTO.request.RegisterDTO;
import br.com.crisgo.iservice.DTO.response.LoginResponse;
import br.com.crisgo.iservice.infra.TokenService;
import br.com.crisgo.iservice.models.User;
import br.com.crisgo.iservice.repositorys.UserRepository;
import br.com.crisgo.iservice.DTO.request.AuthenticationDTO;
import br.com.crisgo.iservice.mapper.Mapper;

import br.com.crisgo.iservice.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.desktop.UserSessionEvent;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;

    @GetMapping("/message")
    public String getMessage() {
        return "Hello from Spring Boot!";
    }

    @PostMapping("/send")
    public String receiveData(@RequestBody String data) {
        return "Received: " + data;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = authenticationManager.authenticate(usernamePassword);

            String token = tokenService.generateToken((User) auth.getPrincipal());

            logger.info("User '{}' logged in successfully", data.login());
            return ResponseEntity.ok(new LoginResponse(token));

        } catch (AuthenticationException e) {
            logger.warn("Failed login attempt for user '{}'", data.login());
            return ResponseEntity.status(401).body("Invalid username or password.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
        userService.register(data);
        return ResponseEntity.ok().build();
    }
}