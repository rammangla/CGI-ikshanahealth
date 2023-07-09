import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { RegisterService } from 'src/app/services/register.service';
import { UserUpdationComponent } from '../user-updation/user-updation.component';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  email: string;
  user: User;
  error: string;

  constructor(private router: ActivatedRoute , private registerService: RegisterService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.email = localStorage.getItem('emailId');
    this.registerService.getUserById(this.email).subscribe(
      (response) => {
        this.user = response;
      },
      (error) => {
        this.error = error;
      }
    );
  }

  openDialog() {
    let dialogRef = this.dialog.open(UserUpdationComponent, {disableClose: true, data: this.user});
    dialogRef.afterClosed().subscribe(result => {
      this.user = result;
      console.log(JSON.stringify(result));
      this.registerService.updateUser(this.user).subscribe(
        (response) => this.user=response,
        (error) => this.error = error

      );
    });
  }

}
