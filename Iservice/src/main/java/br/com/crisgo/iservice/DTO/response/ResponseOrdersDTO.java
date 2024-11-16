package br.com.crisgo.iservice.DTO.response;

import org.springframework.hateoas.RepresentationModel;

public class ResponseOrdersDTO extends RepresentationModel<ResponseOrdersDTO> {

    private Long id;
    private String status;
    private Double totalPrice;

    public ResponseOrdersDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
//The ResponseOrdersDTO.java should only contain fields that you want to expose in the response. This includes details such as
// order status, total price, and any other necessary fields, but excludes sensitive or unnecessary information.