package com.blps.firstlaboratory.services;

import com.blps.firstlaboratory.exceptions.WrongOrderInfoException;
import com.blps.firstlaboratory.model.Order;
import com.blps.firstlaboratory.model.Product;
import com.blps.firstlaboratory.model.Shipping;
import com.blps.firstlaboratory.repostitory.CustomerRepository;
import com.blps.firstlaboratory.repostitory.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void registerOrder(List<Product> products, Shipping shipping, String login) {
        Order order = new Order();
        order.setDate(new Date());
        order.setCustomerLogin(login);
        order.setProduct(products);
        order.setShipping(shipping);
        orderRepository.save(order);
    }

    public boolean isOrderInfoCorrect(Map<String, Boolean> productsExistence, Map<String, Boolean> shippingPossibility) {
        boolean allOrdersExist = true;
        for (Boolean existence : productsExistence.values()) {
            if (!existence) {
                allOrdersExist = false;
                break;
            }
        }
        boolean allShippingIsPossible = true;
        for (Boolean possibility : shippingPossibility.values()) {
            if (!possibility) {
                allShippingIsPossible = false;
                break;
            }
        }
        boolean result = allOrdersExist && allShippingIsPossible;
        if (!result) {
            throw new WrongOrderInfoException("Order info is incorrect!");
        }
        return true;
    }
}