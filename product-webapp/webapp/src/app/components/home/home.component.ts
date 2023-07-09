import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  flipped1: boolean = false;
  flipped2: boolean = false;
  flipped3: boolean = false;
  
  constructor() { }

  ngOnInit(): void {
  }
  // var swiper = new Swiper('.swiper-container', {
  //   effect: 'cover'
  // })
}
