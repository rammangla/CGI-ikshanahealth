import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup,Validators } from '@angular/forms';
import { SearchService } from 'src/app/services/search.service';
import { SearchResponse } from 'src/app/models/searchresponse.model';
import { Doctor } from 'src/app/models/doctor.model';
import { Caretaker } from 'src/app/models/caretaker.model';
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  search:FormGroup;
  location:string;
  typeOfService:string;
  specialization:string;
  searchResponse:SearchResponse;
  doctorsList: Doctor[];
  caretakersList:Caretaker[];
  showdoctorDropdown:boolean=false;
  showcaretakerDropdown:boolean=false;
  
  showdoctor:boolean=true;
  showcaretaker:boolean=true;
  selectedValue:string='';
  constructor(private fb: FormBuilder,private searchService: SearchService) { }
  
  
  ngOnInit(): void {
    this.search = this.fb.group({
      location: new FormControl('', [Validators.required ]),
      typeOfService: new FormControl(''),
      specialization:new FormControl('')
    });
    this.doctorsList=[]
    this.caretakersList=[]
        
  }

  onSubmit(): any{

    this.location=this.search.get('location').value;
    
    this.specialization=this.search.get('specialization').value;

    this.typeOfService=this.search.get("typeOfService").value;
    // console.log(this.typeOfService);
    // console.log(this.showcaretaker);
    // console.log(this.showdoctor);
    if(this.typeOfService==="doctor"){
    
      this.showdoctorDropdown=true;
      this.showcaretakerDropdown=false;
      this.showdoctor=true;
      this.showcaretaker=false;
    }
    if(this.typeOfService==="caretaker"){
      this.showcaretakerDropdown=true;
      this.showdoctorDropdown=false;
      this.showdoctor=false;
      this.showcaretaker=true;
    }
    if(this.typeOfService==="none"){
      this.showcaretakerDropdown=false;
      this.showdoctorDropdown=false;
      this.showcaretaker=true;
      this.showdoctor=true;
      this.specialization="none"
    }

    console.log(this.typeOfService);
    console.log(this.showcaretaker);
    console.log(this.showdoctor);

    // console.log(this.location);
    // console.log(this.typeOfService);
    // console.log(this.specialization);
    
    if(this.specialization=="none"){
    this.searchService.getlistsByLocation(this.location).subscribe(
      (response: SearchResponse) => this.searchResponse=response

    );}
    else{
    this.searchService.getlistsBySpecializaion(this.location,this.specialization).subscribe((response: SearchResponse) => this.searchResponse=response
    );
    } 
    console.log(this.searchResponse);
    this.doctorsList=this.searchResponse["doctorList"];
    this.caretakersList=this.searchResponse["caretakerList"];

  }

}
