package com.clinic.furtrack.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Consultation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;
	
	@Column(nullable = false)
	private String description;
	
	private double fee;
	private LocalDate date;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ConsultationStatus status = ConsultationStatus.SCHEDULED;
	
	@Column(name = "brought_by")
	private String broughtBy;
	
	public String getBroughtBy() {
		return broughtBy;
	}
	
	public void setBroughtBy(String broughtBy) {
		this.broughtBy = broughtBy;
	}
}
