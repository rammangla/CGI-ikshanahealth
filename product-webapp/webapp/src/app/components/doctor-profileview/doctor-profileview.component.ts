import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { Doctor } from 'src/app/models/doctor.model';
import { DoctorProfileService } from 'src/app/services/doctor-profile.service';
import { DoctorProfileComponent } from '../doctor-profile/doctor-profile.component';

@Component({
  selector: 'app-doctor-profileview',
  templateUrl: './doctor-profileview.component.html',
  styleUrls: ['./doctor-profileview.component.css']
})
export class DoctorProfileviewComponent implements OnInit {

  doctor: Doctor;
  error: string;
  emailId: string;
  facilities: string[]= ["Emergency","Ambulance","Medical Insurance"];
  constructor(private doctorService:DoctorProfileService, public dialog :MatDialog, private router: ActivatedRoute) { }

  ngOnInit(): void {
    this.emailId = localStorage.getItem('emailId');
    this.doctorService.getDoctorById(this.emailId).subscribe(
      (response) => {
        this.doctor = response;
      },
      (error) => {
        this.error = error;
      }
    );
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(DoctorProfileComponent, {
      disableClose: true,
      data: this.doctor
    });

    dialogRef.afterClosed().subscribe(result => {
      this.doctor = result;
      console.log(JSON.stringify(result));
      this.doctorService.updateDoctor(this.doctor).subscribe(
        (response) => this.doctor=response,
        (error) => this.error = error
      );
    });
  }
}
