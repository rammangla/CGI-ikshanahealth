package com.stackroute.recommendationservice.model.doctor;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Appointments {
    private String slot;
    private PatientDetails patientDetails;
}
