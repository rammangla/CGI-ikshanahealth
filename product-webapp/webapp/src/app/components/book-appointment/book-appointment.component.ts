import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { Appointment } from 'src/app/models/appointment.model';
import { BookAppointment } from 'src/app/models/book-appointment.model';
import { Doctor } from 'src/app/models/doctor.model';
import { Scheduler } from 'src/app/models/scheduler.model';
import { User } from 'src/app/models/user.model';
import { BookAppointmentService } from 'src/app/services/book-appointment.service';
import { DataStorageServiceService } from 'src/app/services/data-storage-service.service';
import { RegisterService } from 'src/app/services/register.service';
import { SchedulerService } from 'src/app/services/scheduler.service';
import { Pay2Component } from '../pay2/pay2.component';

@Component({
  selector: 'app-book-bookAppointment',
  templateUrl: './book-appointment.component.html',
  styleUrls: ['./book-appointment.component.css']
})
export class BookAppointmentComponent implements OnInit {
  private _subscription: Subscription;
  id: number = 0;
  form: FormGroup;
  isSubmitted: boolean = false;
  slotSubmitted: boolean = false;
  selectedDate: Date;
  date: Date = new Date();
  day: string;
  slotMap = new Map();
  todayMap = new Map();
  tomorrowMap = new Map();
  overmorrowMap = new Map();
  userEmail: string;
  user: User;
  doctor: Doctor;/* = new Doctor("Yash Singhal","singhal.yash@max.com",new Date("05/19/1998"),"male", "passport",
  "MD", "ABCD1234ABCD", "21", 1234567890); */ 
  appointmentObj: Appointment;
  //doctorEmail: string = "srs@gmail.com";
  errorMessage: string;
  selectedSlot: string;
  bookAppointment: BookAppointment;
  availableSlots: Scheduler;
  bookingId: string;
  Istrue: boolean;
  
  
  constructor(private fb: FormBuilder, private getUser: RegisterService, private appointment: BookAppointmentService, private scheduler: SchedulerService,private dataStorageService: DataStorageServiceService, private dialog: MatDialog) {
    this.userEmail = localStorage.getItem('emailId');
   }

  ngOnInit(): void {
     
   /* this._subscription = this.dataStorageService.getDoctorData().subscribe((doctor:Doctor)=>{
      this.doctor=doctor;
      console.warn(this.doctor);
      localStorage.setItem('doctor', JSON.stringify(this.doctor));
    });*/
    localStorage.setItem('bookingId', '0');
    this.doctor = JSON.parse(localStorage.getItem('doctor'));
    this.scheduler.getSlotsById(this.doctor.emailId).subscribe(
      (response) => {
        this.availableSlots = response;
        this.slotMap = this.availableSlots.slots;
        for (const [key, value] of Object.entries(this.slotMap)) { 
          console.log(key, value);
          if(key.substring(0,5)==="today" && value === "Available") {
            this.todayMap.set(key, value)
          }
          if(key.substring(0,8)==="tomorrow" && value === "Available") {
            this.tomorrowMap.set(key, value)
          }
          if(key.substring(0,10)==="overmorrow" && value === "Available") {
            this.overmorrowMap.set(key, value)
          }
      }
      
      }
    );
    this.form = this.fb.group({
      appDate: new FormControl('', [Validators.required ])
    });
  }

  get dobInput(): any { return this.form.get('appDate'); }

  onSubmit() {
    if (this.form.valid)  {
      this.isSubmitted = true;
      this.selectedDate = this.form.get('appDate').value;
      console.log(this.selectedDate);
      if(this.selectedDate.getDate() === new Date().getDate()) {
        this.day = "today";
      }

      if(this.selectedDate.getDate() === new Date().getDate()+1) {
        this.day = "tomorrow";
      }

      if(this.selectedDate.getDate() === new Date().getDate()+2) {
        this.day = "overmorrow";
      }
      this.getUser.getUserById(this.userEmail).subscribe(
        (response) => {
          this.user = response;
        },
        (error) => this.errorMessage = error
      );
    }
    else {
      this.isSubmitted = false;
      window.alert("Enter a valid date");
    }
  }

  OnslotSubmit(value: Array<string>) {
    this.slotSubmitted = true;
    console.log(this.user.name);
    this.selectedSlot = value[1];
    console.log(this.selectedSlot);
    console.log(this.selectedDate);
    //this.doctor.emailId = this.doctorEmail; //to be removed
    let dialogRef=this.dialog.open(Pay2Component,{height:'80%',width:'40%'});
       dialogRef.afterClosed().subscribe(
          (data)=> {this.Istrue=data;
    
    if(this.Istrue) {
      this.selectedDate.setDate( this.selectedDate.getDate()+1);
      this.appointmentObj = new Appointment(this.doctor.name, this.selectedDate, <string>this.doctor.address.clinicName, this.doctor.address, value[1], this.id);
      this.user.appointments.push(this.appointmentObj);
      /*this.id = parseInt(localStorage.getItem('bookingId'));*/
      ++this.id;
      /*localStorage.setItem("bookingId", `${this.id}`);*/
      this.bookingId = this.userEmail+this.selectedDate+this.selectedSlot;
      this.bookAppointment = new BookAppointment(this.bookingId, this.doctor, this.user, this.selectedDate, parseInt(this.bookingId), this.selectedSlot);
      this.appointment.saveAppointments(this.bookAppointment).subscribe(
        (response) => this.bookAppointment = response,
        (error) => this.errorMessage = error
      );
      localStorage.removeItem('doctor');
    }
    else{
      window.alert("Try Again");
    }
  });
    
  }

  /* OpenPaymentDialog()
    {
       let dialogRef=this.dialog.open(Pay2Component,{height:'80%',width:'40%'});
       dialogRef.afterClosed().subscribe(
          data=>this.Istrue=data
       );
       return  this.Istrue;
      // dialogConfig.disableClose = false;
      
       dialogConfig.autoFocus = true;
         dialogConfig.position = {
          'top': '10%',
           left: '20%'
               };
       this.dialog.open(Pay2Component);
   } */







}
