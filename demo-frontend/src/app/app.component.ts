import { Component } from '@angular/core';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CommonModule } from '@angular/common';
import { NgxChartsModule } from '@swimlane/ngx-charts';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [DashboardComponent, CommonModule, NgxChartsModule],  
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'DataAnalysis';
}
