package com.stackroute.recommendationservice.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    private String doctorName;
    private String clinicName;
    private DocAddress address;
    private String slot;
}
