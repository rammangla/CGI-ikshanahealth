package com.stackroute.doctorservice.model.bookappointment;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserAddress implements Serializable {
    private String plotNo;
    private String street;
    private String town;
    private String district;
    private String state;
    private long pincode;

}
