package br.com.crisgo.iservice.DTO.request;

import jakarta.validation.constraints.NotBlank;

public class RequestAddressDTO {
    @NotBlank(message = "CEP é obrigatorio")
    private String cep;
    @NotBlank(message = "Complemento é obrigatorio")
    private String complement;
    @NotBlank(message = "Numero da casa é obrigatorio")
    private String houseNumber;
    @NotBlank(message = "Rua é obrigatorio")
    private String street;
    @NotBlank(message = "Estado é obrigatorio")
    private String state;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
