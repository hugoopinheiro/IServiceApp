// ResponseUserDTO.java
package br.com.crisgo.iservice.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseUserDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
}
