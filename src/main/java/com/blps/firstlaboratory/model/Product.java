package com.blps.firstlaboratory.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "product_name", nullable = false)
    String productName;

    @Column(name = "quantity", nullable = false)
    Long quantity;

    @Column(name = "price", nullable = false)
    Long price;

    @ManyToMany
    @JoinTable (name="product_shipping",
            joinColumns=@JoinColumn (name="product_id"),
            inverseJoinColumns=@JoinColumn(name="shipping_id"))
    private List<Shipping> shippingList;

    @ManyToMany
    @JoinTable (name="product_order",
            joinColumns=@JoinColumn (name="product_id"),
            inverseJoinColumns=@JoinColumn(name="order_id"))
    List<Order> orders;

    @Column(name = "times_bought", nullable = false)
    Integer timesBought;

    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "id")
    ProductType productType;
}
