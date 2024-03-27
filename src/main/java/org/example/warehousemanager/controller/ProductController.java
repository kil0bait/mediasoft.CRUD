package org.example.warehousemanager.controller;

import lombok.RequiredArgsConstructor;
import org.example.warehousemanager.model.Product;
import org.example.warehousemanager.model.dto.ProductRequest;
import org.example.warehousemanager.model.exception.EmptyProductStorageException;
import org.example.warehousemanager.model.exception.NotFoundByArticleException;
import org.example.warehousemanager.model.exception.NotFoundByIdException;
import org.example.warehousemanager.service.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("rest/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(@Param("article") String article) throws EmptyProductStorageException {
        List<Product> allProducts = productService.getAllProducts();
        return allProducts;
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) throws SQLException {
        productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

// to remove
//    @DeleteMapping
//    public ResponseEntity<?> deleteAllProducts() throws EmptyProductStorageException{
//        productService.deleteAll();
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @GetMapping("/{uuid}")
    public ProductResponse getProductById(@PathVariable("uuid") UUID uuid) throws NotFoundByIdException {
        Product product = productService.getProductById(uuid);
        return product;
    }

    @PutMapping("/{uuid}")
    public Product updateProductById(@PathVariable("uuid") UUID uuid, @RequestBody ProductRequest product) throws SQLException, NotFoundByIdException {
        Product newProduct = productService.updateProductById(product, uuid);
        return newProduct;
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("uuid") UUID uuid) throws NotFoundByIdException{
        productService.deleteProductById(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/products/article/{article}")
    public Product getProductByArticle(@PathVariable("article") String article) throws NotFoundByArticleException {
        Product product = productService.getProductByArticle(article);
        return product;
    }

    @PutMapping("/products/article/{article}")
    public Product updateProductByArticle(@PathVariable("article") String article, @RequestBody Product product) throws SQLException, NotFoundByArticleException {
        Product newProduct = productService.updateProductByArticle(product, article);
        return newProduct;
    }

    @DeleteMapping("/products/article/{article}")
    public ResponseEntity<?> deleteProductByArticle(@PathVariable("article") String article) throws NotFoundByArticleException{
        productService.deleteProductByArticle(article);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
