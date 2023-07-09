package com.stackroute.doctorservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

/**
 * Appointment details for Doctor Dashboard
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Appointments {
    private LocalDate date;
    private String slot;
    private PatientDetails patientDetails;
    private Integer appointmentId;
}
