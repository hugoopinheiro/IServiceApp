package br.com.crisgo.iservice.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestSellerDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Phone is mandatory")
    private String phone;

    @NotBlank(message = "Description is mandatory")
    private String sellerDescription;

    private RequestAddressDTO address;  // Nested DTO for address
}
