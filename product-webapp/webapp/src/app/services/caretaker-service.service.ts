import { Injectable } from '@angular/core';
import { Caretaker } from '../models/caretaker.model';
import { HttpClient, HttpHeaders, HttpErrorResponse} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
// import { FormControl, FormGroup, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class CaretakerServiceService {
caretakerss: any = [];

  caretakers: any = []; constructor(private http: HttpClient) { }

  errorHandler(error: HttpErrorResponse): Observable<Caretaker[]> {  // the return type required by lint but might give problem
    return throwError(error.message || 'Something went wrong');
  }
 
  errorHandlerCaretaker(error: HttpErrorResponse): Observable<Caretaker> {  // the return type required by lint but might give problem
    return throwError(error.message || 'Caretaker not found');
  }

  addCaretaker = (caretaker: Caretaker): Observable<any> => {
    console.log(caretaker);
    return this.http.post<Caretaker>('https://ikshanahealth.stackroute.io/caretaker-service/api/v1/caretakers', caretaker, {
        headers: new HttpHeaders({'Content-Type': 'application/json'})
      });
  }

  getCaretakers(): Observable<Caretaker[]> {
    return this.http.get<Caretaker[]>(`https://ikshanahealth.stackroute.io/caretaker-service/api/v1/caretakers`).pipe(catchError(this.errorHandler));
  }

  getCaretakerbyId(emailId: string): Observable<Caretaker> {
    return this.http.get<Caretaker>(`https://ikshanahealth.stackroute.io/caretaker-service/api/v1/caretakers/${emailId}`).pipe(catchError(this.errorHandlerCaretaker));
  }

  updateCaretaker(caretaker: Caretaker): Observable<Caretaker> {
    return this.http.put<Caretaker>('https://ikshanahealth.stackroute.io/caretaker-service/api/v1/caretakers', caretaker, {
      headers: new HttpHeaders({'Content-Type': 'application/json'})}).pipe(catchError(this.errorHandlerCaretaker));
  }

  getCaretaker(): void {
    this.http.get<Caretaker[]>('https://ikshanahealth.stackroute.io/caretaker-service/api/v1/caretakers').subscribe((response: Caretaker[]) => {this.caretakerss = response;
  });
  }
}
