package com.stackroute.bookappointmentservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Address implements Serializable {
    private String plotNo;
    private String street;
    private String town;
    private String district;
    private String state;
    private long pincode;
}
