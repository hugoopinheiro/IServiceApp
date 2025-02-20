package br.com.crisgo.iservice.DTO.request;

import br.com.crisgo.iservice.models.Seller;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestBankAccountDTO {

    @NotBlank(message = "Numero da conta é obrigatorio")
    private String numberAccount;

    @NotBlank(message = "Agência é obrigatorio")
    private String agency;

    private Seller seller;

}
