import { Injectable } from '@angular/core';
import { Observable,Subject } from 'rxjs';
import { Caretaker } from '../models/caretaker.model';

@Injectable({
  providedIn: 'root'
})
export class DateCaretakerStorageService {

  constructor() { }
  private _subject = new Subject<Caretaker>( );
  sendCaretakerData(caretaker :Caretaker)
  {
    this._subject.next(caretaker);
  }

  getCaretakerData() : Observable<Caretaker> {
      return this._subject.asObservable();
  }

}
