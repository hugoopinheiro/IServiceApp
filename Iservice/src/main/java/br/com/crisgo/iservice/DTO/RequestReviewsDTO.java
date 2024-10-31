package br.com.crisgo.iservice.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestReviewsDTO {

    @NotBlank(message = "Name is mandatory")
    private Integer rating;

    @NotBlank(message = "Email is mandatory")
    private String comments;

}
