package com.stackroute.searchservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaretakerAppointments implements Serializable {
    //private String date;
}
