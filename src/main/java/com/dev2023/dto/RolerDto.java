package com.dev2023.dto;

import com.dev2023.Entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
public class RolerDto {
    @Setter
    @Getter
    private Long id;
    @Setter
    @Getter
    private String authority;

    public RolerDto(Role role) {
        id = role.getId();
        authority = role.getAuthority();
    }
}