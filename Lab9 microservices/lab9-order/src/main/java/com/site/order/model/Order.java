package com.site.order.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private int quantity;

    private double totalPrice;

    @Column(name = "customer_id", nullable = false, unique = true)
    private String customerId;
}