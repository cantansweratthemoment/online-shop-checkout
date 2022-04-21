package com.blps.firstlaboratory.repostitory;

import com.blps.firstlaboratory.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    List<ProductType> getAll();
}
