import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { DoctorAppointments } from 'src/app/models/doctor-appointments.model';
import { Doctor } from 'src/app/models/doctor.model';
import { DoctorProfileService } from 'src/app/services/doctor-profile.service';
/*import { MatDialog } from '@angular/material/dialog';*/
import { Router } from '@angular/router';
import { ChatComponent } from '../chat/chat.component'; 

@Component({
  selector: 'app-doctor-dashboard',
  templateUrl: './doctor-dashboard.component.html',
  styleUrls: ['./doctor-dashboard.component.css']
})
export class DoctorDashboardComponent implements OnInit {

  today = new Date();
  doctor: Doctor;
  earning: number;
  upcoming: DoctorAppointments[] = [];
  // recent: DoctorAppointments[] = [];
  previous: DoctorAppointments[] = [];
  emailId: string;
  error: string;
  patients: number;

  constructor(private doctorService: DoctorProfileService, private router: Router) { }

  ngOnInit(): void {
    this.emailId = localStorage.getItem('emailId');
    console.log(this.emailId);
    this.doctorService.getDoctorById(this.emailId).subscribe(
      (response) => {
        this.doctor = response;
        for (var appointment of this.doctor.appointments) {
          console.log(JSON.stringify(appointment));
          console.log(this.today);
          console.log(appointment.date);
          if (new Date(appointment.date) >= new Date()) {
            this.upcoming.push(appointment);
            console.log(this.upcoming);
            console.log(this.today);
            console.log(appointment);
          }
          else if (new Date(appointment.date) < new Date()) {
            this.previous.push(appointment);
            console.log(this.previous);
          }
          // else {
          //   this.recent.push(appointment);
          //   console.log(this.recent);
          // }
        }
        console.log(this.previous);
        console.log(this.upcoming);
      },
      (error) => {
        this.error = error;
      }
    );
    console.log(this.previous);
    console.log(this.upcoming);
  }


  Earning() {
    return this.earning = Number(750 * this.doctor.appointments.length);
  }

  Patients() {
    return this.patients = Number(this.doctor.appointments.length);
  }

  /*openDialog() {
    let dialogRef = this.dialog.open(ChatComponent, {
      height: '500px',
      width: '500px'
    });
  }*/

  startChat(phoneNumber: any, appDate: Date, slot: string, name: string) {
      let roomId = <string> phoneNumber + appDate + slot;
      console.log(roomId);
      this.router.navigate(['/chat', name, roomId]);
  }
}
