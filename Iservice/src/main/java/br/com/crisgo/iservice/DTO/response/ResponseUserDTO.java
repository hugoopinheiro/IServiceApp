package br.com.crisgo.iservice.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@Builder
public class ResponseUserDTO extends RepresentationModel<ResponseUserDTO> {
    private Long id;
    private String name;
    private String username;
    private String login;
    private String contact;
    private LocalDateTime createdAt;
    private ResponseAddressDTO responseAddressDTO;

    public ResponseUserDTO() {}

}