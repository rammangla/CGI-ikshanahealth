package com.stackroute.schedularservice.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PatientDetails implements Serializable {
    private String patientName;
    private long phone;
    private String gender;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dob;
}

