package com.stackroute.userservice.model.bookappointment;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class User implements Serializable {
    private String name;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dob;
    private String gender;
    private long phone;
    private String emailId;
    private String password;
    private Address address;
    private String role;
    private String clinicName;
    private String idCard;
    private List<UserAppointment> userAppointmentList;
}
