package com.rasul.crud.CRUD.dao;

import com.rasul.crud.CRUD.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product getProductByArticle(String article);

    boolean existsById(UUID id);

    boolean existsByArticle(String Article);

    Product getProductById(UUID id);

    Product findByArticle(String article);

}
