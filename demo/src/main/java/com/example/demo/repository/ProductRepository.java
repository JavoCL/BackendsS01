package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
    // Spring data JPA se va encargar de realizar automaticamente 
    // los metodos basicos como save, findAll, findById, deleteById
}
