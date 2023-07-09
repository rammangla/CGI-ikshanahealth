package com.stackroute.bookappointmentservice.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PatientDetails implements Serializable {
    private String patientName;
    private long phone;
    private String gender;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dob;
}
