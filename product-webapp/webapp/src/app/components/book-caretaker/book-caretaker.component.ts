import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { CaretakerAddress } from 'src/app/models/caretaker-address.model';
import { CaretakerAppointments } from 'src/app/models/caretaker-appointments.model';
import { Caretaker } from 'src/app/models/caretaker.model';
import { User } from 'src/app/models/user.model';
import { CaretakerServiceService } from 'src/app/services/caretaker-service.service';
import { DateCaretakerStorageService } from 'src/app/services/date-caretaker-storage.service';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-book-caretaker',
  templateUrl: './book-caretaker.component.html',
  styleUrls: ['./book-caretaker.component.css']
})
export class BookCaretakerComponent implements OnInit {

  id: number = 0;
  isSubmitted: boolean = false;
  date: Date = new Date();
  userEmail: string;
  user: User;
  caretaker: Caretaker;
  // caretaker: Caretaker = new Caretaker("Suryansh", "suryanshraj99@gmail.com", "srs@9999", new Date(),"male", new CaretakerAddress("B31", "ABC", "DEF", "GHI", "JKL", 221005), "ABCD1234", "21");
  errorMessage: string;
  availableSlots: CaretakerAppointments[] = [];
  bookedDates: CaretakerAppointments[] = [];
  private _subscription: Subscription;
  
  
  constructor( private getUser: RegisterService, private getCaretaker: CaretakerServiceService, private caretakerData: DateCaretakerStorageService) {
   }


  ngOnInit(): void {
    this.userEmail = localStorage.getItem('emailId');
    /*this._subscription = this.caretakerData.getCaretakerData().subscribe((caretaker: Caretaker)=>{
      this.caretaker=caretaker;
      console.warn(this.caretaker);
      localStorage.setItem('caretaker', JSON.stringify(this.caretaker));
    });*/
    this.caretaker = JSON.parse(localStorage.getItem('caretaker'));
    this.getUser.getUserById(this.userEmail).subscribe(
      (response) => {
        this.user = response;
        console.log(JSON.stringify(this.user));
        localStorage.setItem('user', JSON.stringify(this.user));
      },
      (error) => this.errorMessage = error
    );
    this.user = JSON.parse(localStorage.getItem('user'));
    console.log(JSON.stringify(this.user));
    this.availableSlots = this.caretaker.appointments;
    console.log(this.availableSlots);

  }

  onAdd(item: CaretakerAppointments) {
    this.isSubmitted = true;
    for (const slot of this.availableSlots) {
      if(slot === item) {
        item.userEmailId = this.user.emailId; // may give error
        this.bookedDates.push(item);
        //this.user.caretakerAppointments.push(item);
        //this.caretaker.bookedAppointments.push(item);
        //delete this.availableSlots[item.appointmentId];
      }
    }
    for (const item1 of this.bookedDates) {
      if(item === item1) {
        this.user.caretakerAppointments.push(item1);
        this.caretaker.bookedAppointments.push(item1);
      }
    }
   /*  this.user.caretakerAppointments = this.user.caretakerAppointments.concat(this.bookedDates);
    this.caretaker.bookedAppointments =  this.caretaker.bookedAppointments.concat(this.bookedDates); */
    console.log(JSON.stringify(this.user));
    console.log(JSON.stringify(this.caretaker));
  }

  onSubmit() {
    this.getUser.updateUser(this.user).subscribe(
      (response) => {
        this.user = response;
      },
      (error) => this.errorMessage = error
    );

    console.log(this.user);
    localStorage.removeItem('user');
  }

  onSubmita() {
    for (const item of this.caretaker.bookedAppointments) {
      for (const item1 of this.caretaker.appointments) {
        if(item === item1) {
          this.caretaker.appointments.splice(this.caretaker.appointments.indexOf(item), 1);
        }
      }
      
    }
    this.getCaretaker.updateCaretaker(this.caretaker).subscribe(
      (response) => this.caretaker = response,
      (error) => this.errorMessage = error
    );
    console.log(this.caretaker);
    localStorage.removeItem('caretaker');
  }

  actionMethod($event: MouseEvent) {
    ($event.currentTarget as HTMLButtonElement).disabled = true;
  }

}
