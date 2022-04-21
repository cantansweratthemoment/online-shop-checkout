package com.blps.firstlaboratory.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "product_type")
public class ProductType {
    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;
}
