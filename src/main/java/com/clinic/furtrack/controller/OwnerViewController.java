package com.clinic.furtrack.controller;

import com.clinic.furtrack.model.Owner;
import com.clinic.furtrack.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OwnerViewController {

	@Autowired
	private OwnerRepository ownerRepository;
	
	@GetMapping("/owners")
	public String viewOwners(Model model) {
		List<Owner> owners = ownerRepository.findAll();
		model.addAttribute("owners", owners);
		return "owners";
	}
	
	@GetMapping("/owners/add")
	public String showAddOwnerForm() {
		return "add-owner";
	}
	
	@PostMapping("/owners/add")
	public String addOwner(@RequestParam String name, @RequestParam String contact) {
		Owner owner = new Owner();
		owner.setName(name);
		owner.setContact(contact);
		ownerRepository.save(owner);
		return "redirect:/owners";
	}
	
	@GetMapping("/owners/edit/{id}") 
	public String showEditOwnerForm(@PathVariable Long id, Model model) {
		Owner owner = ownerRepository.findById(id).orElse(null);
		model.addAttribute("owner", owner);
		return "edit-owner";
	}
	
	@PostMapping("/owners/edit/{id}")
	public String updateOwner(@PathVariable Long id, @RequestParam String name, @RequestParam String contact) {
		Owner owner = ownerRepository.findById(id).orElse(null);
		if(owner != null) {
			owner.setName(name);
			owner.setContact(contact);
			ownerRepository.save(owner);
		}
		return "redirect:/owners";
	}
	
	@GetMapping("/owners/delete/{id}")
	public String deleteOwner(@PathVariable Long id) {
		ownerRepository.deleteById(id);
		return "redirect:/owners";
	}
}
