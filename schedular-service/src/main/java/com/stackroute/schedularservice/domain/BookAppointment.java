package com.stackroute.schedularservice.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class BookAppointment implements Serializable {
    @Id
    private String id;
    private Doctor doctor;
    private User user;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate appointmentDate;
    private Integer appointmentId;
    private String slot;

}
