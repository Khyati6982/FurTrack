package com.clinic.furtrack.controller;

import com.clinic.furtrack.model.Pet;
import com.clinic.furtrack.repository.PetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class PetViewController {

	@Autowired
	private PetRepository petRepository;
	
	@GetMapping("/pets")
	public String viewPets(Model model) {
		List<Pet> pets = petRepository.findAll();
		model.addAttribute("pets", pets);
		return "pets";
	}
	
	@GetMapping("/pets/add") 
	public String showAddPetForm() {
		return "add-pet";
	}
	
	@PostMapping("/pets/add")
	public String addPet(@RequestParam String name, @RequestParam String species, @RequestParam int age) {
	    Pet pet = new Pet();
	    pet.setName(name);
	    pet.setSpecies(species);
	    pet.setAge(age);
	    petRepository.save(pet);
	    return "redirect:/pets";
	}
	
	@GetMapping("/pets/edit/{id}")
	public String showEditPetForm(@PathVariable Long id, Model model) {
	    Pet pet = petRepository.findById(id).orElse(null);
	    model.addAttribute("pet", pet);
	    return "edit-pet";
	}
	
	@PostMapping("/pets/edit/{id}")
	public String updatePet(@PathVariable Long id, @RequestParam String name, @RequestParam String species, @RequestParam int age) {
		Pet pet = petRepository.findById(id).orElse(null);
		if(pet != null) {
			pet.setName(name);
			pet.setSpecies(species);
			pet.setAge(age);
			petRepository.save(pet);
			System.out.println("Updated Pet: " +pet);
		}
		return "redirect:/pets";
	}
	
	@GetMapping("/pets/delete/{id}")
	public String deletePet(@PathVariable Long id) {
		petRepository.deleteById(id);
		return "redirect:/pets";
	}
}
