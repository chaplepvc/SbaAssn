import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Plan } from '../model/plan';

@Injectable({
  providedIn: 'root'
})
export class PlanService {
  baseUrl: string;
  constructor(private http: Http) {
    this.baseUrl = "http://localhost:6565/plans";
  }

  getBaseUrlById(id: number): string {
    return this.baseUrl + "/" + id;
  }

  getSearchUrl(field: string, value: string): string {
    return this.baseUrl + "/" + field + "/" + value;
  }

  getJsonContentTypeHeader(): RequestOptions {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return new RequestOptions({ headers: headers });
  }

  getAllPlans(): Observable<Plan[]> {
    return this.http.get(this.baseUrl).pipe(
      map(data => data.json())
    );
  }

  searchPlans(field: string, value: string): Observable<Plan[]> {
    return this.http.get(this.getSearchUrl(field,value)).pipe(
      map(data => data.json())
    );
  }

  getPlanById(id: number): Observable<Plan> {
    return this.http.get(this.getBaseUrlById(id)).pipe(
      map(data => data.json())
    );
  }

  deletePlanById(id: number): Observable<Response> {
    return this.http.delete(this.getBaseUrlById(id));
  }

  addPlan(plan: Plan): Observable<Plan> {
    return this.http.post(this.baseUrl, JSON.stringify(plan), this.getJsonContentTypeHeader()).pipe(
      map(data => data.json())
    );
  }

  updatePlan(plan: Plan): Observable<Plan> {
    return this.http.put(this.baseUrl, JSON.stringify(plan), this.getJsonContentTypeHeader()).pipe(
      map(data => data.json())
    );
  }
}
