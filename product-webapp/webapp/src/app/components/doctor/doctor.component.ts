import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Doctor } from 'src/app/models/doctor.model';
import { DoctorProfileService } from 'src/app/services/doctor-profile.service';
import { DoctorAddress } from 'src/app/models/doctor-address.model';

@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.css']
})
export class DoctorComponent implements OnInit {
  maxDate = new Date();
  isSubmitted:boolean = false;
  exists = false;
  doctor: Doctor;
  message = '';
  newDoctor : any =[];
  gridColumns=3;
  registerFormGrp : FormGroup;
  hide = true;
  hide1 = true;
  error = '';
  showSuccess:boolean=false;
  showFail:boolean=false;
  doctorAddress: DoctorAddress;

  constructor(private rf: FormBuilder , private doctorService : DoctorProfileService) { }

  ngOnInit(): void {
        this.registerFormGrp = this.rf.group({
      email: new FormControl('', [Validators.required, Validators.email,Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]),
      password: new FormControl('', [Validators.required, Validators.pattern(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/) ]),
      fullName: new FormControl('', [Validators.required ]),
      dob: new FormControl('', [Validators.required ]),
      gender: new FormControl('', [Validators.required ]),
      edu: new FormControl('', [Validators.required ]),
      icard: new FormControl('', [
        Validators.required,
        Validators.pattern("^[0-9]*$"),
        Validators.maxLength(12),
        Validators.minLength(12),
      ]),
      // icard: new FormControl('', [Validators.required, Validators.min(12), Validators.minLength(12)]),
      license: new FormControl('', [
        Validators.required,
        Validators.pattern("^[0-9]*$"),
      ]),
      // license: new FormControl('', [Validators.required ]),
      experience: new FormControl('', [
        Validators.required,
        Validators.pattern("^[0-9]*$"),
        Validators.maxLength(2),
        Validators.minLength(1),
      ]),
      // experience: new FormControl('', [Validators.required ]),
      phone: new FormControl('', [
        Validators.required,
        Validators.pattern("^[0-9]*$"),
        Validators.maxLength(10),
        Validators.minLength(10),
      ]),
      confirmpassword: new FormControl('', [Validators.required, Validators.min(8) ]),
      // experience: new FormControl('', [Validators.required ]),
      // phoneNumber: new FormControl('', [Validators.required ]),
      // confirmpassword: new FormControl('', [Validators.required, Validators.min(8) ]),
      address: new FormControl('', [Validators.required]),
      specialization: new FormControl('', [Validators.required]),
     
    });
  }
  // get emailInput(): any { return this.registerFormGrp.get('email'); }
   get passwordInput(): any { return this. registerFormGrp.get('password'); }
  // get fullNameInput(): any { return this. registerFormGrp.get('fullName'); }
  // get genderInput(): any { return this. registerFormGrp.get('gender'); }
  // get dobInput(): any { return this. registerFormGrp.get('dob'); }
  // get eduInput():any {return this.registerFormGrp.get('edu');}
  // get icardInput():any {return this.registerFormGrp.get('icard');}
  // get licenseInput():any {return this.registerFormGrp.get('license');}
  // get experienceInput():any {return this.registerFormGrp.get('experience');}
  // get phoneInput():any {return this.registerFormGrp.get('phone');}
   get confirmpasswordInput(): any { return this.registerFormGrp.get('confirmpassword'); }
  // get addressInput(): any { return this.registerFormGrp.get('address'); }
  // get specializationInput(): any { return this.registerFormGrp.get('specialization'); }

 
  
  onSubmit(){
    this.isSubmitted= true;
    const newaddress = this.registerFormGrp.get('address').value.split(', ');
    console.log(newaddress.length);
    if(newaddress.length<7 || newaddress.length>7){
      window.alert('Follow the right address format');
      window.location.reload();
    }
    else {
      this.doctorAddress = new DoctorAddress(newaddress[0], newaddress[1], newaddress[2], newaddress[3], newaddress[4], newaddress[5],newaddress[6]);
    }
    this.doctor = new Doctor(
    this.registerFormGrp.get('fullName').value,
    this. registerFormGrp.get('email').value, 
    this.registerFormGrp.get('dob').value,
     this.registerFormGrp.get('gender').value,
    this.registerFormGrp.get('icard').value, 
    this.registerFormGrp.get('edu').value, 
    this.doctorAddress, 
    this.registerFormGrp.get('license').value, 
    this.registerFormGrp.get('phone').value,
     this.registerFormGrp.get('specialization').value,
     this.registerFormGrp.get('experience').value,
     this.registerFormGrp.get('password').value
     );
    console.log(this.doctor.phoneNumber);
    console.log(this.doctor.address);
    console.log(this.doctorAddress);
    console.log(this.doctor);
    console.log(this.doctor.password);
    console.log(this.registerFormGrp.get('specialization').value);
    if (this.doctorService.doctors != null) {
     
      this.newDoctor = this.doctorService.doctors;
    }
    else {

    this.doctorService.getDoctors().subscribe(
      (response: any) => this.newDoctor = response,
      (error) =>  {
        this.error = error;
      }
    );}

    console.log(this.doctor.emailId);
    console.log(this.newDoctor);
    for (const element of this.newDoctor) {
      if (element.emailId === this.doctor.emailId) {
       this.exists = true;
       break;
      }
    }
    console.log(this.doctor.emailId);

    if ( this.exists === false && this.registerFormGrp.valid) {
      this.doctorService.addNewDoctor(this.doctor).subscribe(
         (response) => this.doctor = response,
         (error) => this.error = error
      );
      this.showSuccess = true;
      this.showFail = false;
      console.log(this.exists);
      console.log(this.doctor.emailId);
      this.newDoctor.push(this.doctor); // check once during running
      this.registerFormGrp.reset({
        fullName: '',
        email: '',
        dob: '',
        gender: '',
        icard:'',
        edu: '',
        address:'',
        license: '',
        specialization: '',
        experience: '',
        phone:'',
        password: ''
      });
     
      return this.message = 'Doctor Info Submitted Succusfully';
    }
    else {
      
      console.log(this.exists);
      console.log(this.doctor.emailId);
      this.showSuccess = false;
      this.showFail = true;
      return this.message = 'The Doctor either already exists or the data entered is wrong';
    }

 
  }
  
}
  


