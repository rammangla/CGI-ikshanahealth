package com.stackroute.searchservice.exchange;

import com.stackroute.searchservice.model.Caretaker;
import com.stackroute.searchservice.model.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

//Model of Search response i.e type of output at endpoint
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponse {
    List<Doctor> doctorList;
    List<Caretaker> caretakerList;
}
