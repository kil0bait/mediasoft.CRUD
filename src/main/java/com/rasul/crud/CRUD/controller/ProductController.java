package com.rasul.crud.CRUD.controller;

import com.rasul.crud.CRUD.entity.Product;
import com.rasul.crud.CRUD.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> showAllProducts(){
        List<Product> allProducts = productService.getAllProducts();
        return allProducts;
    }

    @GetMapping("/products/{uuid}")
    public Product getProduct(@PathVariable("uuid") String uuid){
        Product product = productService.getProduct(uuid);

        return product;
    }
}
