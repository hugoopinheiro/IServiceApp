package br.com.crisgo.iservice.DTO.response;

import lombok.Data;

@Data
public class ResponseProductDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String category;
    private Long sellerId;
}
