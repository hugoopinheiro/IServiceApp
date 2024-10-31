package br.com.crisgo.iservice.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestPaymentsDTO {

    @NotBlank(message = "Name is mandatory")
    private DOuble amount;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String paymentMethod;
}
