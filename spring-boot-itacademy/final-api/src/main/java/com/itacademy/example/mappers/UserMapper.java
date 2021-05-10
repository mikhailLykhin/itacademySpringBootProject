package com.itacademy.example.mappers;

import com.itacademy.example.dto.UserDto;
import com.itacademy.example.entity.User;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

//@Mapper
@UtilityClass
public class UserMapper {

    public User mapUser(UserDto source) {
        return User.builder()
                .id(source.getId())
                .username(source.getUsername())
                .salary(source.getSalary())
                .password(source.getPassword())
                .passwordConfirm(source.getPasswordConfirm())
                .build();

    }

    public UserDto mapUserDto(User source) {
        return UserDto.builder()
                .id(source.getId())
                .username(source.getUsername())
                .salary(source.getSalary())
                .password(source.getPassword())
                .passwordConfirm(source.getPasswordConfirm())
                .pets(PetMapper.mapAllPetsDto(source.getPets()))
                .build();

    }

    public List<User> mapAllUsers(List<UserDto> source) {
        return source.stream().map(UserMapper::mapUser).collect(Collectors.toList());
    }

    public List<UserDto> mapAllUsersDto(List<User> source) {
        return source.stream().map(UserMapper::mapUserDto).collect(Collectors.toList());
    }
}
