package com.clinic.furtrack.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

@Data
public class ConsultationRequest {
    private Long petId;
    private Long ownerId;
    private double fee;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
