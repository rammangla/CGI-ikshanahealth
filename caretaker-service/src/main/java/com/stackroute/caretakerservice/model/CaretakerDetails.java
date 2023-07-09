package com.stackroute.caretakerservice.model;

import lombok.Data;

import java.time.LocalDate;
@Data
public class CaretakerDetails {
    private String hireName;
    private long phoneNumber;
    private CaretakerAddress caretakerAddress;
    private String gender;
    private LocalDate dob;

}
