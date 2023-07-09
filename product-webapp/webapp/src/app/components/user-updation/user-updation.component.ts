import { Component, OnInit, Inject, Optional } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-user-updation',
  templateUrl: './user-updation.component.html',
  styleUrls: ['./user-updation.component.css']
})
export class UserUpdationComponent implements OnInit {
  maxDate = new Date();
  hide = true;
  form: FormGroup;
  user: User;
  constructor(public dialogRef: MatDialogRef<UserUpdationComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any, private fb: FormBuilder) {
   }

  ngOnInit(): void {
    console.log(this.data.name);
    this.user = this.data;
    this.form = this.fb.group({
      emailId: new FormControl({value: '', disabled: true}, [Validators.email, Validators.required ]),
      password: new FormControl('', [Validators.required,  Validators.minLength(8), Validators.pattern(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/) ]),
      name: new FormControl('', [Validators.required ]),
      dob: new FormControl('', [Validators.required ]),
      gender: new FormControl('', [Validators.required ]),
      phoneNumber: new FormControl('', [Validators.required ]),
      plotNo: new FormControl('', [Validators.required ]),
      street: new FormControl('', [Validators.required ]),
      town: new FormControl('', [Validators.required ]),
      district: new FormControl('', [Validators.required ]),
      state: new FormControl('', [Validators.required ]),
      pincode: new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(6), Validators.min(100000) ])
    });
  }
  get emailIdInput(): any { return this.form.get('emailId'); }
  get passwordInput(): any { return this.form.get('password'); }
  get nameInput(): any { return this.form.get('name'); }
  get genderInput(): any { return this.form.get('gender'); }
  get dobInput(): any { return this.form.get('dob'); }
  get phoneNumberInput(): any { return this.form.get('phoneNumber'); }
  get plotNoInput(): any { return this.form.get('plotNo'); }
  get streetInput(): any { return this.form.get('street'); }
  get townInput(): any { return this.form.get('town'); }
  get districtInput(): any { return this.form.get('district'); }
  get stateInput(): any { return this.form.get('state'); }
  get pincodeInput(): any { return this.form.get('pincode'); }

  closeDialog() {
     this.data.name = this.form.get('name').value;
     this.data.emailId = this.form.get('emailId').value;
     this.data.dob = this.form.get('dob').value;
     this.data.gender = this.form.get('gender').value;
     this.data.phoneNumber = this.form.get('phoneNumber').value;
     this.data.password = this.form.get('password').value;
     this.data.address.plotNo = this.form.get('plotNo').value;
     this.data.address.street = this.form.get('street').value;
     this.data.address.town = this.form.get('town').value;
     this.data.address.district = this.form.get('district').value;
     this.data.address.state = this.form.get('state').value;
     this.data.address.pincode = this.form.get('pincode').value;
     console.log(this.form.get('name').value);
      console.log(this.data.name);
    // this.dialogRef.close({ event: 'close', data: this.data });
    if(this.form.valid) {
      this.dialogRef.close(this.data);
    }
  }

  cancelDialog() {
   if(this.form.valid) {
     this.dialogRef.close(this.user);
   }
 }

}
