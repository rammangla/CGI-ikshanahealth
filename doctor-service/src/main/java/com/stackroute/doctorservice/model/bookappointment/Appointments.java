package com.stackroute.doctorservice.model.bookappointment;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.stackroute.doctorservice.model.bookappointment.PatientDetails;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Appointment details for Doctor Dashboard
 */
@Data
public class Appointments implements Serializable {
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
    private String slot;
    private PatientDetails patientDetails;
    private Integer appointmentId;

}
