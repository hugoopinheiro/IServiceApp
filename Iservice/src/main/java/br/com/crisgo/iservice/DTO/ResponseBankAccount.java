// ResponseSellerDTO.java
package br.com.crisgo.iservice.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseBankAccountDTO {

    private Long id;
    private String numberAccount;
    private String agency;
    private Seller seller; 
}
