package com.stackroute.userauthservice.model.doctor;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Doctor.class)
public class Doctor {

    private String emailId;
    private String name;
    private String password;
    private String role;
    private long phoneNumber;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dob;
    private String gender;
    private String idCard;
    private String educationalQualification;
    private Address address;
    private String licenseNumber;
    private String specialization;
    private String totalExperience;
    private String fees;
    private List<Appointments> appointments;
    private List<String> facilities;

}
