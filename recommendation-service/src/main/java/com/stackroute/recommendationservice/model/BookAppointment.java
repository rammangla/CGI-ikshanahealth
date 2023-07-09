package com.stackroute.recommendationservice.model;

import com.stackroute.recommendationservice.model.doctor.Doctor;
import com.stackroute.recommendationservice.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BookAppointment implements Serializable {

    @Id
    private String id;
    private Doctor doctor;
    private User user;
    private Integer appointmentId;
    private String slot;

}
