package com.stackroute.searchservice.service;

import com.stackroute.searchservice.exchange.SearchResponse;
import com.stackroute.searchservice.model.Caretaker;
import com.stackroute.searchservice.model.CaretakerEntity;
import com.stackroute.searchservice.model.Doctor;
import com.stackroute.searchservice.model.DoctorEntity;
import com.stackroute.searchservice.repository.CaretakerRepository;
import com.stackroute.searchservice.repository.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Provider;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    CaretakerRepository caretakerRepository;
    @Autowired
    Provider<ModelMapper> modelMapperProvider;

    //Method to save the Doctor object from rabbitmq to Elastic search by flattening it out to Doctor Entity using Mapper
    @Override
    public void saveDoctor(Doctor doctor) {
        ModelMapper modelMapper = modelMapperProvider.get();
        DoctorEntity doctorEntity = modelMapper.map(doctor,DoctorEntity.class);
        doctorRepository.save(doctorEntity);
    }
    //Method to save the Caretaker object from rabbitmq to Elastic search by flattening it out to Caretaker Entity using Mapper
    @Override
    public void saveCaretaker(Caretaker caretaker) {
        ModelMapper modelMapper = modelMapperProvider.get();
        CaretakerEntity caretakerEntity = modelMapper.map(caretaker,CaretakerEntity.class);
        caretakerRepository.save(caretakerEntity);
    }
    //Method to return the Doctor Entity from Elastic search to Endpoint by converting it back to Doctor object using Mapper
    @Override
    public SearchResponse searchByLocation(String addresstown){
        List<DoctorEntity> doctorEntityList = doctorRepository.findByAddressTown(addresstown);
        SearchResponse searchResponse = new SearchResponse();
        ModelMapper modelMapper = modelMapperProvider.get();
        List<Doctor> doctorList = new ArrayList<>();
        for (DoctorEntity doctorEntity : doctorEntityList
             ) {
            doctorList.add(modelMapper.map(doctorEntity,Doctor.class));
        }
        List<CaretakerEntity> caretakerEntityList =caretakerRepository.findByAddressTown(addresstown);
        List<Caretaker> caretakerList = new ArrayList<>();
        for (CaretakerEntity caretakerEntity : caretakerEntityList
             ) {
            caretakerList.add(modelMapper.map(caretakerEntity,Caretaker.class));
        }
        searchResponse.setDoctorList(doctorList);
        searchResponse.setCaretakerList(caretakerList);
        return searchResponse;
    }
    //Method to return the Caretaker Entity from Elastic search to Endpoint by converting it back to Caretaker object using Mapper
    @Override
    public SearchResponse searchByTownAndSpecialization(String addresstown,String specialization) {
        List<DoctorEntity> doctorEntityList = doctorRepository.findByAddressTownAndSpecialization(addresstown,specialization);
        SearchResponse searchResponse = new SearchResponse();
        ModelMapper modelMapper = modelMapperProvider.get();
        List<Doctor> doctorList = new ArrayList<>();
        for (DoctorEntity doctorEntity : doctorEntityList
        ) {
            doctorList.add(modelMapper.map(doctorEntity,Doctor.class));
        }
        List<CaretakerEntity> caretakerEntityList =caretakerRepository.findByAddressTown(addresstown);
        List<Caretaker> caretakerList = new ArrayList<>();
        for (CaretakerEntity caretakerEntity : caretakerEntityList
        ) {
            caretakerList.add(modelMapper.map(caretakerEntity,Caretaker.class));
        }
        searchResponse.setDoctorList(doctorList);
        searchResponse.setCaretakerList(caretakerList);
        return searchResponse;
    }

}
