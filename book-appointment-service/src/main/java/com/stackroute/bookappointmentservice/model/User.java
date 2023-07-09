package com.stackroute.bookappointmentservice.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class User implements Serializable {
    private String name;

    @JsonSerialize(using = LocalDateSerializer.class)
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
