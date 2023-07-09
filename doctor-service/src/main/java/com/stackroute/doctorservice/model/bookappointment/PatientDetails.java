package com.stackroute.doctorservice.model.bookappointment;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;


/**
 * Patient Details for Doctor Dashboard
 */
@Data
public class PatientDetails implements Serializable {

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dob;
    private String patientName;
    private long phone;
    private String gender;


}
