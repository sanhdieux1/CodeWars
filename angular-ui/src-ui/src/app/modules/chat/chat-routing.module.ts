import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {ChatComponent} from "./chat.component";

const routes : Routes = [
  {
    path:"",
    component: ChatComponent
  }
]

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forChild(routes)]
})
export class ChatRoutingModule {

}
