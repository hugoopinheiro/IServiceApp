// ResponseSellerDTO.java
package br.com.crisgo.iservice.DTO.response;

import br.com.crisgo.iservice.models.Address;
import br.com.crisgo.iservice.models.User;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
public class ResponseSellerDTO extends RepresentationModel<ResponseSellerDTO> {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String sellerDescription;
    private LocalDateTime createdAt;
    private ResponseAddressDTO responseAddressDTO;
    private ResponseUserDTO responseUserDTO;

    public ResponseSellerDTO(){}
}
