package com.blps.firstlaboratory.controllers;

import com.blps.firstlaboratory.dto.CustomerDto;
import com.blps.firstlaboratory.model.Customer;
import com.blps.firstlaboratory.services.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody String login, @RequestBody String name) {
        return checkoutService.addCustomer(login, name);
    }

    
}
