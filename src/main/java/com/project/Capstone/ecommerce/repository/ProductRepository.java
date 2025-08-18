package com.project.Capstone.ecommerce.repository;

import com.project.Capstone.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
