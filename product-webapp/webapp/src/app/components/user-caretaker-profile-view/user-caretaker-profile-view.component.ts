import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Caretaker } from 'src/app/models/caretaker.model';
import { CaretakerServiceService } from 'src/app/services/caretaker-service.service';
import { DateCaretakerStorageService } from 'src/app/services/date-caretaker-storage.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-user-caretaker-profile-view',
  templateUrl: './user-caretaker-profile-view.component.html',
  styleUrls: ['./user-caretaker-profile-view.component.css']
})
export class UserCaretakerProfileViewComponent implements OnInit {
  
  caretaker: Caretaker;
  error: any;
  emailId: string;

  constructor(private caretakerService:CaretakerServiceService, private route: ActivatedRoute, private dateCaretakerStorageService : DateCaretakerStorageService,
  private router: Router, private login: LoginService ) { }

  ngOnInit(): void {
    this.emailId = this.route.snapshot.params["emailId"];
    this.caretakerService.getCaretakerbyId(this.emailId).subscribe(
      (response) => {
        this.caretaker = response;
      },
      (error) => {
        this.error = error;
      }
    );
    //console.log(this.caretaker.name);
  }
  
  public sendCaretakerData( ):void {
    console.log("Hello");
    console.log(this.caretaker);


    /* localStorage.setItem('caretaker', JSON.stringify(this.caretaker));
    this.router.navigate(['/book-caretaker']); */
    /*this.dateCaretakerStorageService.sendCaretakerData(this.caretaker);
    console.warn(this.caretaker);*/
    console.log(this.login.isLoggedIn());
    if(this.login.isLoggedIn()) {
      localStorage.setItem('caretaker', JSON.stringify(this.caretaker));
      this.router.navigate(['/book-caretaker']);
    }
    else {
      this.router.navigate(['/login']);
    }
  }
}


