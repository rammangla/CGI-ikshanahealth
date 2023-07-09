import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { LocationComponent } from '../location/location.component';
/**import { Interface } from 'readline';*/

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  locate:any;
  constructor(private router: Router,public loginService: LoginService, public dialog: MatDialog) { }

  ngOnInit(): void {
  }
  openDialog(){
    let dialogRef=this.dialog.open(LocationComponent, {
      height:'500px',
      width:'1500px'
    });
  }

 removeData(): any{
   this.loginService.logout();
   this.router.navigate(['/home']);
 }

}
