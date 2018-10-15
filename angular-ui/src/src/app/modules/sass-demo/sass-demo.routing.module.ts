import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {SassDemoComponent} from "./sass-demo.component";
import {VarialesComponent} from "./variales/variales.component";

const routes : Routes = [
  {
    path:"",
    component: SassDemoComponent,
    children: [{
      path: 'variables',
      component: VarialesComponent
    }]
  }
]

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forChild(routes)]
})
export class SassDemoRoutingModule {

}
