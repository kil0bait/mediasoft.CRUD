package com.rasul.crud.CRUD.controller;

import com.rasul.crud.CRUD.entity.Product;
import com.rasul.crud.CRUD.handler.exceptions.EmptyProductStorageException;
import com.rasul.crud.CRUD.handler.exceptions.NotFoundByArticleException;
import com.rasul.crud.CRUD.handler.exceptions.NotFoundByIdException;
import com.rasul.crud.CRUD.service.ProductService;
import org.hibernate.annotations.processing.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> showAllProducts() throws EmptyProductStorageException {
        List<Product> allProducts = productService.getAllProducts();
        return allProducts;
    }

    @PostMapping("/products")
    public Product addNewProduct(@RequestBody Product product) throws SQLException {
        productService.saveProduct(product);
        return product;
    }

    @DeleteMapping("/products")
    public ResponseEntity<?> deleteAllProducts() throws EmptyProductStorageException{
        productService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/products/{uuid}")
    public Product getProductById(@PathVariable("uuid") UUID uuid) throws NotFoundByIdException {
        Product product = productService.getProductById(uuid);
        return product;
    }

    @PutMapping("/products/{uuid}")
    public Product updateProductById(@PathVariable("uuid") UUID uuid, @RequestBody Product product) throws SQLException, NotFoundByIdException {
        Product newProduct = productService.updateProductById(product, uuid);
        return newProduct;
    }

    @DeleteMapping("/products/{uuid}")
    public ResponseEntity<?> deleteProductById(@PathVariable("uuid") UUID uuid) throws NotFoundByIdException{
        productService.deleteProductById(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/products/article/{article}")
    public Product getProductByArticle(@PathVariable("article") String article) throws NotFoundByArticleException{
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
