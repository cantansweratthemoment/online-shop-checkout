package com.blps.firstlaboratory.controllers;

import com.blps.firstlaboratory.model.Customer;
import com.blps.firstlaboratory.services.CustomerService;
import com.blps.firstlaboratory.services.ProductService;
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

    /**
     * Добавление или поиск покупателя среди существующих.
     * @param login
     * @param name
     */
    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody String login, @RequestBody String name) {
        return customerService.addCustomer(login, name);
    }

    /**
     * Проверка на наличие продукта.
     */
    @GetMapping("/checkExists")
    public Map<String, Boolean> checkProductExists(@RequestParam("products") String products) {
        return productService.checkExists(products);
    }

    /**
     * Проверка на возможность доставки продукта.
     */
    @GetMapping("/checkShippingPossibility")
    public Map<String, Boolean> checkShippingPossibility(@RequestParam("product") String products, @RequestParam("country") String country,
                                            @RequestParam("region") String region) {
        return productService.checkPossibility(products, country, region);
    }

    @GetMapping("/checkPayment")
    public boolean checkPayment(@RequestParam("price") Long price, @RequestParam("login") String login, @RequestParam("product") String products) {
        boolean result = customerService.checkPayment(price, login);
        /*if (result) {

        }*/
    }
}
