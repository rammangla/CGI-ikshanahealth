package com.stackroute.caretakerservice.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Appointments {
    private LocalDate date;
    private String userEmailId;
    private String caretakerEmailId;
    /*private CaretakerDetails caretakerDetails;*/
    private Integer appointmentId;
}
