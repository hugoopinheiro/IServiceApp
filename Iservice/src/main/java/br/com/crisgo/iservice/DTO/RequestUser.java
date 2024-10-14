package br.com.crisgo.iservice.DTO;


import java.time.LocalDateTime;

public record RequestUser(String name, String last_name, String email, String password, LocalDateTime created_at) {

}
