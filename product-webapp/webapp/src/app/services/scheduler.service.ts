import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Scheduler } from '../models/scheduler.model';

@Injectable({
  providedIn: 'root'
})
export class SchedulerService {

  constructor(private http: HttpClient) { }

  getSlotsById(emailId: string): Observable<Scheduler> {
    return this.http.get<Scheduler>(`https://ikshanahealth.stackroute.io/schedular-service/api/v1/slots/${emailId}`).pipe(catchError(this.errorHandlerUser));
  }

  errorHandlerUser(error: HttpErrorResponse): Observable<Scheduler> {  // the return type required by lint but might give problem
    return throwError(error.message || 'Something went wrong');
  }
}
