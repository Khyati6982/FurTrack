package com.clinic.furtrack.controller;

import com.clinic.furtrack.dto.ConsultationRequest;
import com.clinic.furtrack.model.Pet;
import com.clinic.furtrack.model.Owner;
import com.clinic.furtrack.model.Consultation;
import com.clinic.furtrack.repository.ConsultationRepository;
import com.clinic.furtrack.repository.PetRepository;
import com.clinic.furtrack.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {
	
	@Autowired
	private ConsultationRepository consultationRepository;
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	@GetMapping
	public List<Consultation> getAllConsultations() {
		return consultationRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Consultation> createConsultation(@RequestBody ConsultationRequest request) {
	    if (request.getPetId() != null && request.getOwnerId() != null) {
	        Pet pet = petRepository.findById(request.getPetId()).orElse(null);
	        Owner owner = ownerRepository.findById(request.getOwnerId()).orElse(null);

	        if (pet != null && owner != null) {
	            Consultation consultation = new Consultation();
	            consultation.setPet(pet);
	            consultation.setOwner(owner);
	            consultation.setFee(request.getFee());
	            consultation.setDate(request.getDate());

	            Consultation saved = consultationRepository.save(consultation);
	            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	        }
	    }
	    return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{id}")
	public Consultation getConsultationById(@PathVariable Long id) {
		return consultationRepository.findById(id).orElse(null);
	}
	
	@DeleteMapping("/{id}") 
	public void deleteConsultation(@PathVariable Long id) {
		consultationRepository.deleteById(id);
	}
}
