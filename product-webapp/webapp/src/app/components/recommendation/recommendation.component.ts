import { Component, OnInit } from '@angular/core';
import { RecommendationService } from 'src/app/services/recommendation.service';

@Component({
  selector: 'app-recommendation',
  templateUrl: './recommendation.component.html',
  styleUrls: ['./recommendation.component.css'],
  providers: [RecommendationService]
})
export class RecommendationComponent implements OnInit {

  doctorRecommendationList: any;
  emailIdGiven: string;

  constructor(private recommendationService: RecommendationService) { }

  ngOnInit(): void {
    this.emailIdGiven = localStorage.getItem('emailId');
    this.recommendationService.getDoctorRecommendations(this.emailIdGiven)
      .subscribe(
        response => {
          this.doctorRecommendationList = response
          console.log("hello2", this.doctorRecommendationList)
        }
      );
  }

}
