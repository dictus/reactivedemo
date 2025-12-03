package com.spr.reactivedemo.module;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("medicines")
@Data
public class Medicine {
    @Id
    private Long id;

    private String name;

    private int maxQuantity;

    private BigDecimal price;

    // Getters and Setters
}
