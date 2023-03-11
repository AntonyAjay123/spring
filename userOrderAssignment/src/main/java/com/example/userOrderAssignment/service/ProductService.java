package com.example.userOrderAssignment.service;

import com.example.userOrderAssignment.model.Product;
import com.example.userOrderAssignment.repository.ProductRepository;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void saveProduct(Product product){
        productRepository.save(product);
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product getProductId(int id){
        return productRepository.findById(id).get();
    }

    public List<Product> getCategory(String category){
        return productRepository.findByCategory(category);
    }
}
