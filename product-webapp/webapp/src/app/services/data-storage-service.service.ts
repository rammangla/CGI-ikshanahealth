import { Injectable } from '@angular/core';
import { Observable,Subject } from 'rxjs';
import { Doctor } from '../models/doctor.model';
@Injectable({
  providedIn: 'root'
})
export class DataStorageServiceService {

  constructor() { }
  private _subject = new Subject<Doctor>( );

  sendDoctorData(doctor :Doctor)
  {
    this._subject.next(doctor);
  }

  getDoctorData() : Observable<Doctor> {
      return this._subject.asObservable();
  }
}
