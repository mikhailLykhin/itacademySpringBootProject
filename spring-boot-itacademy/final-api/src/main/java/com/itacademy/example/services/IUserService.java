package com.itacademy.example.services;

import com.itacademy.example.dto.PetDtoWrapper;
import com.itacademy.example.dto.UserDto;
import com.itacademy.example.dto.UserPetIdsDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Service
public interface IUserService {

     UserDto findUser(int id);

     UserDto findByName(String name);

     List<UserDto> getUsers();

     boolean createUser(UserDto user, MultipartFile file);

     boolean updateUser(Principal principal, UserDto user, MultipartFile file);

     void deleteUser(int id);

//     void assignPetToUser(int userId, PetDto pet);
     void assignPetToUser(UserPetIdsDto ids);

     void assignAllPetsToUser(int userId, PetDtoWrapper pets);

}
