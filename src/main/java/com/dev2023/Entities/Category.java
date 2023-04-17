package com.dev2023.Entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.TemporalType.DATE;


@NoArgsConstructor
@Entity
@Table(name = "tb_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    @Temporal(DATE)
    private Date created_At ;
    @ManyToMany(mappedBy = "categories")
    @Getter
    private Set<Product> products = new HashSet<>();
    public Category(String name) {
        this.name = name;
    }
}
