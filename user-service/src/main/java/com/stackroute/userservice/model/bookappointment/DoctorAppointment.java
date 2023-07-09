package com.stackroute.userservice.model.bookappointment;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class DoctorAppointment implements Serializable {
    private PatientDetails patientDetails;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
    private String slot;
    private Integer appointmentId;
}
