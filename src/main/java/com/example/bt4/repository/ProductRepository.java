package com.example.bt4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bt4.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategoryId(int categoryId);
    List<Product> findByNameContainingIgnoreCaseAndCategoryId(String name, int categoryId);
}
