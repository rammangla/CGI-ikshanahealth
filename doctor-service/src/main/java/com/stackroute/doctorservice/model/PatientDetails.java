package com.stackroute.doctorservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;


/**
 * Patient Details for Doctor Dashboard
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDetails {

    private String patientName;
    private long phoneNumber;
    private String gender;
    private LocalDate dob;

}
