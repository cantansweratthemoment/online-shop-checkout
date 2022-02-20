package com.blps.firstlaboratory.services;

import com.blps.firstlaboratory.model.Customer;
import com.blps.firstlaboratory.repostitory.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckoutService {
    private final CustomerRepository customerRepository;

    public Customer addCustomer(String login, String name) {
        Customer customer = customerRepository.findCustomerByLogin(login);
        if (customer == null) {
            Customer newCustomer = new Customer();
            newCustomer.setLogin(login);
            newCustomer.setName(name);
            customerRepository.save(newCustomer);
            return newCustomer;
        }
        return customer;
    }
}
