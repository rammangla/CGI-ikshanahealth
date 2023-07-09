package com.stackroute.searchservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorAppointments implements Serializable {
    private String slot;
    private PatientDetails patientDetails;
}
