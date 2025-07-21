package com.clinic.furtrack.repository;

import com.clinic.furtrack.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
	
}
