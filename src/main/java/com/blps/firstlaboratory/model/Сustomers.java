package com.blps.firstlaboratory.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Сustomers")
public class Сustomers {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false)
    String Name;

    @OneToMany(mappedBy="id", fetch=FetchType.EAGER)
    List<Order> orders = new ArrayList<>();
}
