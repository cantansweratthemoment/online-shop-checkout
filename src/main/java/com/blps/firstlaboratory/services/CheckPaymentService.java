package com.blps.firstlaboratory.services;

import com.blps.firstlaboratory.model.Product;
import com.blps.firstlaboratory.model.Shipping;
import com.blps.firstlaboratory.utils.Sender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CheckPaymentService {

    private static final Double ADMIN_DISCOUNT = 1.0;

    @Autowired
    Sender sender;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerLevelService customerLevelService;

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private OrderService orderService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseEntity<String> checkPayment(String[] products, String login, String country, String region) {
        List<Product> productsList = productService.getProductsByNames(products);
        Long level = customerService.getLevel(login);
        Double discount = customerLevelService.getDiscount(level);
        Long price = productService.calculatePrice(productsList, discount);
        boolean result = customerService.checkPayment(price, login);
        if (result) {
            Shipping shipping = shippingService.getShippingByCountryAndRegion(country, region);
            Map<String, Boolean> productsExistence = productService.checkExists(products);
            Map<String, Boolean> shippingPossibility = productService.checkPossibility(products, country, region);
            orderService.isOrderInfoCorrect(productsExistence, shippingPossibility);
            orderService.registerOrder(productsList, shipping, login);
            productService.reduceQuantity(productsList);
            customerService.reduceCash(price, login);

            sender.send(customerService.getMail(login));
            return new ResponseEntity<>("Payment successful!", HttpStatus.OK);
        }
        return new ResponseEntity<>("No money =(", HttpStatus.OK);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseEntity<String> checkAdminPayment(String[] products, String login, String country, String region) {
        List<Product> productsList = productService.getProductsByNames(products);
        Double discount = ADMIN_DISCOUNT;
        Long price = productService.calculatePrice(productsList, discount);
        boolean result = customerService.checkPayment(price, login);
        if (result) {
            Shipping shipping = shippingService.getShippingByCountryAndRegion(country, region);
            Map<String, Boolean> productsExistence = productService.checkExists(products);
            Map<String, Boolean> shippingPossibility = productService.checkPossibility(products, country, region);
            orderService.isOrderInfoCorrect(productsExistence, shippingPossibility);
            orderService.registerOrder(productsList, shipping, login);
            productService.reduceQuantity(productsList);
            customerService.reduceCash(price, login);

            sender.send(customerService.getMail(login));
            return new ResponseEntity<>("Payment successful!", HttpStatus.OK);
        }
        return new ResponseEntity<>("No money =(", HttpStatus.OK);
    }
}
