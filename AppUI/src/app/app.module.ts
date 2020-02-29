import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule,MatIconModule, MatListModule , MatToolbarModule, MatCardModule, MatSelectModule, MatButtonModule,MatSnackBarModule } from '@angular/material';
import { CovalentLayoutModule } from '@covalent/core';
import { CovalentCodeEditorModule } from '@covalent/code-editor';
import { AppComponent } from './app.component';
import { MainComponent } from 'src/main/main.component';
import { GraphsComponent } from 'src/graphs/graphs.component';
import {ChartModule , HIGHCHARTS_MODULES} from 'angular-highcharts';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import * as threed from 'highcharts/highcharts-3d';
import { HomeComponent } from './home/home.component';
import { CostComponent } from './cost/cost.component';
@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    GraphsComponent,
    HomeComponent,
    CostComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CovalentLayoutModule,
    CovalentCodeEditorModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatIconModule,
    MatListModule,
    MatToolbarModule,
    ChartModule,
    MatCardModule,
    MatSelectModule,
    MatButtonModule,
    MatInputModule,
    MatSnackBarModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [{ provide: HIGHCHARTS_MODULES, useFactory: () => [ threed ] }],
  bootstrap: [AppComponent]
})
export class AppModule { }
