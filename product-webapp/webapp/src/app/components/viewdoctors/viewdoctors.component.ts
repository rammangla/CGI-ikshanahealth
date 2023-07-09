import { Component, OnInit } from '@angular/core';
import { Doctor } from 'src/app/models/doctor.model';
import { DoctorProfileService } from 'src/app/services/doctor-profile.service';
@Component({
  selector: 'app-viewdoctors',
  templateUrl: './viewdoctors.component.html',
  styleUrls: ['./viewdoctors.component.css']
})
export class ViewdoctorsComponent implements OnInit {
  doctorsList:Doctor[];
  errorMessage: any;
  constructor(private doctorService:DoctorProfileService ) { }
  ngOnInit(): void {
    this.doctorService.getAllDoctors().subscribe(
      (response: Doctor[])=> this.doctorsList=response,
      (error) => this.errorMessage = error
    );
    console.log(this.doctorsList);
  }
}





