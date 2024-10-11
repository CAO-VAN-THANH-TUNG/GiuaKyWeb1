package com.bannguoiminhyeu.bannguoiyeu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bannguoiminhyeu.bannguoiyeu.models.Product;

@Service
public class ProductService {

    @Autowired
    private ProductsRepository productsRepository;

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    public Product findById(int id) {
        return productsRepository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return productsRepository.save(product);
    }

    public void delete(int id) {
        productsRepository.deleteById(id);
    }

    public List<Product> searchProducts(String query) {
        return productsRepository.findByNameContainingIgnoreCase(query);
    }
}
