package com.stackroute.doctorservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Doctor Address
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
	
	private String clinicName;
	private String plotNo;
	private String street;
	private String town;
	private String district;
	private String state;
	private long pinCode;


}
