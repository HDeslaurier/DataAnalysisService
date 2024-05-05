import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MonthMap, ProductMap } from './dashboardTypes';

  @Injectable()
  export class DashboardService {
    serviceBaseUrl = "http://localhost:8080/";

    constructor(private http: HttpClient) {}

    /* Get map of product data */
    getProducts(): Observable<ProductMap> {
        return this.http.get<ProductMap>(this.serviceBaseUrl + "products");
    } 

    /* Get map of sales by month */
    getMonths(): Observable<MonthMap> {
        return this.http.get<MonthMap>(this.serviceBaseUrl + "salesByMonth")
    }
  }