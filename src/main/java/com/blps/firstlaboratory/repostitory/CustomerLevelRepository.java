package com.blps.firstlaboratory.repostitory;

import com.blps.firstlaboratory.model.CustomerLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerLevelRepository  extends JpaRepository<CustomerLevel, Long> {
    CustomerLevel getCustomerLevelById(Long id);
}
