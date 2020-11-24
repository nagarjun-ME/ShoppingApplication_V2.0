package com.naga.spring.dbservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="products")

@Data
@NoArgsConstructor
@ToString
public class Product {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    //@SequenceGenerator(name = "sequenceGenerator")
    private long productId;

    @Column(name ="productName")
    private String productName;

    @Column(name = "productDescription")
    private String productDescription;

    @Column(name = "productPrice")
    private double productPrice;



    public Product(String productName, String productDescription, double productPrice) {

        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }


}
