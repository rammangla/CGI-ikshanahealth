package com.stackroute.searchservice.repository;

import com.stackroute.searchservice.model.DoctorEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

//Doctor Repository
@Repository
public interface DoctorRepository extends ElasticsearchRepository<DoctorEntity,String> {
    //@Query("{\"query\":{\"nested\":{\"path\":\"address\",\"query\":{\"bool\":{\"must\":[{\"match\":{\"address.town\":\"?0\"}}]}}}}}")
    List<DoctorEntity> findByAddressTownAndSpecialization(String addresstown, String specialization);

    List<DoctorEntity> findByAddressTown(String addresstown);
}
