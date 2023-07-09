package com.stackroute.userservice.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CaretakerAppointment {
    private LocalDate date;
    private String userEmailId;
    private String caretakerEmailId;
    private Integer appointmentId;
}
