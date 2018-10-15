import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {ChatComponent} from "./chat.component";
import { LeftMenuComponent } from './left-menu/left-menu.component';
import {ChatRoutingModule} from "./chat-routing.module";
import { MainComponent } from './main/main.component';
import {WebsocketModule} from "../websocket/websocket.module";
import { UserInputBoxComponent } from './user-input-box/user-input-box.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  imports:[
    ChatRoutingModule,
    CommonModule,
    FormsModule,
    WebsocketModule
  ],
  declarations: [ChatComponent, LeftMenuComponent, MainComponent, UserInputBoxComponent]
})
export class ChatModule{}
