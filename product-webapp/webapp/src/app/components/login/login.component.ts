import { Component, OnInit } from '@angular/core';
import { Login } from 'src/app/models/login.model';
import {  Form, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginService } from 'src/app/services/login.service';
import { LoginWithPassword } from 'src/app/models/login-with-password.model';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 

  constructor(private fb: FormBuilder, private loginService: LoginService ,private _router: Router ) { }
  loginform: FormGroup;
  passwordloginform: FormGroup;
  login: Login;
  loginWithPassword:LoginWithPassword;
  message='';
  message1='';
  error='';
  error1='';
  token: string;
  emailId:string;
  role:string;




  ngOnInit(): void {
    this.loginform = this.fb.group({
      username: new FormControl('', [Validators.email, Validators.required]),
      otpnumber: new FormControl('', [Validators.minLength(6), Validators.maxLength(6)])
    
    });

    this.passwordloginform = this.fb.group({
      username: new FormControl('', [Validators.email, Validators.required]),
      password: new FormControl('',[Validators.min(8), Validators.minLength(8), Validators.pattern(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/) ])
    });

  }

  get usernameInput(): any { return this.loginform.get('username'); }
  get otpnumberInput(): any { return this.loginform.get('otpnumber'); }
  get paasswordInput(): any {return this.passwordloginform.get('password'); }
  get usernameInputFromPassword(): any { return this.passwordloginform.get('username'); }


  checkOtp(): any {
    this.login = new Login(this.loginform.get('username').value,this.loginform.get('otpnumber').value);
    this.loginService.validateOtp(this.login).subscribe(
      (response) => {this.message1 = response;console.log(this.message1)
                    this.loginService.setOtp(this.loginform.get('otpnumber').value);
                   this._router.navigate(['/user-dashboard'])},

      (error)=>this.error1=error
    );
  }
  getOtp(): any {
    this.login = new Login(this.loginform.get('username').value,this.loginform.get('otpnumber').value);
    this.loginService.generateOtp(this.login).subscribe(
      (response) => {this.message = response;console.log(response);
                    this.loginService.setEmailId(this.loginform.get('username').value);
            },
       (error)=>{this.error=error;console.log(error)}
    );
  }
  getAuthenticated(): any{
    this.loginWithPassword=new LoginWithPassword(this.passwordloginform.get('username').value,this.passwordloginform.get('password').value);
    this.loginService.getAuthenticated(this.loginWithPassword).subscribe(
      (response)=>{console.log(response);

      // saving token to localStorage
      this.token=response.jwt;
       this.loginService.loginUser(this.token);

       var payload = JSON.parse(window.atob(this.token.split('.')[1])); 
       console.log(payload);
       // saving emailId to localStorage
       this.emailId=payload.sub;
       console.log(this.emailId);
       this.loginService.setEmailId(this.emailId);

       // saving role to localStorage
       this.role=payload.role[0].authority;
       console.log(this.role);
      this.loginService.setRole(this.role);

      // navigating based on role of the user
      
       if(this.role=="user"){
        this._router.navigate(['/user-dashboard'])
       }else if(this.role=="doctor"){
         this._router.navigate(['/doctordashboard'])
       } else if (this.role == "caretaker") {
        this._router.navigate(['/caretakerdashboard'])
       }
    },
      (error)=>console.log(error)
    );
  }

}
