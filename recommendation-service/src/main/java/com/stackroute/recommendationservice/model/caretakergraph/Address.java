package com.stackroute.recommendationservice.model.caretakergraph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/*
 * Address class for Caretaker address
 */
@Node(labels = "CaretakerAddress")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    private String town;
    private String state;
}
