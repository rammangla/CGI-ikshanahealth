import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CaretakerAppointments } from 'src/app/models/caretaker-appointments.model';
// import { FormGroup } from '@angular/forms';
import { Caretaker } from 'src/app/models/caretaker.model';
import { User } from 'src/app/models/user.model';
import { CaretakerServiceService } from 'src/app/services/caretaker-service.service';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-caretaker-dashboard',
  templateUrl: './caretaker-dashboard.component.html',
  styleUrls: ['./caretaker-dashboard.component.css']
})
export class CaretakerDashboardComponent implements OnInit {
  today = new Date();
  caretaker: Caretaker;
  user:User;
  recent: CaretakerAppointments[] = [];
  error: any;
  emailId: string;
  hirer: number;
  earning: number;
  // userId: string ;
  caretakerAppointments : CaretakerAppointments = new CaretakerAppointments();

  constructor(private caretakerService:CaretakerServiceService, public dialog :MatDialog, private registerService:RegisterService) { }

  ngOnInit(): void {
    this.emailId = localStorage.getItem('emailId');
    // this.userId = this.caretakerAppointments.userEmailId;
    // console.log(this.userId)
    this.caretakerService.getCaretakerbyId(this.emailId).subscribe(
      (response) => {
        this.caretaker = response;
        this.recent = this.caretaker.bookedAppointments;
        /* for (const appointment of this.caretaker.bookedAppointments) {
          console.log(JSON.stringify(appointment));
          this.recent.push(appointment);
        } */
      },
      (error) => {
        this.error = error;
      }
    );
  }
  
  Earning() {
    console.log(this.caretaker.bookedAppointments.length);
    return this.earning = Number(750 * this.caretaker.bookedAppointments.length);
  }

  Hirer() {
    console.log(this.recent.length);
    return this.hirer = this.recent.length;
  }

  Event(userId: string){
    
    this.registerService.getUserById(userId).subscribe(
      (response) => {
        this.user = response;
      },
      (error) => {
        this.error = error;
      }
    );
    console.log(userId);
    // console.log(this.user);
    return this.user;  
  }

}

