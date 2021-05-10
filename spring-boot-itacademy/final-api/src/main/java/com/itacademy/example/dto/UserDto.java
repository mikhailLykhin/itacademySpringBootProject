package com.itacademy.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserDto {

    private int id;
    @Size(min = 5, message = "Не меньше 5 знаков")
    private String username;
    private int salary;
    @Size(min = 5, message = "Не меньше 5 знаков")
    private String password;
    private String passwordConfirm;
    private List<PetDto> pets;
}
