package com.stackroute.userauthservice.model.doctor;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;


import java.time.LocalDate;

@Data
public class Appointments  {
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
    private String slot;
    private PatientDetails patientDetails;
    private Integer appointmentId;
}
