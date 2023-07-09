import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { ServiceTypeComponent } from '../service-type/service-type.component';
import { FormBuilder, FormControl, FormGroup,Validators } from '@angular/forms';
import { SearchService } from 'src/app/services/search.service';
import { SearchResponse } from 'src/app/models/searchresponse.model';
import { Doctor } from 'src/app/models/doctor.model';
import { Caretaker } from 'src/app/models/caretaker.model';
import {STEPPER_GLOBAL_OPTIONS} from '@angular/cdk/stepper';

@Component({
  selector: 'app-location',
  templateUrl: './location.component.html',
  styleUrls: ['./location.component.css'],
  providers: [{
    provide: STEPPER_GLOBAL_OPTIONS, useValue: {displayDefaultIndicatorType: false}
  }]
})
export class LocationComponent implements OnInit {
  hideMe:boolean=true;
  isLinear=false;
  showMe:boolean=false;
  search:FormGroup;
  location:string;
  typeOfService:string;
  searchResponse:SearchResponse;
  doctorsList: Doctor[];
  caretakersList:Caretaker[];
  constructor(private router: Router,public dialog: MatDialog,private fb:FormBuilder,private searchService: SearchService ) { }

  ngOnInit(): void {
    this.search=this.fb.group({
      location: new FormControl('', [Validators.required]),
      typeOfService: new FormControl('')
    });
    this.doctorsList=[]
    this.caretakersList=[]
  }
  toggleTag()
  {
    this.showMe = !this.showMe;
    this.hideMe= !this.hideMe;
  }
  onSubmit():any{
    if(this.search.valid){
    this.location=this.search.get('location').value;
    this.typeOfService=this.search.get('typeOfService').value;
    console.log(this.location);
    console.log(this.typeOfService);
    if(this.typeOfService=="doctor"){
      this.searchService.getlistsByLocation(this.location).subscribe(
        (response: SearchResponse) => this.searchResponse=response
      );
    }
    else{
    this.searchService.getlistsBySpecializaion(this.location,this.typeOfService).subscribe((response: SearchResponse) => this.searchResponse=response);
    }
    console.log(this.searchResponse);
    this.doctorsList=this.searchResponse.doctorList;
    this.caretakersList=this.searchResponse.caretakerList;
    if(this.typeOfService=="doctor"){
      this.caretakersList=[];
    }
   }
    // console.log(this.searchResponse);
    console.log(this.caretakersList);
    console.log(this.doctorsList);
  }}
