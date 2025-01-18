package br.com.crisgo.iservice.DTO.request;

import br.com.crisgo.iservice.models.BankAccount;
import br.com.crisgo.iservice.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestSellerDTO {

    @NotBlank(message = "Name is mandatory")
    private String cpf;

    @NotBlank(message = "Description is mandatory")
    private String sellerDescription;

    private String numberAccount;
    private String agency;

    private RequestBankAccountDTO bankAccount; // Nested DTO for bank account

}
