import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
// import { Appointment } from 'src/app/models/appointment.model';
// import { CaretakerAppointments } from 'src/app/models/caretaker-appointments.model';
// import { CaretakerDetails } from 'src/app/models/caretaker-details.model';
import { Caretaker } from 'src/app/models/caretaker.model';
import { CaretakerServiceService } from 'src/app/services/caretaker-service.service';
import { CaretakerProfileComponent } from '../caretaker-profile/caretaker-profile.component';

@Component({
  selector: 'app-caretaker-data',
  templateUrl: './caretaker-data.component.html',
  styleUrls: ['./caretaker-data.component.css']
})
export class CaretakerDataComponent implements OnInit {

  caretaker: Caretaker;
  error: any;
  emailId: string;
  services: string[]= ["Baby Sitter","Elderly Care","Special needs"];

  constructor(private caretakerService:CaretakerServiceService, public dialog :MatDialog) { }

  ngOnInit(): void {
    this.emailId = localStorage.getItem('emailId');
    this.caretakerService.getCaretakerbyId(this.emailId).subscribe(
      (response) => {
        this.caretaker = response;
      },
      (error) => {
        this.error = error;
      }
    );
    console.log(this.caretaker.name);
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(CaretakerProfileComponent, {
      disableClose: true,
      data: this.caretaker
    });

    dialogRef.afterClosed().subscribe(result => {
      // alert('Caretaker updated successfully');
      this.caretaker = result;
      this.caretakerService.updateCaretaker(this.caretaker).subscribe(
        (response) => this.caretaker=response,
        (error) => this.error = error

      );

    });
  }
}
