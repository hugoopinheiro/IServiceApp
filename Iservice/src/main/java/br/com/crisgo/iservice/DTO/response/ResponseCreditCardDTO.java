// ResponseSellerDTO.java
package br.com.crisgo.iservice.DTO.response;

import br.com.crisgo.iservice.models.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ResponseCreditCardDTO {

    private Long id;
    private Date expirationDate;
    private String owner;
    private String creditNumberCard;
    private User userId;
}
