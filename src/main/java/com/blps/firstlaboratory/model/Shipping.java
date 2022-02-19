package com.blps.firstlaboratory.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "Shipping")
public class Shipping {

    @Id
    @Column(name = "shipping_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "Country")
    String country;

    @Column(name = "region")
    String region;

    @ManyToMany
    @JoinTable (name="product_shipping",
            joinColumns=@JoinColumn (name="shipping_id"),
            inverseJoinColumns=@JoinColumn(name="product_id"))
    private List<Product> shippingList;

}
