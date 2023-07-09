package com.stackroute.doctorservice.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Main Doctor class
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Doctor {
	private String name;
	@Id
	private String emailId;
	private String password;
	private long phoneNumber;
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
	private String role;

	public void addAppointments(Appointments appointments) {
		this.appointments.add(appointments);
	}
}
