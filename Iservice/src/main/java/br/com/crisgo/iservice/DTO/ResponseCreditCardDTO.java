// ResponseSellerDTO.java
package br.com.crisgo.iservice.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseCreditCardDTO {

    private Long id;
    private Date expirationDate;
    private String owner;
    private String creditNumberCard;
    private User userId;
}
