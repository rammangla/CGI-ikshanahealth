package com.stackroute.userauthservice.model.caretaker;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CaretakerDetails {
    private String hireName;
    private long phoneNumber;
    private CaretakerAddress caretakerAddress;
    private String gender;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dob;
}
