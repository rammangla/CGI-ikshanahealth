import { Component, OnInit } from '@angular/core';
import { AbstractControl, Form, FormBuilder, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { User } from 'src/app/models/user.model';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private fb: FormBuilder, private registerService: RegisterService) {}

  maxDate = new Date();
  form: FormGroup;
  isSubmitted = false;
  exists = false;
  message = '';
  bookFormGroup: FormGroup;
  user: User;
  users: any = [];
  hide = true;
  hide1 = true;
  error = '';
  ngOnInit(): void {
        this.form = this.fb.group({
      emailId: new FormControl('', [Validators.email, Validators.required ]),
      password: new FormControl('', [Validators.required, Validators.min(8), Validators.minLength(8), Validators.pattern(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/) ]),
      name: new FormControl('', [Validators.required, Validators.min(3) ]),
      dob: new FormControl('', [Validators.required ]),
      gender: new FormControl('', [Validators.required ]),
      confirmpassword: new FormControl('', [Validators.required, Validators.min(8) ]),
    }, { validators: this.checkPasswords });
  }
  get emailIdInput(): any { return this.form.get('emailId'); }
  get passwordInput(): any { return this.form.get('password'); }
  get nameInput(): any { return this.form.get('name'); }
  get genderInput(): any { return this.form.get('gender'); }
  get dobInput(): any { return this.form.get('dob'); }
  get confirmpasswordInput(): any { return this.form.get('confirmpassword'); }

  onSubmit(): any {
    this.isSubmitted = true;
    this.user = new User(this.form.get('name').value,
    this.form.get('emailId').value, this.form.get('password').value,
    this.form.get('dob').value, this.form.get('gender').value);
    if (this.registerService.userss != null) {
      this.users = this.registerService.userss;
    }
    else {

    this.registerService.getUsers().subscribe(
      (response: User[]) => this.users = response,
      (error) =>  {
        this.error = error;
      }
   );
    }
    console.log(this.user.emailId);

    for (const element of this.users) {
      if (element.emailId === this.user.emailId) {
       this.exists = true;
       break;
      }
    }

    if (this.form.valid && this.exists === false) {
      this.registerService.registerUser(this.user).subscribe(
         (response) => this.user = response,
         (error) => this.error = error
      );
      this.users.push(this.user); // check once durig running
      this.form.reset({
        name: '',
        emailId: '',
        dob: '',
        gender: '',
        password: '',
        confirmpassword: ''
      });
      return this.message = 'Registration Successfull...!!!';
    }
    else {
      console.log(this.exists);
      this.form.reset({
        name: '',
        emailId: '',
        dob: '',
        gender: '',
        password: '',
        confirmpassword: ''
      });
      return this.message = 'The User either already exists or the data entered is wrong';
    }
  }

  checkPasswords: ValidatorFn = (group: AbstractControl): ValidationErrors | null => {
    const pass = group.get('password').value;
    const confirmPass = group.get('confirmpassword').value;
    return pass === confirmPass ? null : { notSame: true };
  }
}
