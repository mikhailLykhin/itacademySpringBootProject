package com.itacademy.example.rest.controllers;

import com.itacademy.example.dto.PetDto;
import com.itacademy.example.services.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final IPetService petService;

    @Autowired
    public PetController(IPetService petService) {
        this.petService = petService;
    }

    @GetMapping("/{id}")
    @Transactional
    public PetDto findPet(@PathVariable int id) {
        return this.petService.findPet(id);
    }

    @GetMapping()
    @Transactional
    public List<PetDto> getPets() {
        return this.petService.getPets();
    }

    @PostMapping
    @Transactional
    public void createPet(@RequestBody PetDto pet) {
         this.petService.createPet(pet);
    }

    @PutMapping("/{id}")
    @Transactional
    public void updatePet(@PathVariable int id,@RequestBody PetDto pet) {
        this.petService.updatePet(id, pet);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void updatePet(@PathVariable int id) {
        this.petService.deletePet(id);
    }
}
