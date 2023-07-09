package com.stackroute.bookappointmentservice.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class DoctorAppointment implements Serializable {
    private PatientDetails patientDetails;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
    private String slot;
    private Integer appointmentId;
}
