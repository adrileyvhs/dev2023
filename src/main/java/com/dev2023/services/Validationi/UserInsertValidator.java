package com.dev2023.services.Validationi;

import com.dev2023.Entities.User;
import com.dev2023.dto.UserDto;
import com.dev2023.repository.UserRepository;
import com.dev2023.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserDto> {

    @Autowired
    private UserRepository repository;
    @Override
    public void initialize(UserInsertValid ann) {
    }
    @Override
    public boolean isValid(UserDto dto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        // Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
        User user = repository.findByEmail(dto.getEmail());

        if(user!=null){
             list.add(new FieldMessage("Email","Ja existe"));
        } 
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFiledName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}