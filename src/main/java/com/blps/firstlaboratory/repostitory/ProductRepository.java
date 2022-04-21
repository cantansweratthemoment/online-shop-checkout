package com.blps.firstlaboratory.repostitory;

import com.blps.firstlaboratory.model.Product;
import com.blps.firstlaboratory.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Boolean existsByProductName(String name);
    Product findByProductName(String name);
    List<Product> findAllByProductTypeIs(ProductType productType);
}
