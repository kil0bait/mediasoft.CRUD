package org.example.warehousemanager.dao;

import org.example.warehousemanager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> getProductByArticle(String article);

    boolean existsById(UUID id);

//    boolean existsByArticle(String article);

    Product getProductById(UUID id);

    Product findByArticle(String article);

}
