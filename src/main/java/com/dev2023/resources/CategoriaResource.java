package com.dev2023.resources;
import com.dev2023.Entities.Category;
import com.dev2023.dto.CategoriaDto;
import com.dev2023.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
@RestController
@RequestMapping(value = "/categories")
public class CategoriaResource {
    @Autowired
    private CategoryService service;
    @GetMapping
    public ResponseEntity<Page<CategoriaDto>> findAll(Pageable pageable)
    {
        Page<CategoriaDto>list = service.findAllPaged(pageable);
      return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoriaDto> findById(@PathVariable Integer id){
        CategoriaDto categoriaDto = service.findById(id);
        return ResponseEntity.ok().body(categoriaDto);
     }
     @PostMapping
      public ResponseEntity<CategoriaDto> Insert(@Valid @RequestBody CategoriaDto dto){
          dto = service.insert(dto);
          URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(dto.getId()).toUri();
            return ResponseEntity.created(uri).body(dto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoriaDto> update(@PathVariable Integer id, @RequestBody  CategoriaDto dto){
        dto = service.update(id,dto);
        return ResponseEntity.ok().body(dto);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id ){
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
