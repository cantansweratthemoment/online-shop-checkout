package com.blps.firstlaboratory.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Customer")
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true, name = "login")
    String login;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "cash")
    Long cash;

    @OneToMany(mappedBy="id", fetch=FetchType.EAGER)
    List<Order> orders = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "level", referencedColumnName = "id")
    CustomerLevel level;
}
