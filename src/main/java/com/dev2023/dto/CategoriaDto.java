package com.dev2023.dto;

import com.dev2023.Entities.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDto {

    private Integer id;
    @NotNull
    private String name;
    @Temporal(TemporalType.DATE)
    private Instant updatedAt ;


    public  CategoriaDto(Category category){
        this.name=category.getName();
        this.id=category.getId();
    }
    public  CategoriaDto (Category categoryByid,String n){
         this.name=categoryByid.getName();
         this.id=categoryByid.getId();
    }
}
