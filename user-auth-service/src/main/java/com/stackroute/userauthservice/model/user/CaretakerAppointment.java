package com.stackroute.userauthservice.model.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.time.LocalDate;

public class CaretakerAppointment {
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
    private Integer appointmentId;
}
