package br.com.crisgo.iservice.DTO.response;

import lombok.Data;

@Data
public class ResponseOrdersDTO {

    private Long id;
    private String status;
    private Double totalPrice;
}
//The ResponseOrdersDTO.java should only contain fields that you want to expose in the response. This includes details such as
// order status, total price, and any other necessary fields, but excludes sensitive or unnecessary information.