package com.stackroute.userservice.model;

import lombok.Data;

@Data
public class Address {
	private String plotNo;
	private String street;
	private String town;
	private String district;
	private String state;
	private long pincode;


}
