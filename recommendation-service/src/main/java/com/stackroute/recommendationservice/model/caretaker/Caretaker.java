package com.stackroute.recommendationservice.model.caretaker;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Caretaker.class)
public class Caretaker {
    private String emailId;
    private String name;
    private String password;
    private String gender;
    private CaretakerAddress address;
    private String idCard;
    private String totalExperience;
    private String fees;
    private long phoneNumber;
    private List<Appointments> appointments;
}
