package com.site.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponse {

    private Order order;

    private CustomerResponse customer;
}