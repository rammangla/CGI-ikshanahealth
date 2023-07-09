package com.stackroute.recommendationservice.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String plotNo;
    private String street;
    private String town;
    private String district;
    private String state;
    private long pincode;
}
