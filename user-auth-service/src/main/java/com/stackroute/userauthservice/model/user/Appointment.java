package com.stackroute.userauthservice.model.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    private String doctorName;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
    private String clinicName;
    private DocAddress address;
    private String slot;
    private Integer appointmentId;
}
