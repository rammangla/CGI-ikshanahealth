import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { CaretakerAppointments } from 'src/app/models/caretaker-appointments.model';
import { Caretaker } from 'src/app/models/caretaker.model';
import { CaretakerServiceService } from 'src/app/services/caretaker-service.service';

@Component({
  selector: 'app-caretaker-slot',
  templateUrl: './caretaker-slot.component.html',
  styleUrls: ['./caretaker-slot.component.css']
})
export class CaretakerSlotComponent implements OnInit {

  form: FormGroup;
  date: Date = new Date();
  selectedDate: Date;
  isSubmitted: boolean;
  caretaker: Caretaker;
  availableSlots: CaretakerAppointments[] = [];
  error: any;
  constructor(private fb: FormBuilder, private getCaretaker: CaretakerServiceService) { }
  emailId: string;

  ngOnInit(): void {
    this.emailId = localStorage.getItem('emailId');

    this.getCaretaker.getCaretakerbyId(this.emailId).subscribe(
      (response) => this.caretaker = response,
      (error) => this.error = error
    );
    this.form = this.fb.group({
      appDate: new FormControl('', [Validators.required])
    });
  }

  get dobInput(): any { return this.form.get('appDate'); }

  onSubmit() {
    console.log(JSON.stringify(this.caretaker));
    this.isSubmitted = true;
    let slot: CaretakerAppointments = new CaretakerAppointments();
    this.selectedDate = this.form.get('appDate').value;
    this.selectedDate.setDate(this.selectedDate.getDate() + 1);
    slot.date = this.selectedDate;
    slot.appointmentId = this.availableSlots.length + 1;
    slot.caretakerEmailId = this.caretaker.emailId;
    this.availableSlots.push(slot);
    console.log(JSON.stringify(this.availableSlots));
    this.form.reset({
      appDate: ''
    });
  }

  onFinalSubmit() {
    if(this.caretaker != null) {
      this.caretaker.appointments = this.availableSlots;
    this.getCaretaker.updateCaretaker(this.caretaker).subscribe(
      (response) => this.caretaker = response,
      (error) => this.error = error
    );
    console.log(JSON.stringify(this.caretaker));
  }
  else {
    window.alert('Something went wrong, Kindly refresh and try again');
  }
  }

  myHolidayFilter = (d: Date): boolean => {
    const time = d.getDate();
    return !this.availableSlots.find(x => x.date.getDate()-1 == time);
  }

}
