package br.com.crisgo.iservice.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestReviewsDTO {

    @NotNull(message = "Rating is mandatory")
    private Integer rating;

    @NotBlank(message = "Comments are mandatory")
    private String comments;
}
