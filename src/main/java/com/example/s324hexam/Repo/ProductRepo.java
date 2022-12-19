package com.example.s324hexam.Repo;

import com.example.s324hexam.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    //find product by name
    @Query("SELECT p FROM Product p WHERE p.name = ?1")
    List<Product> findByName(String keyword);
}


