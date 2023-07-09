package com.stackroute.searchservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

//Doctor Model
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Doctor.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Doctor implements Serializable {
	private String emailId;
	private String name;
	private long phoneNumber;
	private String gender;
	private String idCard;
	private String educationalQualification;
	private DoctorAddress address;
	private String specialization;
	private String licenseNumber;
	private String totalExperience;
	private String fees;
	private List<DoctorAppointments> appointments;

}
