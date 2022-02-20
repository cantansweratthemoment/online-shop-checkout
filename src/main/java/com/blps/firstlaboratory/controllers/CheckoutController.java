package com.blps.firstlaboratory.controllers;

import com.blps.firstlaboratory.model.Customer;
import com.blps.firstlaboratory.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody String login, @RequestBody String name) {
        return customerService.addCustomer(login, name);
    }

    
}
