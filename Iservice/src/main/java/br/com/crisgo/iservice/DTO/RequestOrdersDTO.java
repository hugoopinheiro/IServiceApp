package br.com.crisgo.iservice.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestOrdersDTO {

    @NotBlank(message = "Name is mandatory")
    private String status;

    @NotBlank(message = "Email is mandatory")
    @Email
    private Dopuble totalPrice;
}
