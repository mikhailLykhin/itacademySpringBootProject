package com.itacademy.example.service.services;

import com.itacademy.example.dao.IPetDao;
import com.itacademy.example.dao.IUserDao;
import com.itacademy.example.dto.PetDto;
import com.itacademy.example.dto.PetDtoWrapper;
import com.itacademy.example.dto.UserDto;
import com.itacademy.example.dto.UserPetIdsDto;
import com.itacademy.example.entity.Pet;
import com.itacademy.example.entity.Role;
import com.itacademy.example.entity.User;
import com.itacademy.example.mappers.UserMapper;
import com.itacademy.example.service.utils.LogoFileUploader;
import com.itacademy.example.services.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UserService implements IUserService {

    private final IUserDao userDao;
    private final IPetDao petDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(IUserDao userDao, IPetDao petDao,
                       BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager) {
        this.userDao = userDao;
        this.petDao = petDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    @Transactional
    public UserDto findUser(int id) {
        User user = this.userDao.get(id);
        return UserMapper.mapUserDto(user);
    }

    @Override
    @Transactional
    public UserDto findByName(String name) {
        User user = this.userDao.getByName(name);
        return UserMapper.mapUserDto(user);
    }

    @Override
    @Transactional
    public List<UserDto> getUsers() {
        return UserMapper.mapAllUsersDto(this.userDao.getAll());

    }

    @Override
    @Transactional
    public boolean createUser(UserDto user, MultipartFile file) {
        User userFromDB = this.userDao.getByName(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        try {
            LogoFileUploader.updateOrCreateLogo(file, user);
        } catch (IOException e) {
            log.error("Failed to upload image. Error message: {}", e.getMessage());
        }
        User entity = UserMapper.mapUser(user);
        entity.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
        entity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.userDao.create(entity);

        return true;
    }

    @Override
    @Transactional
    public boolean updateUser(Principal principal, UserDto user, MultipartFile file) {
        User entity = this.userDao.getByName(principal.getName());
        if (!bCryptPasswordEncoder.matches(user.getPasswordConfirm(), entity.getPassword())) {
            return false;
        }
        entity.setUsername(Optional.ofNullable(user.getUsername()).orElse("I_hate_names"));
        entity.setSalary(user.getSalary());
        entity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        entity.setPasswordConfirm(bCryptPasswordEncoder.encode(user.getPassword()));
        this.userDao.update(entity);
        try {
            LogoFileUploader.updateOrCreateLogo(file, user);
        } catch (IOException e) {
            log.error("Failed to upload image. Error message: {}", e.getMessage());
        }
        return true;
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        User entity = this.userDao.get(id);
        this.userDao.delete(entity);
        LogoFileUploader.deleteLogo(entity.getUsername());
    }

//    @Override
//    @Transactional
//    public void assignPetToUser(int userId, PetDto petDto) {
//        User user = this.userDao.get(userId);
//        Pet pet = this.petDao.get(petDto.getId());
//        pet.setUser(user);
//        this.petDao.update(pet);
//
//    }

    @Override
    @Transactional
    public void assignPetToUser(UserPetIdsDto ids) {
        User user = this.userDao.get(ids.getUserId());
        Pet pet = this.petDao.get(ids.getPetId());
        pet.setUser(user);
        this.petDao.update(pet);

    }

    @Override
    @Transactional
    public void assignAllPetsToUser(int userId, PetDtoWrapper petsDto) {
        User user = this.userDao.get(userId);
        List<Pet> pets = new ArrayList<>();
        for (PetDto petDto : petsDto.getPets()) {
            Pet pet = this.petDao.get(petDto.getId());
            pets.add(pet);
        }
        user.setPets(pets);
        this.userDao.update(user);
    }


}
