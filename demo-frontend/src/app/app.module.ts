import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { HttpClientModule } from '@angular/common/http';
import { importProvidersFrom } from '@angular/core';
import { provideProtractorTestingSupport } from '@angular/platform-browser';


import { AppComponent } from './app.component';

@NgModule({
  imports:      [ 
    BrowserModule, 
    FormsModule,
    NgxChartsModule,
    BrowserAnimationsModule 
],
  declarations: [ AppComponent ],
  providers: [importProvidersFrom(HttpClientModule),provideProtractorTestingSupport()],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
