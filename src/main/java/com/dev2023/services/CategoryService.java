package com.dev2023.services;
import com.dev2023.Entities.Category;
import com.dev2023.dto.CategoriaDto;
import com.dev2023.repository.CategoryRepository;
import com.dev2023.services.exception.DatabaseExption;
import com.dev2023.services.exception.ErroGeral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;
    @Transactional(readOnly = true)
    public Page<CategoriaDto> findAllPaged(Pageable pageable){
        Page<Category> list = repository.findAll(pageable);
       return list.map(x-> new CategoriaDto(x));
    }
    @Transactional(readOnly = true)
    public CategoriaDto findById(Integer id){
        //Optional<Category> entity = repository,findById(id);
         return new CategoriaDto(
                  repository.findById(id)
                  .orElseThrow(()-> new ErroGeral("Categoria Não localizada")),"n");
    }
     @Transactional
    public CategoriaDto insert(CategoriaDto dto) {
          Category category = new Category();
          category.setName(dto.getName());
          return new CategoriaDto(repository.save(category));
    }
    @Transactional
    public CategoriaDto update(Integer id, CategoriaDto dto) {
        try {
            Category category = repository.getOne(id);
            category.setName(dto.getName());
            return new CategoriaDto(repository.save(category));
        }catch (EntityNotFoundException e){
            throw new ErroGeral("Não existe essa Categoria " + id);
        }
    }
    public void delete(Integer id ) {
        try {
            repository.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            throw new ErroGeral("Não existe essa Categoria " + id);
        }
        catch (DatabaseExption e){
            throw new ErroGeral("Integrity Violation " + id);
        }
    }
}
