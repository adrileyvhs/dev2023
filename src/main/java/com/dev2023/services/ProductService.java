package com.dev2023.services;

import com.dev2023.Entities.Category;
import com.dev2023.Entities.Product;
import com.dev2023.dto.CategoriaDto;
import com.dev2023.dto.ProductDto;
import com.dev2023.repository.CategoryRepository;
import com.dev2023.repository.ProductRepository;
import com.dev2023.services.exception.DatabaseExption;
import com.dev2023.services.exception.ErroGeral;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Transactional(readOnly = true)
    public Page<ProductDto> findAllPaged(Pageable pageable){
        Page<Product> list = repository.findAll(pageable);
       return list.map(x-> new ProductDto(x));
    }
    @Transactional(readOnly = true)
    public ProductDto findById(Integer id){
           Optional<Product> product = repository.findById(id);
           Product entity = product.orElseThrow(()-> new ErroGeral("Não lizado"));
         return new ProductDto(entity,entity.getCategories());
    }
     @Transactional
    public ProductDto insert(ProductDto dto) {
            Product product = new Product();
            GravaDto(dto,product);
            return new ProductDto(repository.save(product));
    }
    @Transactional
    public ProductDto update(Integer id, ProductDto dto) {
        try {
            Product product = repository.getOne(id);
            GravaDto(dto,product);
            return new ProductDto(repository.save(product));

        }catch (EntityNotFoundException e){
            throw new ErroGeral("Não Existe:" + id);
        }
    }
    public void delete(Integer id ) {
        try {
            repository.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            throw new ErroGeral("Não Existe:" + id);
        }
        catch (DatabaseExption e){
            throw new ErroGeral("Integrity Violation:" + id);
        }
    }
    private void GravaDto(ProductDto dto, Product product) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setDate(dto.getDate());
        product.setImg_url(dto.getUrl());
        product.getCategories().clear();

   for(CategoriaDto categoriaDto:dto.getCategoriaDtoList()){
       Category category= categoryRepository.getOne(categoriaDto.getId());
      product.getCategories().add(category);
     }
   }
}
