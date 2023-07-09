import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import { PaymentSuccessfulComponent } from '../payment-successful/payment-successful.component';
import { PaymentFailedComponent } from '../payment-failed/payment-failed.component';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-pay2',
  templateUrl: './pay2.component.html',
  styleUrls: ['./pay2.component.css']
})
export class Pay2Component implements OnInit {
  title = 'payment-service';
  form: FormGroup;
  amt: number = 750;
  constructor(private http: HttpClient,private dialog: MatDialog,public dialogRef:MatDialogRef<Pay2Component>,private fb: FormBuilder) {}
  ngOnInit(): void {
    //throw new Error('Method not implemented.');
    this.form = this.fb.group({
      cardNumber: new FormControl('', [ Validators.required ]),
      expiryMonth: new FormControl('', [Validators.required]),
      expiryYear: new FormControl('', [Validators.required ]),
      cvv: new FormControl('', [Validators.required ]),
      amount: new FormControl({value: '', disabled: true}, [Validators.required ])
    }
    );
  }
  get cardNumberInput(): any { return this.form.get('cardNumber'); }
  get expiryMonthInput(): any { return this.form.get('expiryMonth'); }
  get expiryYearInput(): any { return this.form.get('expiryYear'); }
  get cvvInput(): any { return this.form.get('cvv'); }
  get amountInput(): any { return this.form.get('amount'); }
     chargeCreditCard(){
   //this.openSuccessDialog();
   //this.openFailedDialog();
   // let form = document.getElementsByTagName("form")[0];
    console.log(this.form.get('cardNumber').value);
    (<any>window).Stripe.card.createToken({
      number: this.form.get('cardNumber').value,
      exp_month: this.form.get('expiryMonth').value,
      exp_year:  this.form.get('expiryYear').value,
      cvc: this.form.get('cvv').value
    }, (status: number, response: any) => {
      if (status === 200) {
        let token = response.id;
        this.chargeCard(token);
      } 
      else {
        //alert('wrong card details');
        this.openFailedDialog();
        console.log(response.error.message);
      }
    });
  }
   chargeCard = (token: string) => {
    const headers =new HttpHeaders({'token': token, 'amount': '750'});
    console.log(JSON.stringify(headers));
    this.http.post('https://ikshanahealth.stackroute.io/payment-service/payment/charge', '{}', {headers: headers})
      .subscribe(resp => {
        console.log(resp);
       //alert('payment done');
        this.openSuccessDialog();
      })
      console.log(JSON.stringify(headers));
  }
  openSuccessDialog(){
   // const dialogConfig = new MatDialogConfig();
       // dialogConfig.disableClose = false;
       /*
        dialogConfig.autoFocus = true;
          dialogConfig.position = {
           'top': '10%',
            left: '20%'
           }; */
        let dialogRef1 = this.dialog.open(PaymentSuccessfulComponent,{disableClose: true});
       dialogRef1.afterClosed().subscribe(
          data=> this.closeDialog(data)
       ); 
        //this.closeDialog(true);
       // dialogRef.close();
  }
openFailedDialog(){
 /* const dialogConfig = new MatDialogConfig();
  // dialogConfig.disableClose = false;
   dialogConfig.autoFocus = true;
     dialogConfig.position = {
      'top': '10%',
       left: '20%'
           }; */
        let dialogRef1= this.dialog.open( PaymentFailedComponent,{disableClose: true});
        dialogRef1.afterClosed().subscribe(
          data=> this.closeDialog(data)
       ); 
        // this.closeDialog(false);
  // this.dialog.open(PaymentFailedComponent);
 }
 closeDialog(status:boolean){
   this.dialogRef.close(status);
 } 
}

/* import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import { PaymentSuccessfulComponent } from '../payment-successful/payment-successful.component';
import { PaymentFailedComponent } from '../payment-failed/payment-failed.component';

@Component({
  selector: 'app-pay2',
  templateUrl: './pay2.component.html',
  styleUrls: ['./pay2.component.css']
})
export class Pay2Component implements OnInit {

  title = 'payment-service';

  
  constructor(private http: HttpClient,private dialog: MatDialog) {}
  ngOnInit(): void {
    throw new Error('Method not implemented.');
    
  }


   chargeCreditCard(){
    let form = document.getElementsByTagName("form")[0];
    (<any>window).Stripe.card.createToken({
     
      number: form.cardNumber.value,
      exp_month: form.expMonth.value,
      exp_year: form.expYear.value,
      cvc: form.cvc.value,
     
    }, (status: number, response: any) => {
      if (status === 200) {
        let token = response.id;
        this.chargeCard(token);
        
      } 
      else {
       
        //alert('wrong card details');
        this.openFailedDialog();
        console.log(response.error.message);
        
      }
    });
  }cd

   chargeCard = (token: string) => {
    const headers =new HttpHeaders({'token': token, 'amount': '1000'});
    this.http.post('https://ikshanahealth.stackroute.io/payment-service/payment/charge', '{}', {headers: headers})
    
      .subscribe(resp => {
        console.log(resp);
       //alert('payment done');
       this.openSuccessDialog();
       
      })

      
  }
  
  openSuccessDialog(){
    const dialogConfig = new MatDialogConfig();

       // dialogConfig.disableClose = false;
        dialogConfig.autoFocus = true;
        
          dialogConfig.position = {
           'top': '10%',
            left: '20%'
           };

        this.dialog.open(PaymentSuccessfulComponent);
  }
  
openFailedDialog(){
  const dialogConfig = new MatDialogConfig();

  // dialogConfig.disableClose = false;
   dialogConfig.autoFocus = true;
   
     dialogConfig.position = {
      'top': '10%',
       left: '20%'
           };

   this.dialog.open(PaymentFailedComponent);
}
  
  

}

 */

