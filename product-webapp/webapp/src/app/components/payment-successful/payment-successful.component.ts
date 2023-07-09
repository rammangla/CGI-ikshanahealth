
import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
@Component({
  selector: 'app-payment-successful',
  templateUrl: './payment-successful.component.html',
  styleUrls: ['./payment-successful.component.css']
})
export class PaymentSuccessfulComponent implements OnInit {
  constructor( private dialogRef: MatDialogRef<PaymentSuccessfulComponent>) { }
  ngOnInit(): void {
  }
  Successclose() {
   // this.dialogRef.disableClose=true;
    console.log("Hello");
    this.dialogRef.close(true);
    console.log();
} 
}