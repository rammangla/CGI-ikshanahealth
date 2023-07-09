import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-service-type',
  templateUrl: './service-type.component.html',
  styleUrls: ['./service-type.component.css']
})
export class ServiceTypeComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  goBack(){
    this.router.navigate(['/home'])
  }
}
