package br.com.crisgo.iservice.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestPaymentsDTO {

    @NotBlank(message = "Name is mandatory")
    private Double amount;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String paymentMethod;
}
