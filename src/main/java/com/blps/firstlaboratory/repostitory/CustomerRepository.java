package com.blps.firstlaboratory.repostitory;

import com.blps.firstlaboratory.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByLogin(String login);
}
