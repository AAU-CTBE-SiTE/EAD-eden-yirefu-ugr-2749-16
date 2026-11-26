package com.site.order.controller;

import com.site.order.model.Order;
import com.site.order.model.OrderResponse;
import com.site.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // CREATE
    @PostMapping
    public OrderResponse saveOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    // READ ALL
    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    // READ BY CUSTOMER ID
    @GetMapping("/customer/{customerId}")
    public OrderResponse getOrderByCustomerId(
            @PathVariable String customerId) {
        return orderService.getOrderByCustomerId(customerId);
    }

    // UPDATE
    @PutMapping("/{id}")
    public OrderResponse updateOrder(
            @PathVariable Long id,
            @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}