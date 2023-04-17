package com.dev2023.dto;

import com.dev2023.Entities.Category;
import com.dev2023.Entities.Product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String name;
    private String description;
    @Positive(message = "Valor NÃO dever negativo!")
    private Double price;
    private String url;
    @PastOrPresent(message = "Data do produto deve ser atual")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Getter
    private List<CategoriaDto> categoriaDtoList = new ArrayList<>();

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.url = product.getImg_url();
        this.date = product.getDate();
    }

    public ProductDto(Product product, Set<Category> categories) {
        this(product);
        categories.forEach(cat -> this.categoriaDtoList.add(new CategoriaDto(cat)));
    }
}