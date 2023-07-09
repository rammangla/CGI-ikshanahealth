package com.stackroute.userauthservice.model.caretaker;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import java.time.LocalDate;


@Data
public class Appointments{
    /*private CaretakerDetails caretakerDetails;*/
    private String gender;
    private String userEmailId;
    private String caretakerEmailId;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
}
