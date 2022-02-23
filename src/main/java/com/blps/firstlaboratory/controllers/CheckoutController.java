package com.blps.firstlaboratory.controllers;

import com.blps.firstlaboratory.exceptions.WrongOrderInfoException;
import com.blps.firstlaboratory.model.Customer;
import com.blps.firstlaboratory.model.Product;
import com.blps.firstlaboratory.model.Shipping;
import com.blps.firstlaboratory.repostitory.CustomerLevelRepository;
import com.blps.firstlaboratory.requests.AddCustomerRequest;
import com.blps.firstlaboratory.requests.CheckPaymentRequest;
import com.blps.firstlaboratory.requests.ProductExistsRequest;
import com.blps.firstlaboratory.requests.ProductPossibilityRequest;
import com.blps.firstlaboratory.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private CustomerLevelService customerLevelService;

    /**
     * Добавление или поиск покупателя среди существующих.
     */
    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody AddCustomerRequest request) {
        return customerService.addCustomer(request.getLogin(), request.getName());
    }

    /**
     * Проверка на наличие продукта.
     */
    @RequestMapping(value = "checkExists", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Boolean> checkProductExists(@RequestBody ProductExistsRequest products) {
        return productService.checkExists(products.getProductNames());
    }

    /**
     * Проверка на возможность доставки продукта.
     */
    @PostMapping("/checkShippingPossibility")
    public Map<String, Boolean> checkShippingPossibility(@RequestBody ProductPossibilityRequest products) {
        return productService.checkPossibility(products.getProductNames(), products.getCountry(), products.getRegion());
    }

    /**
     * Проверка успешной оплаты.
     */
    @PostMapping("/checkPayment")
    public ResponseEntity<String> checkPayment(@RequestBody CheckPaymentRequest request) {
        String login = request.getLogin();
        String[] products = request.getProducts();
        String country = request.getCountry();
        String region = request.getRegion();
        List<Product> productsList = productService.getProductsByNames(products);
        Long level = customerService.getLevel(login);
        Double discount = customerLevelService.getDiscount(level);
        Long price = productService.calculatePrice(productsList, discount);
        boolean result = customerService.checkPayment(price, login);
        if (result) {
            Shipping shipping = shippingService.getShippingByCountryAndRegion(country, region);
            Map<String, Boolean> productsExistence = productService.checkExists(products);
            Map<String, Boolean> shippingPossibility = productService.checkPossibility(products, country, region);
            try {
                orderService.isOrderInfoCorrect(productsExistence, shippingPossibility);
            } catch (WrongOrderInfoException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
            orderService.registerOrder(productsList, shipping, login);
            productService.reduceQuantity(productsList);
            customerService.reduceCash(price, login);
            return new ResponseEntity<>("Payment successful!", HttpStatus.OK);
        }
        return new ResponseEntity<>("No money =(", HttpStatus.OK);
    }
}
