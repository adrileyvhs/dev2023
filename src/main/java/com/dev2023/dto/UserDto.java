package com.dev2023.dto;

import com.dev2023.Entities.User;
import com.dev2023.services.Validationi.UserInsertValid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@UserInsertValid
public class UserDto {
    private Integer id;
    @NotNull
    @NotBlank
    private String firstName;
    private String lastName;
    @NotNull
    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;
    private String password;
    @Getter
    Set<RolerDto> roles = new HashSet<>();

    public UserDto(User user, boolean S) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        phone = user.getPhone();
        if (S) {
            password = user.getPassword();
        }
        user.getRoles().forEach(role -> roles.add(new RolerDto(role)));
    }
}

