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
                'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
            ],
            title:{
                text:'Usage'
            }
        },
        yAxis: {
          title: {
              text:"Cost"
          }
        },
        series: [{
            name: 'AWS',
            data: [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6],
            type: undefined
        },
        {
            name: 'AZURE',
            data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8],
            type: undefined
        }
    ]
    };
    chart: Chart ;
    ngOnInit(){
        this.chart = new Chart(this.highChartsOptions);
    }
   
}