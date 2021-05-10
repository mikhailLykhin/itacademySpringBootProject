package com.itacademy.example.mappers;

import com.itacademy.example.dto.PetDto;
import com.itacademy.example.entity.Pet;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

//@Mapper
@UtilityClass
public class PetMapper {

    public Pet mapPet(PetDto source) {
        return Pet.builder().id(source.getId()).nickName(source.getNickName()).build();

    }

    public PetDto mapPetDto(Pet source) {
        return PetDto.builder().id(source.getId()).nickName(source.getNickName()).build();

    }

    public List<Pet> mapAllPets(List<PetDto> source) {
        return source.stream().map(PetMapper::mapPet).collect(Collectors.toList());
    }

    public List<PetDto> mapAllPetsDto(List<Pet> source) {
        return source.stream().map(PetMapper::mapPetDto).collect(Collectors.toList());
    }
}
