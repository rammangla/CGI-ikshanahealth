import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
// import { News } from '../models/news.model';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(private http: HttpClient) { }

  fetchIndiaNews() {
    return this.http.get('https://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=4bf671dda342429589cc52093ef7ed84');
  }

  fetchUsNews() {
    return this.http.get('https://newsapi.org/v2/top-headlines?country=us&category=health&apiKey=4bf671dda342429589cc52093ef7ed84');
  }

  fetchUkNews() {
    return this.http.get('https://newsapi.org/v2/top-headlines?country=gb&category=health&apiKey=4bf671dda342429589cc52093ef7ed84');
  }

  fetchAustraliaNews() {
    return this.http.get('https://newsapi.org/v2/top-headlines?country=au&category=health&apiKey=4bf671dda342429589cc52093ef7ed84');
  }

  fetchCanadaNews() {
    return this.http.get('https://newsapi.org/v2/top-headlines?country=ca&category=health&apiKey=4bf671dda342429589cc52093ef7ed84');
  }

  fetchSingaporeNews() {
    return this.http.get('https://newsapi.org/v2/top-headlines?country=sg&category=health&apiKey=4bf671dda342429589cc52093ef7ed84');
  }

  fetchMalaysiaNews() {
    return this.http.get('https://newsapi.org/v2/top-headlines?country=my&category=health&apiKey=4bf671dda342429589cc52093ef7ed84');
  }

}
