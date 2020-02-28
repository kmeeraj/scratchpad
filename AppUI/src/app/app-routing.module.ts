import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainComponent } from '../main/main.component';
import { GraphsComponent }  from '../graphs/graphs.component';
const routes: Routes = [{
  path:'',component: MainComponent,
  children:[
    {
      path: 'graphs',
      component: GraphsComponent
    },
   /*  {
      path: '',
      pathMatch: 'full',
      redirectTo: '/graphs',
    }, */
  ]
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
