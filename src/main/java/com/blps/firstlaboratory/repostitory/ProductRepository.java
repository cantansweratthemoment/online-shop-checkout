package com.blps.firstlaboratory.repostitory;

import com.blps.firstlaboratory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Boolean existsByProductName(String name);
    Product findByProductName(String name);
}
