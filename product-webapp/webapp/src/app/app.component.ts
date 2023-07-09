import { Component, OnInit } from '@angular/core';
import { RegisterService } from './services/register.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DoctorComponent } from './components/doctor/doctor.component';
import { CaretakerServiceService } from './services/caretaker-service.service';
import { DoctorProfileService } from './services/doctor-profile.service';
import { Doctor } from './models/doctor.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'IkshanaHealth';

  constructor(
    private registerService: RegisterService,
    private registercaretaker: CaretakerServiceService,
    private registerdoctor: DoctorProfileService,
    public dialog:MatDialog,
    
    ) {}

  ngOnInit(): void {
    this.registerService.getUser();
    this.registercaretaker.getCaretaker();
    this.registerdoctor.getDoctor();
  }
  
}

