// ResponseProductDTO.java
package br.com.crisgo.iservice.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseProductDTO {

    private Long id;
    private String name;
    private String description;
    private String category;
    private Seller seller;
    private Double price;
    private LocalDateTime createdAt;
}
