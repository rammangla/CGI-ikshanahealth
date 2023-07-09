package com.stackroute.doctorservice.model.bookappointment;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UserAppointment implements Serializable {
    private Doctor doctor;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
    private String clinicName;
    private Address address;
    private String slot;
    private Integer appointmentId;
}
