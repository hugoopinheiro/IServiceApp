package br.com.crisgo.iservice.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestCreditCardDTO {

    @NotBlank(message = "Data de validade é obrigatorio")
    private Date expirationDate;

    @NotBlank(message = "Nome do titular é obrigatorio")
    private String owner;

    @NotBlank(message = "Numero do cartao é obrigatorio")
    private String creditCardNumber;
  
}
