import { Component, Inject, OnInit, Optional } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Doctor } from 'src/app/models/doctor.model';
import { MatSnackBar } from "@angular/material/snack-bar";


@Component({
  selector: 'app-doctor-profile',
  templateUrl: './doctor-profile.component.html',
  styleUrls: ['./doctor-profile.component.css']
})
export class DoctorProfileComponent implements OnInit {

  doctor: Doctor;
  editForm: FormGroup;
  isLoading = false;
  hide = true;
  maxDate = new Date();
  error: any;


  constructor(
    public dialogRef: MatDialogRef<DoctorProfileComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public data: any,
    private formBuilder: FormBuilder, public snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.doctor = this.data;
    this.editForm = this.formBuilder.group({
      name: new FormControl('', Validators.required),
      emailId: new FormControl('', [Validators.email, Validators.required]),
      dob: new FormControl('', Validators.required),
      phoneNumber: new FormControl('', [Validators.required, Validators.maxLength(10)]),
      password: new FormControl('', [Validators.required]),
      gender: new FormControl('', [Validators.required]),
      idCard: new FormControl('', [Validators.required]),
      educationalQualification: new FormControl('', [Validators.required, Validators.maxLength(30)]),
      clinicName: new FormControl('', [Validators.required, Validators.maxLength(50), Validators.minLength(10)]),
      plotNo: new FormControl('', [Validators.required]),
      street: new FormControl('', [Validators.required]),
      town: new FormControl('', [Validators.required]),
      district: new FormControl('', [Validators.required]),
      state: new FormControl('', [Validators.required]),
      pinCode: new FormControl('', [Validators.required]),
      licenseNumber: new FormControl('', [Validators.required, Validators.maxLength(12)]),
      specialization: new FormControl('', [Validators.required]),
      totalExperience: new FormControl('', [Validators.required, Validators.maxLength(2)]),
      fees: new FormControl('', [Validators.required, Validators.maxLength(3)]),
      // facilities: new FormControl('',[Validators.required])
    });


  }

  get nameInput(): any { return this.editForm.get('name'); }
  get emailIdInput(): any { return this.editForm.get('emailId'); }
  get passwordInput(): any { return this.editForm.get('password'); }
  get genderInput(): any { return this.editForm.get('gender'); }
  get dobInput(): any { return this.editForm.get('dob'); }
  get phoneNumberInput(): any { return this.editForm.get('phoneNumber'); }
  get plotNoInput(): any { return this.editForm.get('plotNo'); }
  get streetInput(): any { return this.editForm.get('street'); }
  get educationalQualificationInput(): any { return this.editForm.get('educationalQualification'); }
  get clinicNameInput(): any { return this.editForm.get('clinicName'); }
  get townInput(): any { return this.editForm.get('town'); }
  get districtInput(): any { return this.editForm.get('district'); }
  get stateInput(): any { return this.editForm.get('state'); }
  get pinCodeInput(): any { return this.editForm.get('pinCode'); }
  get licenseNumberInput(): any { return this.editForm.get('licenseNumber'); }
  get specializationInput(): any { return this.editForm.get('specialization'); }
  get totalExperienceInput(): any { return this.editForm.get('totalExperience'); }
  get feesInput(): any { return this.editForm.get('fees'); }
  // get facilitiesInput(): any{return this.editForm.get('facilities');} 



  onSubmit() {
    this.data.name = this.editForm.get('name').value;
    this.data.emailId = this.editForm.get('emailId').value;
    this.data.password = this.editForm.get('password').value;
    this.data.gender = this.editForm.get('gender').value;
    this.data.dob = this.editForm.get('dob').value;
    this.data.phoneNumber = this.editForm.get('phoneNumber').value;
    this.data.address.plotNo = this.editForm.get('plotNo').value;
    this.data.address.street = this.editForm.get('street').value;
    this.data.address.educationalQualification = this.editForm.get('educationalQualification').value;
    this.data.address.clinicName = this.editForm.get('clinicName').value;
    this.data.address.town = this.editForm.get('town').value;
    this.data.address.district = this.editForm.get('district').value;
    this.data.address.state = this.editForm.get('state').value;
    this.data.address.pinCode = this.editForm.get('pinCode').value;
    this.data.licenseNumber = this.editForm.get('licenseNumber').value;
    this.data.specialization = this.editForm.get('specialization').value;
    this.data.totalExperience = this.editForm.get('totalExperience').value;
    this.data.fees = this.editForm.get('fees').value;

    // this.data.facilities = this.editForm.get('facilities').value;
    console.log(this.editForm.get('name').value);
    console.log(this.data.name);
    // console.log(this.data.facilities);
    if (this.editForm.valid) {
      this.dialogRef.close(this.data);
      this.snackBar.open('Doctor updated successfully!!', 'OK', {
        duration: 2000,
      });
    }
  }


  cancelDialog() {
    if (this.editForm.valid) {
      this.dialogRef.close(this.doctor);
    }
  }

  // trackBy(index, item) {
  //   return index++;
  // }

}





