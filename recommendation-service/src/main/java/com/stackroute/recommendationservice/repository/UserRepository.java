package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.doctorgraph.Doctor;
import com.stackroute.recommendationservice.model.usergraph.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends Neo4jRepository<User, String> {

    @Query("MATCH (user:User {emailId: $userEmail}) MATCH (doctor:Doctor {emailId: $doctorEmail}) CREATE (user)-[r:BOOKED]->(doctor)")
    public void createBookedRelationship(String userEmail, String doctorEmail);

    @Query("MATCH (user: User {emailId: $userEmail}) -[BOOKED]-> (doctor1: Doctor) -[r1:SPECIALIZED_IN]-> () <-[r2:SPECIALIZED_IN]- (doctor2: Doctor) RETURN doctor2")
    public List<?> getRecommendations(String userEmail);

    @Query("MATCH (user: User {emailId: $userEmail}) -[BOOKED]-> (doctor1: Doctor) -[r1:LOCATED_IN]-> () <-[r2:LOCATED_IN]- (doctor2: Doctor) RETURN doctor2")
    public List<?> getRecommendationsLocation(String userEmail);
}