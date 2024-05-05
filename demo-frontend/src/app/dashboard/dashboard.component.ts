import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardService } from './dashboard.service';
import { MonthMap, Product } from './dashboardTypes';
import { NgxChartsModule, ScaleType } from '@swimlane/ngx-charts';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    NgxChartsModule,
    CommonModule,
  ],
  providers: [DashboardService],
  templateUrl: './dashboard.component.html',
})

export class DashboardComponent implements OnInit{
    products: Product[] = []
    monthMap: MonthMap = {}
    productsData: any[] = [];
    productView: [number, number] = [700, 400];
    monthsData: any[] = []
    monthsView: [number, number] = [700, 400];

    // chart options
    animations = false;
    showXAxis = true;
    showYAxis = true;
    gradient = false;
    showXAxisLabel = true;
    xAxisLabelProduct = 'Product';
    xAxisLabelMonth = 'Month';
    showYAxisLabel = true;
    yAxisLabelProduct = 'Quantity Sold';
    yAxisLabelMonth = 'Sales';
    colorScheme = {
        name: "myScheme",
        selectable: true,
        group: ScaleType.Ordinal,
        domain: ['#5AA454', '#A10A28', '#C7B42C', '#AAAAAA']
      };

    constructor(private dashboardService: DashboardService) {}

    ngOnInit() {
        this.getProducts();
        this.getMonths();
      }

    getProducts(): void {
        this.dashboardService.getProducts().subscribe(productMap => {
          this.products = Object.values(productMap);
          this.productsData = Object.values(productMap).map(product => ({name: product.itemDescription, value: product.totalQuantitySold})).sort((a,b) => b.value - a.value ).slice(0,10);
        });
    }

    getMonths(): void {
        this.dashboardService.getMonths().subscribe(monthMap => {
          this.monthMap = monthMap;
          this.monthsData = [({name: "Sales", series: Object.entries(monthMap).map((entry) => ({name: entry[0], value: entry[1].totalSales}))})]
    });
        
    }
}
