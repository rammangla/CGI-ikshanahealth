package com.stackroute.searchservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

//Caretaker Model
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Caretaker.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Caretaker implements Serializable {
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
	private String specialization;
	private String role;
}
