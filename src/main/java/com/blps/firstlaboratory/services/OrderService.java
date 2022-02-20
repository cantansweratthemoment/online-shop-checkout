package com.blps.firstlaboratory.services;

import com.blps.firstlaboratory.model.Order;
import com.blps.firstlaboratory.model.Product;
import com.blps.firstlaboratory.model.Shipping;
import com.blps.firstlaboratory.repostitory.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    public void registerOrder(List<Product> products, Shipping shipping) {
        Order order = new Order();
        order.setDate(new Date());
        order.setProduct(products);
        order.setShipping(shipping);
    }
}
