package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.DTO.request.RequestOrdersDTO;
import br.com.crisgo.iservice.DTO.response.ResponseOrdersDTO;
import br.com.crisgo.iservice.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Validated
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("user/{userId}/seller/{sellerId}/product/{productId}")
    public ResponseEntity<ResponseOrdersDTO> createOrder(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @PathVariable Long sellerId,
            @RequestBody @Validated RequestOrdersDTO requestOrdersDTO) {
        ResponseOrdersDTO createdOrder = ordersService.createOrder(userId, productId, sellerId, requestOrdersDTO);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<ResponseOrdersDTO>> findOrdersByUser(@PathVariable Long userId) {
        List<ResponseOrdersDTO> ordersList = ordersService.findByUser(userId);
        return ResponseEntity.ok(ordersList);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        ordersService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseOrdersDTO> updateOrder(
            @PathVariable Long id,
            @RequestBody @Validated RequestOrdersDTO requestOrdersDTO) {
        ResponseOrdersDTO updatedOrder = ordersService.updateOrder(id, requestOrdersDTO);
        return ResponseEntity.ok(updatedOrder);
    }
}
