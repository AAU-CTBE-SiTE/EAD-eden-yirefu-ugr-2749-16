package com.site.order.service;

import com.site.order.model.Order;
import com.site.order.model.CustomerResponse;
import com.site.order.model.OrderResponse;
import com.site.order.repository.OrderRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestClient customerRestClient;

    public OrderService(
            OrderRepository orderRepository,
            RestClient customerRestClient) {

        this.orderRepository = orderRepository;
        this.customerRestClient = customerRestClient;
    }

    // GET CUSTOMER FROM CUSTOMER SERVICE
    private CustomerResponse getCustomer(String customerId) {

        try {
            CustomerResponse customer = customerRestClient
                    .get()
                    .uri("/customers/{id}", customerId)
                    .retrieve()
                    .body(CustomerResponse.class);

            if (customer == null) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Customer not found");
            }

            return customer;

        } catch (RestClientException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "Unable to retrieve customer from Customer Service");
        }
    }

    // CREATE ORDER
    public OrderResponse saveOrder(Order order) {

        CustomerResponse customer = getCustomer(order.getCustomerId());

        Order savedOrder = orderRepository.save(order);

        return new OrderResponse(savedOrder, customer);
    }

    // GET ALL ORDERS
    public List<OrderResponse> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(order -> new OrderResponse(
                        order,
                        getCustomer(order.getCustomerId())))
                .toList();
    }

    // GET ORDER BY ID
    public OrderResponse getOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order not found"));

        CustomerResponse customer = getCustomer(order.getCustomerId());

        return new OrderResponse(order, customer);
    }

    // GET ORDER BY CUSTOMER ID
    public OrderResponse getOrderByCustomerId(String customerId) {

        Order order = orderRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order not found"));

        CustomerResponse customer = getCustomer(customerId);

        return new OrderResponse(order, customer);
    }

    // UPDATE ORDER
    public OrderResponse updateOrder(Long id, Order updatedOrder) {

        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order not found"));

        CustomerResponse customer = getCustomer(updatedOrder.getCustomerId());

        existingOrder.setProductName(updatedOrder.getProductName());
        existingOrder.setQuantity(updatedOrder.getQuantity());
        existingOrder.setTotalPrice(updatedOrder.getTotalPrice());
        existingOrder.setCustomerId(updatedOrder.getCustomerId());

        Order savedOrder = orderRepository.save(existingOrder);

        return new OrderResponse(savedOrder, customer);
    }

    // DELETE ORDER
    public void deleteOrder(Long id) {

        if (!orderRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Order not found");
        }

        orderRepository.deleteById(id);
    }
}