import {Component} from '@angular/core';
import { Chart } from 'angular-highcharts';
@Component({
selector:'cost',
templateUrl:'./cost.component.html',
styleUrls:['./cost.component.scss']
})
export class CostComponent {
    highChartsOptions =
    {
        chart: {
            renderTo: 'container',
            type: 'line',
            options3d: {
                enabled: true,
               
                viewDistance: 25
            },
            height:'40%'
        },
        title: {
            text: 'Usage vs Cost'
        },
       
        plotOptions: {
            column: {
                depth: 50,
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        xAxis: {
            categories: [
                '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'
            ],
            title:{
                text:'Usage'
            }
        },
        yAxis: {
          title: {
              text:"Cost ($)"
          }
        },
        series: [{
            name: 'AWS(2)',
            data: [200, 100, 400,900, 1300, 1600, 2000, 2015 ],
            type: undefined
        },
        {
            name: 'AZURE(2)',
            data: [90, 100, 200, 500, 800, 1200, 1400, 2000],
            type: undefined
        },
        {
            name: 'AWS(1)',
            data: [700, 600, 900, 1400, 1800, 2100, 2500, 2600 ],
            type: undefined
        },
        {
            name: 'AZURE(1)',
            data: [300, 400, 500, 800, 1100, 1500, 1700, 2300],
            type: undefined
        }
    ]
    };
    chart: Chart ;
    ngOnInit(){
        this.chart = new Chart(this.highChartsOptions);
    }
   
}