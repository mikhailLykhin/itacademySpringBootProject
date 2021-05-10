package com.itacademy.example.services;


import com.itacademy.example.dto.PetDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPetService {

     PetDto findPet(int id);

     List<PetDto> getPets();

     void createPet(PetDto user);

     void updatePet(int id, PetDto user);

     void deletePet(int id);

}
