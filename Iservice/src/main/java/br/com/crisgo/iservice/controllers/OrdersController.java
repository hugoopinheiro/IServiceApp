package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.models.Orders;
import br.com.crisgo.iservice.services.OrdersService;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Validated
public class OrdersController {
    OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("user/{userId}/seller/{sellerId}/product/{productId}")
    public ResponseEntity createOrder(@PathVariable Long userId, @PathVariable Long productId, @PathVariable Long sellerId, @RequestBody Orders orders){
        Orders createdOrders = ordersService.createOrder(userId, productId, sellerId, orders);
        return ResponseEntity.ok(createdOrders);
    }
    @GetMapping("user/{userId}")
    public ResponseEntity findOrdersByUser(@PathVariable Long userId){
        List<Orders> ordersList = ordersService.findByUser(userId);
        return ResponseEntity.ok(ordersList);
    }
}
