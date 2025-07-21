package com.clinic.furtrack.controller;

import com.clinic.furtrack.model.Owner;
import com.clinic.furtrack.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

	@Autowired
	private OwnerRepository ownerRepository;
	
	@GetMapping
	public List<Owner> getAllOwners() {
		return ownerRepository.findAll();
	}
	
	@PostMapping
	public Owner createOwner(@RequestBody Owner owner) {
		return ownerRepository.save(owner);
	}
	
	@GetMapping("/{id}")
	public Owner getOwnerById(@PathVariable Long id) {
		return ownerRepository.findById(id).orElse(null);
	}
	
	@PutMapping("/{id}")
	public Owner updateOwner(@PathVariable Long id, @RequestBody Owner ownerDetails) {
		Owner owner = ownerRepository.findById(id).orElse(null);
		if(owner != null) {
			owner.setName(ownerDetails.getName());
			owner.setContact(ownerDetails.getContact());
			return ownerRepository.save(owner);
		}
		return null;
	}
	
	@DeleteMapping("/{id}")
	public void deleteOwner(@PathVariable Long id) {
		ownerRepository.deleteById(id);
	}
}
