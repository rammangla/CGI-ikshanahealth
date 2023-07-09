import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
@Component({
  selector: 'app-payment-failed',
  templateUrl: './payment-failed.component.html',
  styleUrls: ['./payment-failed.component.css']
})
export class PaymentFailedComponent implements OnInit {
  constructor( private dialogRef: MatDialogRef<PaymentFailedComponent>  ) { }
  ngOnInit(): void {
  }
closeDialog() {
    console.log("Hello");
    this.dialogRef.close(false);
    console.log();
}  
}
