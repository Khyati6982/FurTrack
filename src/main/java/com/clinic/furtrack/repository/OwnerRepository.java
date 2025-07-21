package com.clinic.furtrack.repository;

import com.clinic.furtrack.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
