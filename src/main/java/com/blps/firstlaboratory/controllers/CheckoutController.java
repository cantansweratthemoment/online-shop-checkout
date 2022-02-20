package com.blps.firstlaboratory.controllers;

import com.blps.firstlaboratory.model.Customer;
import com.blps.firstlaboratory.model.Product;
import com.blps.firstlaboratory.model.Shipping;
import com.blps.firstlaboratory.model.requestClasses.ProductExists;
import com.blps.firstlaboratory.model.requestClasses.ProductPossibility;
import com.blps.firstlaboratory.services.CustomerService;
import com.blps.firstlaboratory.services.OrderService;
import com.blps.firstlaboratory.services.ProductService;
import com.blps.firstlaboratory.services.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShippingService shippingService;

    /**
     * Добавление или поиск покупателя среди существующих.
     */
    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestParam String login, @RequestParam String name) {
        return customerService.addCustomer(login, name);
    }

    /**
     * Проверка на наличие продукта.
     */
    @RequestMapping(value = "checkExists", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Boolean> checkProductExists(@RequestBody ProductExists products) {
        return productService.checkExists(products.getProductNames());
    }

    /**
     * Проверка на возможность доставки продукта.
     */
    @PostMapping("/checkShippingPossibility")
    public Map<String, Boolean> checkShippingPossibility(@RequestBody ProductPossibility products) {
        return productService.checkPossibility(products.getProductNames(), products.getCountry(), products.getRegion());
    }

    @GetMapping("/checkPayment")
    public Boolean checkPayment(@RequestParam("price") Long price, @RequestParam("login") String login, @RequestBody String[] products, @RequestParam String country, @RequestParam String region) {
        boolean result = customerService.checkPayment(price, login);
        if (result) {
            customerService.reduceCash(price, login);
            List<Product> productsList = productService.getProductsByNames(products);
            Shipping shipping = shippingService.getShippingByCountryAndRegion(country, region);
            orderService.registerOrder(productsList, shipping);
            productService.reduceQuantity(productsList);
        }
        return result;
    }
}
