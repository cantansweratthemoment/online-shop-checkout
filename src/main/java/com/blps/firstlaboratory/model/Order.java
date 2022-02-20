package com.blps.firstlaboratory.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Orders")
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "date", nullable = false)
    Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping", referencedColumnName = "shipping_id")
    Shipping shipping;

    @ManyToMany
    @JoinTable (name="product_order",
            joinColumns=@JoinColumn (name="order_id"),
            inverseJoinColumns=@JoinColumn(name="product_id"))
    List<Product> product;
}
