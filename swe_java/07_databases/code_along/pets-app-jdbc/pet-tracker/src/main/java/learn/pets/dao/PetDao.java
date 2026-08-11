package learn.pets.dao;

import learn.pets.models.Pet;

import java.util.List;

public interface PetDao {

    //BASIC CRUD FUNCTIONALITY

    List<Pet> findAll();
    Pet findPetById(int id);
    Pet add(Pet pet);
    Boolean update(Pet pet);
    Boolean deleteById(int id);

}
