package com.inovoseltsev.lightdev.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
