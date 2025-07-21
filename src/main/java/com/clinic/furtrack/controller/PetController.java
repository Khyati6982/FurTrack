package com.clinic.furtrack.controller;

import com.clinic.furtrack.model.Pet;
import com.clinic.furtrack.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pets")
public class PetController {
	
	@Autowired
	private PetRepository petRepository;
	
	@GetMapping
	public List<Pet> getAllPets() {
		return petRepository.findAll();
	}
	
	@PostMapping
	public Pet createPet(@RequestBody Pet pet) {
		return petRepository.save(pet);
	}
	
	@GetMapping("/{id}")
	public Pet getPetById(@PathVariable Long id) {
		return petRepository.findById(id).orElse(null);
	}
	
	@PutMapping("/{id}")
	public Pet updatePet(@PathVariable Long id, @RequestBody Pet petDetails) {
		Pet pet = petRepository.findById(id).orElse(null);
		if(pet != null) {
			pet.setName(petDetails.getName());
			pet.setSpecies(petDetails.getSpecies());
			pet.setAge(petDetails.getAge());
			return petRepository.save(pet);
		}
		return null;
	}
	
	@DeleteMapping("/{id}")
	public void deletePet(@PathVariable Long id) {
		petRepository.deleteById(id);
	}
}
