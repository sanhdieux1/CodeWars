import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";

const routes : Routes = [
  {
    path: '',
    redirectTo: 'sass-demo',
    pathMatch: 'full'
  },
  {
    path: "chat",
    loadChildren: "../app/modules/chat/chat.module#ChatModule"
  },
  {
    path: 'sass-demo',
    loadChildren: "../app/modules/sass-demo/sass-demo.module#SassDemoModule"
  }
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)]
})
export class AppRoutingModule {

}
