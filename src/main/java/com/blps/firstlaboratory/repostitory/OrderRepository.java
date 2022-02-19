package com.blps.firstlaboratory.repostitory;

import com.blps.firstlaboratory.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order, Long> {
}
