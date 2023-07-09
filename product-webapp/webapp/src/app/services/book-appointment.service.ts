import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BookAppointment } from '../models/book-appointment.model';

@Injectable({
  providedIn: 'root'
})
export class BookAppointmentService {

  constructor(private http: HttpClient) { }

  saveAppointments = (appointment: BookAppointment): Observable<any> => {
    console.log(appointment.id);
    return this.http.post<BookAppointment>('https://ikshanahealth.stackroute.io/book-appointment-service/api/v1/appointments', appointment, {
        headers: new HttpHeaders({'Content-Type': 'application/json'})
      });
  }
}
