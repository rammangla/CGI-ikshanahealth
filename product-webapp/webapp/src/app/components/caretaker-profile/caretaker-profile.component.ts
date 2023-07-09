import { Component, Inject, OnInit, Optional } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CaretakerServiceService } from 'src/app/services/caretaker-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Caretaker } from 'src/app/models/caretaker.model';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from "@angular/material/snack-bar";
// import { CaretakerDetails } from 'src/app/models/caretaker-details.model';

@Component({
  selector: 'app-caretaker-profile',
  templateUrl: './caretaker-profile.component.html',
  styleUrls: ['./caretaker-profile.component.css']
})

export class CaretakerProfileComponent implements OnInit{

  caretaker:Caretaker;
  editForm: FormGroup;
  isLoading= false;
  hide = true;
  maxDate = new Date();
  error: any;


  
  constructor(public dialogRef: MatDialogRef<CaretakerProfileComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
    private formBuilder : FormBuilder, public snackBar: MatSnackBar
) { }

  ngOnInit(): void {
    this.caretaker = this.data;
    this.editForm = this.formBuilder.group({
      name: new FormControl ('', Validators.required),
      emailId: new FormControl ('', [Validators.email, Validators.required]),
      dob: new FormControl ('',Validators.required),
      phoneNumber: new FormControl ('',[Validators.required, Validators.maxLength(10)]),
      password : new FormControl ('',[Validators.required]),
      gender : new FormControl ('',[Validators.required]),
      idCard : new FormControl ('',[Validators.required]),
      // hireName : new FormControl ('',[Validators.required, Validators.maxLength(50), Validators.minLength(4)]),
      plotNo: new FormControl ('', [Validators.required]),
      street: new FormControl ('',[Validators.required]),
      town : new FormControl('',[Validators.required]),
      district: new FormControl ('',[Validators.required]),
      state: new FormControl ('',[Validators.required]),
      pincode: new FormControl ('',[Validators.required, Validators.maxLength(6)]),
      totalExperience: new FormControl ('', [Validators.required, Validators.maxLength(2)]),
      fees: new FormControl ('', [Validators.required, Validators.maxLength(5)]),
      // services: new FormControl('',[Validators.required])
    });

  }
  
  get nameInput(): any { return this.editForm.get('name'); }
  get emailIdInput(): any { return this.editForm.get('emailId'); }
  get dobInput(): any { return this.editForm.get('dob'); }
  get phoneNumberInput(): any { return this.editForm.get('phoneNumber'); }
  get passwordInput(): any { return this.editForm.get('password'); }
  get genderInput(): any { return this.editForm.get('gender'); }
  get idCardInput():any {return this.editForm.get('idCard');}
  get plotNoInput(): any { return this.editForm.get('plotNo'); }
  get streetInput(): any { return this.editForm.get('street'); }
  get townInput(): any {return this.editForm.get('town');}
  get districtInput(): any {return this.editForm.get('district');}
  get stateInput(): any {return this.editForm.get('state');}
  get pincodeInput(): any {return this.editForm.get('pincode');}
  get totalExperienceInput(): any {return this.editForm.get('totalExperience');}
  get feesInput():any {return this.editForm.get('fees');}
  // get servicesInput(): any{return this.editForm.get('services');}
 
  onSubmit() {
    this.data.name = this.editForm.get('name').value;
    this.data.emailId = this.editForm.get('emailId').value;
    this.data.dob = this.editForm.get('dob').value; 
    this.data.phoneNumber = this.editForm.get('phoneNumber').value;
    this.data.password = this.editForm.get('password').value;
    this.data.gender = this.editForm.get('gender').value;
    this.data.idCard = this.editForm.get('idCard').value;
    this.data.address.plotNo = this.editForm.get('plotNo').value;
    this.data.address.street = this.editForm.get('street').value;
    this.data.address.town = this.editForm.get('town').value;
    this.data.address.district = this.editForm.get('district').value;
    this.data.address.state = this.editForm.get('state').value;
    this.data.address.pincode = this.editForm.get('pincode').value;
    this.data.totalExperience = this.editForm.get('totalExperience').value;
    this.data.fees = this.editForm.get('fees').value;
    // this.data.services = this.editForm.get('services').value;
    

    console.log(this.data.name);
    if (this.editForm.valid) {
    this.dialogRef.close(this.data);
    this.snackBar.open('Caretaker updated successfully!!', 'OK', {
         duration: 2000,
      });
    }
  }  

 
  cancelDialog(){
    if(this.editForm.valid){
      this.dialogRef.close(this.caretaker);
    }
   }

   
  // trackBy(index, item) {
  //   return index++;
  // }
 }

 

