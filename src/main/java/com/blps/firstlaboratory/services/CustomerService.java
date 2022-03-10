package com.blps.firstlaboratory.services;

import com.blps.firstlaboratory.model.Customer;
import com.blps.firstlaboratory.model.CustomerLevel;
import com.blps.firstlaboratory.repostitory.CustomerLevelRepository;
import com.blps.firstlaboratory.repostitory.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerLevelRepository customerLevelRepository;


    public Customer addCustomer(String login, String name) {
        Customer customer = customerRepository.findCustomerByLogin(login);
        if (customer == null) {
            Customer newCustomer = new Customer();
            newCustomer.setLogin(login);
            newCustomer.setName(name);
            newCustomer.setCash(0L);
            newCustomer.setLevel(customerLevelRepository.getCustomerLevelById(1L));
            customerRepository.save(newCustomer);
            return newCustomer;
        }
        return customer;
    }

    public boolean checkPayment(Long price, String login) {
        Customer customer = customerRepository.findCustomerByLogin(login);
        Long currentCash = customer.getCash();
        return currentCash >= price;
    }

    public void reduceCash(Long price, String login) {
        Customer customer = customerRepository.findCustomerByLogin(login);
        Long currentCash = customer.getCash();
        customer.setCash(currentCash - price);
        customerRepository.save(customer);
    }

    public Long getLevel(String login) {
        Customer customer = customerRepository.findCustomerByLogin(login);
        return  customer.getLevel().getId();
    }
}
