import { Component, ViewChild, ElementRef } from '@angular/core';
import { Chart } from 'angular-highcharts';
import ForceGraph3D from '3d-force-graph';
import { dummyjson, realjson } from '../json/miserables';
import { FormGroup, FormControl } from '@angular/forms';
@Component({
    selector:'graphs',
    templateUrl:'./graphs.component.html',
    styleUrls:['./graphs.component.scss']
})
export class GraphsComponent {

    cloudPerf = new FormGroup({
        category: new FormControl(''),
        function: new FormControl(''),
        cloud: new FormControl('')
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
                depth: 25,
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        xAxis:{
            categories:[
                
            ]
        },
        series: [{
            name:'AWS',
            data: []
        },
        {
            name:'AZURE',
            data:[]
        }
    ]
    }
  chart: Chart = new Chart(this.highChartsOptions);

  categories = [];
  functions = [];
  clouds=["AWS","Azure","GCP"]

  sampleData;
  //myGraph;
  sampleCats={
    "Analytics": [{
        "category": "Data Preparation and Transformation",
        "functions": [{
            "functionName": "antiselect",
            "query": [
              "select * from antiselect( on antiselect_test Using Exclude('a') )as dt order by 1,2,3;",
              "select * from antiselect( on antiselect_test Using Exclude('d') )as dt order by 1,2,3;"
            ]
          },
          {
            "functionName": "unpack",
            "query": [
              "SELECT packed_data, cast(gender as varchar(1000)) as gender, cast(race as varchar(1000)) as race, numBuys, numSells, id, src FROM unpack( ON unpack_input using TargetColumn('packed_data') OutputColumns('packed_data','gender','race','numBuys','numSells') OutputDataTypes('INTEGER','varchar','varchar','INTEGER','INTEGER') Regex('(.*)') RegexSet(1) IgnoreInvalid('False') )as dt order by 1,2,3,4,5,6,7;"
            ]
          }
        ]
      },
      {
        "category": "Path and Pattern Analysis",
        "functions": [{
          "functionName": "Attribution",
          "query": ["SELECT * FROM Attribution( ON attribution_sample_table AS \"input\" PARTITION BY user_id ORDER BY time_stamp ON attribution_sample_table2 AS \"input2\" PARTITION BY user_id ORDER BY time_stamp ON conversion_event_table AS \"ConversionEventTable\" DIMENSION  ON excluding_event_table AS \"ExcludedEventTable\" DIMENSION  ON optional_event_table AS \"OptionalEventTable\" DIMENSION  ON model1_table AS \"FirstModel\" DIMENSION  using EventColumn('event') TimeColumn('time_stamp') WindowSize('rows:10') ) as dt order by 1,2,3,4,5;"]
        }]
      }
    ]
  }

  ngOnInit(){

    this.categories= this.sampleCats["Analytics"].map(cat => cat.category);

    this.sampleData=realjson;

    this.sampleData.forEach((element,index) => {
        this.highChartsOptions.xAxis.categories.push(element["functionName"]);
        this.highChartsOptions.series[0].data.push(element["times"][0]["timeTakenMs"]);
        this.highChartsOptions.series[1].data.push(element["times"][1]["timeTakenMs"]);
    });


    this.cloudPerf.get('category').valueChanges.subscribe(
        val =>{
            this.functions = this.getFunctions(val)
        }
    )
    
   // this.myGraph = ForceGraph3D()(this.child.nativeElement).graphData(dummyjson);
  }

  getFunctions(val: any ){
    let filteredCat =  this.sampleCats["Analytics"].filter(cat => cat.category == val);
    return filteredCat[0]["functions"]
  }

}