package com.dev2023.services;

import com.dev2023.Entities.Role;
import com.dev2023.Entities.User;
import com.dev2023.dto.RolerDto;
import com.dev2023.dto.UserDto;
import com.dev2023.repository.RoleRepository;
import com.dev2023.repository.UserRepository;
import com.dev2023.services.exception.DatabaseExption;
import com.dev2023.services.exception.ErroGeral;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Service
public class UserService  implements UserDetailsService {
    private  static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;
    @Autowired
    private BCryptPasswordEncoder  bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Transactional(readOnly = true)
    public Page<UserDto> findAllPaged(Pageable pageable){
        Page<User> list = repository.findAll(pageable);
        return list.map(x-> new UserDto(x,false));
    }
    @Transactional(readOnly = true)
    public UserDto findById(Integer id){
        Optional<User>RetornaUserId=repository.findById(id);
        User user = RetornaUserId.orElseThrow(()->new ErroGeral("Não Localizado! "));
        return  new UserDto(user,false);
    }
    @Transactional
    public UserDto insert(UserDto dto){
        User user = new User();
        GravaDto(dto,user);
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        user = repository.save(user);
        return new UserDto(user,true);
    }
    @Transactional
    public UserDto update( Integer id, UserDto dto){
        try{
           User user = repository.getOne(id);
            GravaDto(dto,user);
            user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
            return new UserDto(repository.save(user),false);
        }catch (EntityNotFoundException e){
           throw new  ErroGeral("Não Localizado: " + id);
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
    private void GravaDto(UserDto dto, User user) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());

        user.getRoles().clear();
        for (RolerDto roleDto : dto.getRoles()) {
            Role role = roleRepository.getOne(roleDto.getId());
            user.getRoles().add(role);
        }
   }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = repository.findByEmail(s);
         if(user == null ){
             logger.error("user Not found" + s);
             throw new UsernameNotFoundException("Email not Found");
         }
         logger.info("User found"+ s);
        return user;
    }
}