package com.stackroute.doctorservice.model.bookappointment;

import lombok.Data;

import java.io.Serializable;

/**
 * Doctor Address
 */
@Data
public class Address implements Serializable {
	
	private String clinicName;
	private String plotNo;
	private String street;
	private String town;
	private String district;
	private String state;
	private long pincode;


}
