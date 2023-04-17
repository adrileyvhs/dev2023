package com.dev2023.resources;

import com.dev2023.dto.UserDto;
import com.dev2023.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    @Autowired
    private UserService service;
    @GetMapping
    public ResponseEntity<Page<UserDto>> findAll(Pageable pageable)
    {
        Page<UserDto>list = service.findAllPaged(pageable);
      return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Integer id){
        UserDto UserDto = service.findById(id);
        return ResponseEntity.ok().body(UserDto);
     }
     @PostMapping
      public ResponseEntity<UserDto> Insert(@Valid @RequestBody UserDto dto){
          dto = service.insert(dto);
          URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(dto.getId()).toUri();
            return ResponseEntity.created(uri).body(dto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Integer id,  @Valid @RequestBody UserDto dto){
        dto = service.update(id,dto);
        return ResponseEntity.ok().body(dto);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id ){
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
