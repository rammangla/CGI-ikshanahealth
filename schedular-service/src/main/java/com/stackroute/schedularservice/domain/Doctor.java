package com.stackroute.schedularservice.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Doctor implements Serializable {
    private String emailId;
    private String role;
    private String name;
    private String gender;
    private long phone;
    private String licenseNo;
    private String qualification;
    private String specialization;
    private String totalExperience;
    private DocAddress address;
    private String password;
    private String slot;
    private List<DoctorAppointment> doctorAppointmentList;
}

