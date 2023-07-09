import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Doctor } from '../models/doctor.model';


@Injectable({
  providedIn: 'root'
})
export class DoctorProfileService {
 
  doctors: any = [];
  
  constructor(private http: HttpClient) {}

  errorHandlerDoctor(error: HttpErrorResponse): Observable<Doctor> {  // the return type required by lint but might give problem
    return throwError(error.message || 'Doctor not found');
  }


  addNewDoctor(doctor : Doctor): Observable<any> {
    // console.log(doctor.name);
    // {
    //   headers: new HttpHeaders({'Content-Type': 'application/json'}
    console.log(doctor.emailId);
    return this.http.post<Doctor>('https://ikshanahealth.stackroute.io/doctor-service/api/v1/addDoctor', doctor,{
         headers: new HttpHeaders({'Content-Type': 'application/json'})
        });
    }
  

  getDoctorById(emailId: string): Observable<Doctor> {
    return this.http.get<Doctor>(`https://ikshanahealth.stackroute.io/doctor-service/api/v1/doctors/${emailId}`).pipe(catchError(this.errorHandlerDoctor));
  }

  updateDoctor(doctor: Doctor): Observable<Doctor> {
    return this.http.put<Doctor>('https://ikshanahealth.stackroute.io/doctor-service/api/v1/doctors', doctor, {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    }).pipe(catchError(this.errorHandlerDoctor));
  }

  getDoctors(): Observable<Doctor> {
    return this.http.get<Doctor>(`https://ikshanahealth.stackroute.io/doctor-service/api/v1/doctors`).pipe(catchError(this.errorHandlerDoctor));
  }

  getDoctor(): void {
    this.http.get<Doctor[]>(`https://ikshanahealth.stackroute.io/doctor-service/api/v1/doctors`).subscribe((response: Doctor[]) => {this.doctors = response;
  });
  }

  getAllDoctors(): Observable<Doctor[]> {
    return this.http.get<Doctor[]>(`https://ikshanahealth.stackroute.io/doctor-service/api/v1/doctors`);
  }

}
