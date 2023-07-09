import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { SearchResponse } from '../models/searchresponse.model';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
    constructor(private http: HttpClient) {}
    
    getlistsByLocation(location:string): Observable<SearchResponse>{
        return this.http.get<SearchResponse>(`https://ikshanahealth.stackroute.io/search-service/api/v1/search/${location}`);
    }
    getlistsBySpecializaion(location:string,specialization:string):Observable<SearchResponse>{
        return this.http.get<SearchResponse>(`https://ikshanahealth.stackroute.io/search-service/api/v1/search/${location}/${specialization}`);    
    }
    
}