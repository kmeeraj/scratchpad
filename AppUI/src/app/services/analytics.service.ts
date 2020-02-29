import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AnalyticsService {

  constructor(private http: HttpClient) { }

  getAnalytics(): Observable<any> {
    return this.http.get<any>(`/api/analytics`);
  }

  getTimes(): Observable<any> {
    return this.http.get<any>(`/time/all`);
  }
  runAnalytics(value: any): Observable<any> {
    return this.http.post('/run/runAnalytics' , value);
  }

  runAll() {
    return this.http.post('/run/runAll', 1);
  }
}
