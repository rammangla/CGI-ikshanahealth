package com.stackroute.bookappointmentservice.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = BookAppointment.class)
public class BookAppointment implements Serializable {
    @Id
    private String id;
    private Doctor doctor;
    private User user;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate appointmentDate;
    private Integer appointmentId;
    private String slot;
}
