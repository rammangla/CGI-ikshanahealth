package com.stackroute.searchservice.repository;

import com.stackroute.searchservice.model.CaretakerEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

//Caretaker repository
@Repository
public interface CaretakerRepository extends ElasticsearchRepository<CaretakerEntity,String> {
    //@Query("{\"query\":{\"nested\":{\"path\":\"address\",\"query\":{\"bool\":{\"must\":[{\"match\":{\"address.town\":\"?0\"}}]}}}}}")
    List<CaretakerEntity> findByAddressTown(String addresstown);
}
