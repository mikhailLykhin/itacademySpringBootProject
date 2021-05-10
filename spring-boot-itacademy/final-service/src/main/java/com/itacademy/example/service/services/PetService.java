package com.itacademy.example.service.services;

import com.itacademy.example.dao.IPetDao;
import com.itacademy.example.dto.PetDto;
import com.itacademy.example.entity.Pet;
import com.itacademy.example.mappers.PetMapper;
import com.itacademy.example.services.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PetService implements IPetService {

    private final IPetDao petDao;

    @Autowired
    public PetService(IPetDao petDao) {
        this.petDao = petDao;
    }

    @Override
    public PetDto findPet(int id) {
        Pet pet = this.petDao.get(id);
        return PetMapper.mapPetDto(pet);
    }

    @Override
    public List<PetDto> getPets() {
       return PetMapper.mapAllPetsDto(this.petDao.getAll());

    }

    @Override
    public void createPet(PetDto pet) {
        Pet entity = PetMapper.mapPet(pet);
         this.petDao.create(entity);
    }

    @Override
    public void updatePet(int id, PetDto pet) {
        Pet entity = this.petDao.get(id);
        entity.setNickName(Optional.ofNullable(pet.getNickName()).orElse("I_hate_nickNames"));
        this.petDao.update(entity);
    }

    @Override
    public void deletePet(int id) {
        Pet entity = this.petDao.get(id);
        this.petDao.delete(entity);
    }
}
