import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {WebsocketModule} from "../websocket/websocket.module";
import {FormsModule} from "@angular/forms";
import {SassDemoRoutingModule} from "./sass-demo.routing.module";
import {SassDemoComponent} from "./sass-demo.component";
import {VarialesComponent} from "./variales/variales.component";

@NgModule({
  imports:[
    SassDemoRoutingModule,
    CommonModule,
    FormsModule,
    WebsocketModule
  ],
  declarations: [SassDemoComponent, VarialesComponent]
})
export class SassDemoModule{}
