package com.clinic.furtrack.controller;

import com.clinic.furtrack.model.Consultation;
import com.clinic.furtrack.model.ConsultationStatus;
import com.clinic.furtrack.model.Owner;
import com.clinic.furtrack.model.Pet;
import com.clinic.furtrack.repository.ConsultationRepository;
import com.clinic.furtrack.repository.OwnerRepository;
import com.clinic.furtrack.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ConsultationViewController {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping("/consultations")
    public String viewConsultations(Model model) {
        List<Consultation> consultations = consultationRepository.findAll();
        model.addAttribute("consultations", consultations);
        return "consultations";
    }

    @GetMapping("/consultations/add")
    public String showAddConsultationForm(Model model) {
        model.addAttribute("pets", petRepository.findAll());
        model.addAttribute("owners", ownerRepository.findAll());
        return "add-consultation";
    }

    @PostMapping("/consultations/add")
    public String addConsultation(@RequestParam Long petId, @RequestParam Long ownerId, @RequestParam String description, @RequestParam double fee, @RequestParam ConsultationStatus status, @RequestParam(required = false) String broughtBy) {
        Pet pet = petRepository.findById(petId).orElse(null);
        Owner owner = ownerRepository.findById(ownerId).orElse(null);

        if (pet != null && owner != null) {
            Consultation consultation = new Consultation();
            consultation.setPet(pet);
            consultation.setOwner(owner);
            consultation.setDescription(description);
            consultation.setFee(fee);
            consultation.setStatus(status);
            consultation.setDate(LocalDate.now());
            consultation.setBroughtBy(broughtBy);
            consultationRepository.save(consultation);
        }

        return "redirect:/consultations";
    }

    @GetMapping("/consultations/edit/{id}")
    public String showEditConsultationForm(@PathVariable Long id, Model model) {
        Consultation consultation = consultationRepository.findById(id).orElse(null);
        model.addAttribute("consultation", consultation);
        model.addAttribute("statuses", ConsultationStatus.values());
        return "edit-consultation";
    }

    @PostMapping("/consultations/edit/{id}")
    public String updateConsultation(@PathVariable Long id, @RequestParam String description, @RequestParam double fee, @RequestParam ConsultationStatus status) {
        Consultation consultation = consultationRepository.findById(id).orElse(null);
        if (consultation != null) {
            consultation.setDescription(description);
            consultation.setFee(fee);
            consultation.setStatus(status);
            consultationRepository.save(consultation);
        }
        return "redirect:/consultations";
    }
    
    @GetMapping("/consultations/delete/{id}")
    public String deleteConsultation(@PathVariable Long id) {
        consultationRepository.deleteById(id);
        return "redirect:/consultations";
    }
}
