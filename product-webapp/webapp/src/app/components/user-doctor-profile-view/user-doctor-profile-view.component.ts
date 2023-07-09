import { Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Doctor } from 'src/app/models/doctor.model';
import { DataStorageServiceService } from 'src/app/services/data-storage-service.service';
import { DoctorProfileService } from 'src/app/services/doctor-profile.service';
import { LoginService } from 'src/app/services/login.service';


@Component({
  selector: 'app-user-doctor-profile-view',
  templateUrl: './user-doctor-profile-view.component.html',
  styleUrls: ['./user-doctor-profile-view.component.css']
})
export class UserDoctorProfileViewComponent implements OnInit {

  doctor: Doctor;
  // name= "Yash";
  error: any;
  // facility: Array<string> = [] ;
  emailId: string ;


  //@Output() GetDoctorFromUserDoctor:EventEmitter<Doctor> = new EventEmitter();


  constructor(private doctorService:DoctorProfileService,private route: ActivatedRoute, private dataStorageService:DataStorageServiceService,
    private router: Router, private login: LoginService) { }

  ngOnInit(): void {
    // this.facility= this.doctor.facilities;
   this.emailId = this.route.snapshot.params["emailId"];
   //this.emailId = "suryansh@gmail.com";

    this.doctorService.getDoctorById(this.emailId).subscribe(
      (response) => {
        this.doctor = response;
      },
      (error) => {
        this.error = error;
      }
    );
    console.log(this.doctor.name);
    // console.log(this.facility);

  }
  
  public sendDoctorData( ):void {
    console.log("Hello");
    console.log(this.doctor);
    console.log(this.login.isLoggedIn());
    if(this.login.isLoggedIn()) {
      localStorage.setItem('doctor', JSON.stringify(this.doctor));
      this.router.navigate(['/book-appointment']);
    }
    else {
      this.router.navigate(['/login']);
    }

    
    /*this.dataStorageService.sendDoctorData(this.doctor);
    console.warn(this.doctor);*/

  }

}

