package com.stackroute.userservice.model;


import java.time.LocalDate;

import lombok.Data;

@Data
public class Appointment {
	private String doctorName;
	private LocalDate date;
	private String clinicName;
	private DocAddress address;
	private String slot;
	private Integer appointmentId;

}
