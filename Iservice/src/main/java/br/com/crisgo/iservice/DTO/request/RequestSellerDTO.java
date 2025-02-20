package br.com.crisgo.iservice.DTO.request;

import br.com.crisgo.iservice.models.BankAccount;
import br.com.crisgo.iservice.models.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestSellerDTO {

    @NotBlank(message = "CPF is mandatory")
    private String cpf;

    @NotBlank(message = "Description is mandatory")
    private String sellerDescription;
    @NotBlank(message = "Numero da conta é obrigatorio")
    private String numberAccount;

    @NotBlank(message = "Agência é obrigatorio")
    private String agency;

    @Valid
    private RequestBankAccountDTO bankAccount;


}
