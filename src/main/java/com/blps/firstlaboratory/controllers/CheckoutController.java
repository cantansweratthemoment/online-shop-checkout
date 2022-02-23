package com.blps.firstlaboratory.controllers;

import com.blps.firstlaboratory.exceptions.WrongOrderInfoException;
import com.blps.firstlaboratory.model.Customer;
import com.blps.firstlaboratory.model.Product;
import com.blps.firstlaboratory.model.Shipping;
import com.blps.firstlaboratory.model.requests.AddCustomerRequest;
import com.blps.firstlaboratory.model.requests.CheckPaymentRequest;
import com.blps.firstlaboratory.model.requests.ProductExistsRequest;
import com.blps.firstlaboratory.model.requests.ProductPossibilityRequest;
import com.blps.firstlaboratory.services.CustomerService;
import com.blps.firstlaboratory.services.OrderService;
import com.blps.firstlaboratory.services.ProductService;
import com.blps.firstlaboratory.services.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public Boolean checkPayment(@RequestBody CheckPaymentRequest request) {
        Long price = request.getPrice();
        String login = request.getLogin();
        String[] products = request.getProducts();
        String country = request.getCountry();
        String region = request.getRegion();
        boolean result = customerService.checkPayment(price, login);
        if (result) {
            List<Product> productsList = productService.getProductsByNames(products);
            Shipping shipping = shippingService.getShippingByCountryAndRegion(country, region);
            Map<String, Boolean> productsExistence = productService.checkExists(products);
            Map<String, Boolean> shippingPossibility = productService.checkPossibility(products, country, region);
            if (!orderService.isOrderInfoCorrect(productsExistence, shippingPossibility)) {
                throw new WrongOrderInfoException("Order info is incorrect!");
            }
            orderService.registerOrder(productsList, shipping);
            productService.reduceQuantity(productsList);
            customerService.reduceCash(price, login);
        }
        return result;
    }
}
