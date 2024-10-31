package br.com.crisgo.iservice.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestProductDTO {

    @NotBlank(message = "Nome é obrigatorio ")
    private String name;

    @NotBlank(message = "Descrição é obrigatorio ")
    private String description;

    @NotBlank(message = "Preço é obrigatorio ")
    private Double price;

    @NotBlank(message = "Categoria é obrigatorio ")
    private String category;

    @NotBlank(message = "Estar ligado a um vendedor é obrigatorio ")
    private Seller seller;
}
