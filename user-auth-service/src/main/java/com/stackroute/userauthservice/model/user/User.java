package com.stackroute.userauthservice.model.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = User.class)
public class User{

    private String emailId;
    private String name;
    private String password;
    private String phoneNumber;
    private String gender;
    private Address address;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dob;
    private List<Appointment> appointments;
    private List<CaretakerAppointment> caretakerAppointments;
    private String idCard;
    private String role;

}
