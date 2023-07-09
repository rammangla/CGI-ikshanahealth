import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Login } from '../models/login.model';
import { LoginWithPassword } from '../models/login-with-password.model';



@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  generateOtp(login: Login): Observable<any>{
  return this.http.post('https://ikshanahealth.stackroute.io/user-auth-service/generateOtp', login , {
    headers: new HttpHeaders({'Content-type': 'application/json'})
  });
}
  validateOtp(login:Login): Observable<any>{
    return this.http.post('https://ikshanahealth.stackroute.io/user-auth-service/validateOtp', login ,
    {headers: new HttpHeaders({'Content-Type': 'application/json'})},
     );
  }
  getAuthenticated(loginWithPassword: LoginWithPassword): Observable<any>{
    return this.http.post('https://ikshanahealth.stackroute.io/user-auth-service/authenticate',loginWithPassword,
    {headers: new HttpHeaders({'Content-Type': 'application/json'})},);
  }
 


  // setters  to the localStorage

  public loginUser(token){
    localStorage.setItem('token',token);
    return true;
  }

   public setEmailId(emailId){
     localStorage.setItem('emailId',emailId);
     return true;
   }

   public setRole(role){
     localStorage.setItem('role',role);
     return true;
   }


   // check if user is logged in or not based on stored token 

   public isLoggedIn(){
     let tokenStr = localStorage.getItem('token');
     if(tokenStr == undefined || tokenStr == '' || tokenStr == null){
       return false;
     }else {
       return true;
     }
   }
   public isOtpPresent(){
    let otpnum= localStorage.getItem('otp');
    if(otpnum === null || otpnum === undefined || otpnum === ''){
      return false;
    }else{
      return true;
    }
  }

  // public isEmailPresent(){
  //   let email=localStorage.getItem('emailId');
  //   if(email == undefined || email == '' || email == null ){
  //     return false;
  //   }else {
  //     return true;
  //   }
  // }
   
   // remove all details from localStorage on logOut

   public logout(){
     localStorage.removeItem('token');
     localStorage.removeItem('emailId');
     localStorage.removeItem('role');
     localStorage.removeItem('otp');
     return true;
   }


   // getters from localStorage

   public getToken(){
     return localStorage.getItem('token');
   }
   
   public getEmailId(){
     return localStorage.getItem('emailId');
   }
  
   public getUserRole(){
     return localStorage.getItem('role');

   }

   public setOtp(otp){
    localStorage.setItem('otp',otp)
  }

  }
