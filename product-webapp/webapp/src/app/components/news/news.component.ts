import { Component, OnInit } from '@angular/core';
import { NewsService } from 'src/app/services/news.service';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css'],
  providers: [NewsService]
})
export class NewsComponent implements OnInit {
  newsIndiaArticles:Array<any>;
  newsUsArticles:Array<any>;
  newsUkArticles:Array<any>;
  newsAustraliaArticles:Array<any>;
  newsCanadaArticles:Array<any>;
  newsSingaporeArticles:Array<any>;
  newsMalaysiaArticles:Array<any>;
  

  constructor(private newsService: NewsService) { }

  ngOnInit(): void {
   this.newsService.fetchIndiaNews()
    .subscribe(
      response => this.newsIndiaArticles = response['articles']
    );

    this.newsService.fetchUsNews()
    .subscribe(
      response => this.newsUsArticles = response['articles']
    );

    this.newsService.fetchUkNews()
    .subscribe(
      response => this.newsUkArticles = response['articles']
    );

    this.newsService.fetchAustraliaNews()
    .subscribe(
      response => this.newsAustraliaArticles = response['articles']
    );

    this.newsService.fetchCanadaNews()
    .subscribe(
      response => this.newsCanadaArticles = response['articles']
    );

    this.newsService.fetchSingaporeNews()
    .subscribe(
      response => this.newsSingaporeArticles = response['articles']
    );

    this.newsService.fetchMalaysiaNews()
    .subscribe(
      response => this.newsMalaysiaArticles = response['articles']
    );

    
  }

}
