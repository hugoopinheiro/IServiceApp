package br.com.crisgo.iservice.DTO;

import java.time.LocalDateTime;

public record RequestProduct(String productName, LocalDateTime createdAt, String description, Double price, String category) {
}
