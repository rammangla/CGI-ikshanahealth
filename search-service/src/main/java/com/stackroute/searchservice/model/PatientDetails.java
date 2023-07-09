package com.stackroute.searchservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;


/**
 * Patient Details for Doctor Dashboard
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDetails implements Serializable {

    private String patientName;
    private long phoneNumber;
    private String gender;
    private LocalDate dob;

}
