import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { CaretakerAddress } from 'src/app/models/caretaker-address.model';
import { Caretaker } from 'src/app/models/caretaker.model';
import { CaretakerServiceService } from 'src/app/services/caretaker-service.service';

@Component({
  selector: 'app-caretaker',
  templateUrl: './caretaker.component.html',
  styleUrls: ['./caretaker.component.css']
})
export class CaretakerComponent implements OnInit {
  maxDate = new Date();
  isSubmitted: boolean = false;
  caretaker: Caretaker;
  caretakers: any = [];
  gridColumns = 3;
  registerFormGrp: FormGroup;
  // error: string;
  exists: boolean = false;
  message: string;
  caretakerAddr: CaretakerAddress;
  hide = true;
  hide1 = true;
  error = '';
  showSuccess:boolean=false;
  showFail:boolean=false;
  constructor(private rf: FormBuilder, private caretakerRegistration: CaretakerServiceService) { }


  ngOnInit(): void {
    console.log(new Date());
    this.registerFormGrp = this.rf.group({
      email: new FormControl('', [Validators.required, Validators.email,Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]),
      // email: new FormControl('', [Validators.email, Validators.required]),
      password: new FormControl('', [Validators.required, Validators.pattern(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/) ]),
      // password: new FormControl('', [Validators.required, Validators.min(8), Validators.minLength(8), Validators.pattern(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/)]),
      fullName: new FormControl('', [Validators.required]),
      dob: new FormControl('', [Validators.required]),
      gender: new FormControl('', [Validators.required]),
      //edu: new FormControl('', [Validators.required, Validators.min(3) ]),
      // icard: new FormControl('', [Validators.required]),
      icard: new FormControl('', [
        Validators.required,
        Validators.pattern("^[0-9]*$"),
        Validators.maxLength(12),
        Validators.minLength(12),
      ]),
      address: new FormControl('', [Validators.required]),
      //license: new FormControl('', [Validators.required, Validators.min(3) ]),
      // experience: new FormControl('', [Validators.required]),
      experience: new FormControl('', [
        Validators.required,
        Validators.pattern("^[0-9]*$"),
        Validators.maxLength(2),
        Validators.minLength(1),
      ]),
      phone: new FormControl('', [
        Validators.required,
        Validators.pattern("^[0-9]*$"),
        Validators.maxLength(10),
        Validators.minLength(10),
      ]),
      confirmpassword: new FormControl('', [Validators.required, Validators.min(8) ]),
      specialization: new FormControl('', [Validators.required]),
      //phone: new FormControl('', [Validators.required, Validators.minLength(10), Validators.maxLength(10), Validators.min(10)]),
      // confirmpassword: new FormControl('', [Validators.required, Validators.min(8) ]),
    });
  }



  
  get emailIdInput(): any { return this.registerFormGrp.get('email'); }
  get passwordInput(): any { return this.registerFormGrp.get('password'); }
  get nameInput(): any { return this.registerFormGrp.get('fullName'); }
  get genderInput(): any { return this.registerFormGrp.get('gender'); }
  get dobInput(): any { return this.registerFormGrp.get('dob'); }
  get icardInput(): any { return this.registerFormGrp.get('icard'); }
  get addressInput(): any { return this.registerFormGrp.get('address'); }
  get experienceInput(): any { return this.registerFormGrp.get('experience'); }
  get confirmpasswordInput(): any { return this.registerFormGrp.get('confirmpassword'); }
  
  
  
  onSubmit() {
    this.isSubmitted = true;
    address: CaretakerAddress;
    var addArr = this.registerFormGrp.get('address').value.split(', ');
    console.log(addArr.length);
    if(addArr.length<6 || addArr.length>6) {
      window.alert('Follow the right address format');
      window.location.reload();
    }
    else {
      this.caretakerAddr = new CaretakerAddress(addArr[0], addArr[1], addArr[2], addArr[3], addArr[4], addArr[5]);
    }
    this.caretaker = new Caretaker(
      this.registerFormGrp.get('fullName').value,
    this.registerFormGrp.get('email').value,
     this.registerFormGrp.get('password').value,
    this.registerFormGrp.get('dob').value, 
    this.registerFormGrp.get('gender').value,
    this.caretakerAddr,
    this.registerFormGrp.get('icard').value,
     this.registerFormGrp.get('experience').value,
     this.registerFormGrp.get('phone').value,
     this.registerFormGrp.get('specialization').value);
    console.log(this.caretaker.phoneNumber);
    console.log(this.caretaker.address);
    console.log(this.caretakerAddr);
    console.log(this.caretaker);
    console.log(this.caretaker.password);
    console.log(this.caretaker.specialization);
    if (this.caretakerRegistration.caretakerss != null) {
      this.caretakers = this.caretakerRegistration.caretakerss;
    }
    else {

      this.caretakerRegistration.getCaretakers().subscribe(
        (response: Caretaker[]) => this.caretakers = response,
        (error) =>  {
          this.error = error;
        }
     );
    }
    console.log(this.caretaker.emailId);

    for (const element of this.caretakers) {
      if (element.emailId === this.caretaker.emailId) {
       this.exists = true;
       break;
      }
    }

    if (this.exists === false && this.registerFormGrp.valid) {
      this.caretakerRegistration.addCaretaker(this.caretaker).subscribe(
         (response) => this.caretaker = response,
         (error) => this.error = error
      );
      this.showSuccess = true;
      this.showFail = false;
      console.log(this.exists);
      console.log(this.caretaker.emailId);
      this.caretakers.push(this.caretaker); // check once during running
      this.registerFormGrp.reset({
        fullName: '',
        emailId: '',
        dob: '',
        gender: '',
        password: '',
        icard: '',
        address: '',
        experience: ''
      });
      return this.message = 'Caretaker Info Submitted Succussefully';
    }
    else {
      console.log(this.exists);
      console.log(this.caretaker.emailId);
      // this.registerFormGrp.reset({
      //   fullName: '',
      //   emailId: '',
      //   dob: '',
      //   gender: '',
      //   password: '',
      //   icard: '',
      //   address: '',
      //   experience: ''
      // });
      this.showSuccess = false;
      this.showFail = true;
      return this.message = 'The Caretaker either already exists or the data entered is wrong';
    }
  }
}
