package br.com.crisgo.iservice.DTO.request;

import br.com.crisgo.iservice.models.Seller;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestProductDTO {

    @NotBlank(message = "Nome é obrigatorio ")
    private String name;

    @NotBlank(message = "Descrição é obrigatorio ")
    private String description;

    @NotNull(message = "Preço é obrigatorio ")
    private Double price;

    @NotBlank(message = "Categoria é obrigatorio ")
    private String category;

    @NotNull(message = "Vendedor é obrigatorio ")
    private Long sellerId;
}
