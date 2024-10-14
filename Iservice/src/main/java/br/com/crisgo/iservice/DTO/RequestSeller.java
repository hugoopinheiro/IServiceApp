package br.com.crisgo.iservice.DTO;

import java.time.LocalDateTime;

public record RequestSeller(String name, String email, String password, String phone, String sellerDescription, LocalDateTime createdAt) {
}
