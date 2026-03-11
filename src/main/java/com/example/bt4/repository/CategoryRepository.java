package com.example.bt4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bt4.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
