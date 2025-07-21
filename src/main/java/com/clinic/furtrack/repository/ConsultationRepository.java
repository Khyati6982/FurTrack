package com.clinic.furtrack.repository;

import com.clinic.furtrack.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

}
