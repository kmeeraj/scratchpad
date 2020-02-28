import { Component, ViewChild, ElementRef } from '@angular/core';
import { Chart } from 'angular-highcharts';
import ForceGraph3D from '3d-force-graph';
import { dummyjson } from '../json/miserables';
import { FormGroup, FormControl } from '@angular/forms';
@Component({
    selector:'graphs',
    templateUrl:'./graphs.component.html',
    styleUrls:['./graphs.component.scss']
})
export class GraphsComponent {

    cloudPerf = new FormGroup({
        category: new FormControl(''),
        function: new FormControl('')
    })
    @ViewChild('child',{static:true})child: ElementRef;
    highChartsOptions: any =
    {
        chart: {
            renderTo: 'container',
            type: 'column',
            options3d: {
                enabled: true,
                alpha: 15,
                beta: 15,
                depth: 50,
                viewDistance: 25
            }
        },
        title: {
            text: 'Chart rotation demo'
        },
        subtitle: {
            text: 'Test options by dragging the sliders below'
        },
        plotOptions: {
            column: {
                depth: 25
            }
        },
        xAxis:{
            categories:[
                'F1','F2','F3'
            ]
        },
        series: [{
            name:'AWS',
            data: [29.9, 71.5, 106.4]
        },
        {
            name:'AZURE',
            data:[194.1, 95.6, 54.4]
        }
    ]
    }
  chart: Chart = new Chart(this.highChartsOptions);

  categories = ['a','b','c'];
  functions = ['1','2','3'];
  myGraph;

  ngOnInit(){
    

    this.myGraph = ForceGraph3D()(this.child.nativeElement).graphData(dummyjson);
  }

}