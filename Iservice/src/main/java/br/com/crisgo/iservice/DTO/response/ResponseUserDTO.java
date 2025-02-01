package br.com.crisgo.iservice.DTO.response;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
@Data
public class ResponseUserDTO extends RepresentationModel<ResponseUserDTO> {
    private Long user_id;
    private String name;
    private String login;
    private String contact;
    private LocalDateTime createdAt;
    private ResponseAddressDTO responseAddressDTO;

    public ResponseUserDTO() {}

}