package com.stackroute.doctorservice.model.bookappointment;


import com.stackroute.doctorservice.model.bookappointment.Address;
import com.stackroute.doctorservice.model.bookappointment.Appointments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Main Doctor class
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Doctor  implements Serializable {

	@Id
	private String emailId;
	private String role;
	private String name;
	private String gender;
	private long phone;
	private String licenseNo;
	private String qualification;
	private String specialization;
	private String totalExperience;
	private Address address;
	private String password;
	private String slot;
	private List<Appointments> doctorAppointmentList;


}
