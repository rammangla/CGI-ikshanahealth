package com.stackroute.recommendationservice.model.doctorgraph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/*
 * Specialization class for Doctor's specialization
 */
@Node(labels = "Specialization")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Specialization {
    @Id
    private String speciality;
    private String totalExperience;
}
