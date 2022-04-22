package com.blps.firstlaboratory.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "popular_products")
public class PopularProducts {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "time", nullable = false)
    Timestamp time;

    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "id")
    ProductType type;

    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "product_id")
    Product product;
}
