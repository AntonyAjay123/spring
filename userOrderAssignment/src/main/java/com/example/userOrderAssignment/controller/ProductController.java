package com.example.userOrderAssignment.controller;

import com.example.userOrderAssignment.model.Product;
import com.example.userOrderAssignment.service.ProductService;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity saveProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return new ResponseEntity("Product saved", HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity getProduct(@Nullable @RequestParam Integer id,@Nullable @RequestParam String category) {
        if(category==null) {
            if (id == null)
                return new ResponseEntity(productService.getProducts(), HttpStatus.ACCEPTED);
            else {
                return new ResponseEntity(productService.getProductId(id), HttpStatus.ACCEPTED);
            }
        }
        else return new ResponseEntity(productService.getCategory(category),HttpStatus.ACCEPTED);
    }

}
