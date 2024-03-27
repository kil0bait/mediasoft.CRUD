package com.rasul.crud.CRUD.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="name")
    @NotBlank(message="name cannot be blank")
    private String name;

    @Column(name="article", unique = true)
    @NotBlank(message="article cannot be blank")
    private String article;

    @Column(name="description")
    @NotBlank(message="description cannot be blank")
    private String description;

    @Column(name="price")
    @Positive(message="Price should be greater than 0")
    private BigDecimal price;

    @Column(name="count")
    @Positive(message="Price should be greater than 0")
    private Integer count;

    @Column(name="date_time_last_edit")
    private LocalDateTime dateTimeLastEdit;

    @Column(name="date_creation")
    private LocalDate dateCreation;

    @Column(name="category")
    @NotBlank(message="description cannot be blank")
    private String category;

    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDate.now();
        dateTimeLastEdit = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dateTimeLastEdit = LocalDateTime.now();
    }
}
