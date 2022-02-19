package com.blps.firstlaboratory.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "Orders")
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product", referencedColumnName = "product_id")
    Product product;

    @Column(name = "date", nullable = false)
    Date date;

    @Column(name = "quantity")
    Long quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping", referencedColumnName = "shipping_id")
    Shipping shipping;

}
