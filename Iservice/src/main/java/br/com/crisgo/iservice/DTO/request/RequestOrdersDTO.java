package br.com.crisgo.iservice.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestOrdersDTO {

    @NotBlank(message = "Status is mandatory")
    private String status;

    @NotNull(message = "Total price is mandatory")
    private Double totalPrice;
}


// The RequestOrdersDTO should have fields relevant to creating an order and must remove fields that are set by the backend,
//such as userId, productId, and sellerId. Also, update the field types and constraints to match the intended request data.