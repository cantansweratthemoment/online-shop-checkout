package com.blps.firstlaboratory.controllers;

import com.blps.firstlaboratory.model.Customer;
import com.blps.firstlaboratory.services.CustomerService;
import com.blps.firstlaboratory.services.ProductService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;

    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody String login, @RequestBody String name) {
        return customerService.addCustomer(login, name);
    }

    /*
     * Проверка на наличие продукта
     */
    @GetMapping("/checkExists")
    public Map<String, Boolean> checkProductExists(@RequestParam("products") String products) {
        return productService.checkExists(products);
    }

    /*
     * Проерка на возможность доставки продукта
     */
    @GetMapping("/checkShippingPossibility")
    public Map<String, Boolean> checkShippingPossibility(@RequestParam("product") String products, @RequestParam("country") String country,
                                            @RequestParam("region") String region) {
        return productService.checkPossibility(products, country, region);
    }

}
