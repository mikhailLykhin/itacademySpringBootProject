package com.itacademy.example.dao;

import com.itacademy.example.entity.Pet;
import org.springframework.stereotype.Repository;

@Repository
public class PetDao extends AGenericDao<Pet> implements IPetDao {
    public PetDao() {
        super(Pet.class);
    }
}
