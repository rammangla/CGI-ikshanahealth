package com.stackroute.recommendationservice.model.doctor;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDetails {
    private String patientName;
    private long phoneNumber;
    private String gender;
}
