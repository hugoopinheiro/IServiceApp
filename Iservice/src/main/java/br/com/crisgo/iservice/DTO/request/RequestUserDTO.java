package br.com.crisgo.iservice.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestUserDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "userName is mandatory")
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String login;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Phone is mandatory")
    private String contact;
    // Address Fields
    private String street;
    private String cep;
    private String complement;
    private String state;
    private String houseNumber;

    private RequestAddressDTO address;  // Nested DTO for addre
}
