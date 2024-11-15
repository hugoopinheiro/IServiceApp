// ResponseSellerDTO.java
package br.com.crisgo.iservice.DTO.response;

import br.com.crisgo.iservice.models.Address;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseSellerDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String sellerDescription;
    private LocalDateTime createdAt;
    private Address address;
}
