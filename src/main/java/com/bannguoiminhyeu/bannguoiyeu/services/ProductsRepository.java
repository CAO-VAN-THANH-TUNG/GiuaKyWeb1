package com.bannguoiminhyeu.bannguoiyeu.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bannguoiminhyeu.bannguoiyeu.models.Product;

public interface ProductsRepository extends JpaRepository<Product, Integer> {

    List<Product> findByNameContainingIgnoreCase(String name);
}
