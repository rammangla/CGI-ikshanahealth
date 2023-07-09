import { Doctor } from './doctor.model';
import { Caretaker } from './caretaker.model';

export class SearchResponse {
    doctorList: Doctor[];
    caretakerList: Caretaker[];
}