package com.project.Capstone.ecommerce.repository;

import com.project.Capstone.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Add this line:
    boolean existsByName(String name);
}
