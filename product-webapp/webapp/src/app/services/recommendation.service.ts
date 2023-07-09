import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecommendationService {

  constructor(private http: HttpClient) { }

  getDoctorRecommendations(emailId: string): Observable<any>{
    return this.http.get(`https://ikshanahealth.stackroute.io/recommendation-service/api/v1/recommendations/${emailId}`);
  }
}
