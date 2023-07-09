import { Component, OnInit } from '@angular/core';
import { Caretaker } from 'src/app/models/caretaker.model';
import { CaretakerServiceService } from 'src/app/services/caretaker-service.service';
@Component({
  selector: 'app-viewcaretakers',
  templateUrl: './viewcaretakers.component.html',
  styleUrls: ['./viewcaretakers.component.css']
})
export class ViewcaretakersComponent implements OnInit {
  caretakersList:Caretaker[];
  errorMessage: any;

  constructor(private caretakerService:CaretakerServiceService) { }
  ngOnInit(): void {
    this.caretakerService.getCaretakers().subscribe(
      (response: Caretaker[])=> this.caretakersList=response,
      (error) => this.errorMessage = error
    );
    console.log(this.caretakersList);
  }
}