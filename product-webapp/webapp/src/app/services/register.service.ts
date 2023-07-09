import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { User } from '../models/user.model';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  userss: any = [];

  constructor(private http: HttpClient) { }

  registerUser = (user: User): Observable<any> => {
    console.log(user.name);
    return this.http.post<User>('https://ikshanahealth.stackroute.io/user-service/api/v1/users', user, {
        headers: new HttpHeaders({'Content-Type': 'application/json'})
      });
  }

  getUserById(emailId: string): Observable<User> {
    return this.http.get<User>(`https://ikshanahealth.stackroute.io/user-service/api/v1/users/${emailId}`).pipe(catchError(this.errorHandlerUser));
  }

  updateUser(user: User): Observable<User> {
    console.log(user.caretakerAppointments);
    return this.http.put<User>('https://ikshanahealth.stackroute.io/user-service/api/v1/users', user, {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    }).pipe(catchError(this.errorHandlerUser));
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`https://ikshanahealth.stackroute.io/user-service/api/v1/users`).pipe(catchError(this.errorHandler));
  }

  getUser(): void {
    this.http.get<User[]>('https://ikshanahealth.stackroute.io/user-service/api/v1/users').subscribe((response: User[]) => {this.userss = response;
  });
  }

  errorHandler(error: HttpErrorResponse): Observable<User[]> {  // the return type required by lint but might give problem
    return throwError(error.message || 'Something went wrong');
  }

  errorHandlerUser(error: HttpErrorResponse): Observable<User> {  // the return type required by lint but might give problem
    return throwError(error.message || 'Something went wrong');
  }
}


