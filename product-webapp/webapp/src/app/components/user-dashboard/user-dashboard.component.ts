import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatTabChangeEvent } from '@angular/material/tabs';
import { Appointment } from 'src/app/models/appointment.model';
import { Caretaker } from 'src/app/models/caretaker.model';
import { User } from 'src/app/models/user.model';
import { CaretakerServiceService } from 'src/app/services/caretaker-service.service';
import { RegisterService } from 'src/app/services/register.service';
/*import { MatDialog } from '@angular/material/dialog';*/
import { Router } from '@angular/router';
import { ChatComponent } from '../chat/chat.component';
 
@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {

  today = new Date();
  users: User[] = [];
  userReq: User;
  upcoming: Appointment[] = [];
  previous: Appointment[] = [];
  emailIdGiven: string;
  selectedRating = 0;
  stars = [
    {
      id: 1,
      icon: 'star',
      class: 'star-gray star-hover star'
    },
    {
      id: 2,
      icon: 'star',
      class: 'star-gray star-hover star'
    },
    {
      id: 3,
      icon: 'star',
      class: 'star-gray star-hover star'
    },
    {
      id: 4,
      icon: 'star',
      class: 'star-gray star-hover star'
    },
    {
      id: 5,
      icon: 'star',
      class: 'star-gray star-hover star'
    }

  ];
  error: string;
  caretakers: Caretaker[] = [];
  neededCaretakers: Caretaker[] = []
  caretakerSlotMap = new Map<Date, Caretaker>();
  caretakerpreviousSlotMap = new Map<Date, Caretaker>();
  caretakerupcomingSlotMap = new Map<Date, Caretaker>();

  constructor(private registerService: RegisterService, private getCaretaker: CaretakerServiceService, private router: Router) { }

  ngOnInit(): void {
    this.emailIdGiven = localStorage.getItem('emailId');
    console.log(this.emailIdGiven);
    this.registerService.getUserById(this.emailIdGiven).subscribe(
      (response) => {
        this.userReq = response;
        for (const appoint of this.userReq.appointments) {
          console.log(JSON.stringify(appoint));
          if (new Date(appoint.date) > this.today) {
            this.upcoming.push(appoint);
          }
          else {
            this.previous.push(appoint);
          }
        }
        console.log(this.previous);
        console.log(this.upcoming);
      },
      (error) => {
        this.error = error;
      }
    );

  }


  selectStar(value): void {
    this.stars.filter((star) => {
      if (star.id <= value) {
        star.class = 'star-gold star star-hover';
      } else {
        star.class = 'star-gray star star-hover';
      }
      return star;
    });
    this.selectedRating = value;

  }

  onChange(event: MatTabChangeEvent) {
    const tab = event.tab.textLabel;
    console.log(tab);
    if (tab === "Hired Caretaker") {
      this.getCaretaker.getCaretakers().subscribe(
        (response) => this.caretakers = response,
        (error) => this.error = this.error
      );
      for (const booked of this.caretakers) {
        for (const userbooked of this.userReq.caretakerAppointments) {
          if (booked.emailId = userbooked.caretakerEmailId) {
            this.caretakerSlotMap.set(userbooked.date, booked);
            console.log(JSON.stringify(this.caretakerSlotMap));
          }
        }
      }

      for (const item of this.caretakerSlotMap) {
        if(new Date(item[0]) < new Date()){
          this.caretakerpreviousSlotMap.set(item[0], item[1]);
        }
        else{
          this.caretakerupcomingSlotMap.set(item[0], item[1]);
        }
      }

    }
    console.log(JSON.stringify(this.caretakerpreviousSlotMap));
    console.log(JSON.stringify(this.caretakerupcomingSlotMap));
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
