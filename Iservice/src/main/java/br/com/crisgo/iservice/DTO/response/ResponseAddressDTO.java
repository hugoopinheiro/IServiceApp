package br.com.crisgo.iservice.DTO.response;

import lombok.Data;

@Data
public class ResponseAddressDTO {

    private Long addressId;
    private String cep;
    private String complement;
    private String houseNumber;
    private String street;
    private String state;
}
