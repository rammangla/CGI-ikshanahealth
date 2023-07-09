package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.doctorgraph.Doctor;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends Neo4jRepository<Doctor, String> {

    @Query("MATCH (user: User {emailId: $emailId}) -[BOOKED]-> (doctor1: Doctor) -[r1:SPECIALIZED_IN]-> (s:Specialization) <-[r2:SPECIALIZED_IN]- (doctor2: Doctor) -[r3: LOCATED_IN]-> (dc: DoctorAddress) RETURN doctor2, collect(r2), collect(r3), collect(s), collect(dc)")
    public List<Doctor> getRecommendations(String emailId);
}

