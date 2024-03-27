package org.example.warehousemanager.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProductRequest {
    @NotBlank(message="name cannot be blank")
    private String name;

    @NotBlank(message="article cannot be blank")
    private String article;

    @NotBlank(message="description cannot be blank")
    private String description;

    @Positive(message="Price should be greater than 0")
    private BigDecimal price;

    @Positive(message="Price should be greater than 0")
    private Integer count;

    private LocalDateTime dateTimeLastEdit;

    private LocalDate dateCreation;

    @NotBlank(message="description cannot be blank")
    private String category;
}
