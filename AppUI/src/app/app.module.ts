import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule, MatListModule , MatToolbarModule, MatCardModule, MatSelectModule, MatButtonModule } from '@angular/material';
import { CovalentLayoutModule } from '@covalent/core';
import { AppComponent } from './app.component';
import { MainComponent } from 'src/main/main.component';
import { GraphsComponent } from 'src/graphs/graphs.component';
import {ChartModule , HIGHCHARTS_MODULES} from 'angular-highcharts';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import * as threed from 'highcharts/highcharts-3d';
@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    GraphsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CovalentLayoutModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatIconModule,
    MatListModule,
    MatToolbarModule,
    ChartModule,
    MatCardModule,
    MatSelectModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [{ provide: HIGHCHARTS_MODULES, useFactory: () => [ threed ] }],
  bootstrap: [AppComponent]
})
export class AppModule { }
