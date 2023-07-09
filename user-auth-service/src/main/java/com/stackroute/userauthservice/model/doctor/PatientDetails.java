package com.stackroute.userauthservice.model.doctor;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;


import java.time.LocalDate;

@Data
public class PatientDetails   {
    private String patientName;
    private long phoneNumber;
    private String gender;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dob;

}
