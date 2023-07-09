package com.stackroute.caretakerservice.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class , property = "@id" , scope = Caretaker.class)
public class Caretaker {
    @Id
    private String emailId;
    private String name;
    private String password;
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

    public void addAppointments(Appointments appointments) {
        this.appointments.add(appointments);
    }
}
