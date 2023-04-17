package com.dev2023.resources;
import com.dev2023.dto.CategoriaDto;
import com.dev2023.dto.ProductDto;
import com.dev2023.services.CategoryService;
import com.dev2023.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
    @Autowired
    private ProductService service;
    @GetMapping
    public ResponseEntity<Page<ProductDto>> findAll(Pageable pageable)
//                    @RequestParam(value = "page",defaultValue = "0") Integer page,
//                    @RequestParam(value = "linesPerPage",defaultValue = "12") Integer linesPerPage,
//                    @RequestParam(value = "direction",defaultValue = "ASC") String direction,
//                    @RequestParam(value = "orderBy",defaultValue = "name") String orderBy)
    {
          // PageRequest pageRequest= PageRequest.of(page,linesPerPage,Sort.Direction.valueOf(direction),orderBy);

           Page<ProductDto>list = service.findAllPaged(pageable);
      return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Integer id){
        ProductDto productDto = service.findById(id);
        return ResponseEntity.ok().body(productDto);
     }
     @PostMapping
      public ResponseEntity<ProductDto> Insert(@Valid  @RequestBody ProductDto dto){
          dto = service.insert(dto);
          URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(dto.getId()).toUri();
            return ResponseEntity.created(uri).body(dto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Integer id, @RequestBody  ProductDto dto){
        dto = service.update(id,dto);
        return ResponseEntity.ok().body(dto);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id ){
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
