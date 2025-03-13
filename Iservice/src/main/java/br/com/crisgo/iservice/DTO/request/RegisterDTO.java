package br.com.crisgo.iservice.DTO.request;


import br.com.crisgo.iservice.models.Role;

public record RegisterDTO(String name, String username, String email, String password, Role role, String contact, String street, String cep, String complement, String state, String houseNumber, RequestAddressDTO addressDTO) {
    @Override
    public String name() {
        return name;
    }

    @Override
    public String username() {
        return username;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public Role role() {
        return role;
    }

    @Override
    public String contact() {
        return contact;
    }

    @Override
    public String street() {
        return street;
    }

    @Override
    public String cep() {
        return cep;
    }

    @Override
    public String complement() {
        return complement;
    }

    @Override
    public String state() {
        return state;
    }

    @Override
    public String houseNumber() {
        return houseNumber;
    }

    @Override
    public RequestAddressDTO addressDTO() {
        return addressDTO;
    }
}
