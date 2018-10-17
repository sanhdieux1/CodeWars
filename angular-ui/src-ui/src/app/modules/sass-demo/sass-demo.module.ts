import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {WebsocketModule} from "../websocket/websocket.module";
import {FormsModule} from "@angular/forms";
import {SassDemoRoutingModule} from "./sass-demo.routing.module";
import {SassDemoComponent} from "./sass-demo.component";
import {VarialesComponent} from "./variales/variales.component";
import {MixinsComponent} from "./mixins/mixins.component";
import {DirectivesComponent} from "./directives/directives.component";
import {SelectionPerformanceComponent} from "./selection-performance/selection-performance.component";
import {NgSelectModule} from "@ng-select/ng-select";

@NgModule({
  imports:[
    SassDemoRoutingModule,
    CommonModule,
    FormsModule,
    WebsocketModule,
      NgSelectModule
  ],
  declarations: [SassDemoComponent, VarialesComponent, MixinsComponent, DirectivesComponent, SelectionPerformanceComponent]
})
export class SassDemoModule{}
