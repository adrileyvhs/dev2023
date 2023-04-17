package com.dev2023.Entities;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;
    @Getter
    @Setter
    private  String name;
    @Getter
    @Setter
    @Column(columnDefinition = "TEXT")
    private  String description;

    @Getter
    @Setter
    private  Double price;

    @Getter
    @Setter
    private  String img_url;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    private Date date ;
    @Getter
    @ManyToMany
    @JoinTable(name = "tb_product_category",joinColumns = @JoinColumn(name = "product_id"),inverseJoinColumns = @JoinColumn(name = "category_id")    )
    private Set<Category> categories =  new HashSet<>();
    public Product(String name, String description, Double price, String url, Date date) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.img_url = url;
        this.date = date;
    }
}
