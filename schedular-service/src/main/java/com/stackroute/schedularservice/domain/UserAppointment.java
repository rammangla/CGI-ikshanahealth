package com.stackroute.schedularservice.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UserAppointment implements Serializable {
    private Doctor doctor;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
    private String clinicName;
    private DocAddress address;
    private String slot;
    private Integer appointmentId;
}

