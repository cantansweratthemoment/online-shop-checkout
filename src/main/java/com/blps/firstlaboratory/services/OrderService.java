package com.blps.firstlaboratory.services;

import com.blps.firstlaboratory.model.Order;
import com.blps.firstlaboratory.repostitory.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void registerOrder(String products) {
        Order order = new Order();
        order.setDate(new Date());

    }
}
