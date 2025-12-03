package com.spr.reactivedemo.module;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
@Data
@Table("orders")
public class Order {
    @Id
    private Long id;

    private Long userId;

    private Long medicineId;

    private int quantity;

    private BigDecimal totalPrice;

    // Getters and Setters
}
