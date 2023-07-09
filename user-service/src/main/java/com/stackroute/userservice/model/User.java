package com.stackroute.userservice.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
* Main User Model class
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = User.class)
public class User {
	private String name;
	
	@Id
	private String emailId;
	
	private String password;
	private long phoneNumber;
	private LocalDate dob;
	private String gender;
	private Address address;
	private String idCard;
	private List<Appointment> appointments;
	private List<CaretakerAppointment> caretakerAppointments;
	private String role;

	public void addAppointments(Appointment appointment) {
		this.appointments.add(appointment);
	}
}
