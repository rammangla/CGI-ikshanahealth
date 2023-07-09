package com.stackroute.userauthservice.model.caretaker;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Caretaker.class)
public class Caretaker  {

    private String emailId;
    private String name;
    private String password;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dob;
    private String gender;
    private CaretakerAddress address;
    private String idCard;
    private String totalExperience;
    private String fees;
    private long phoneNumber;
    private List<Appointments> appointments;
    private List<Appointments> bookedAppointments;
    private String specialization;
    private List<String> services;
    private String role;
}
