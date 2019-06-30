import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from "./modules/home/home.component";
import {MobileHomeComponent} from "./modules/mobile-home/mobile-home.component";
import {MarketComponent} from "./modules/market/market.component";
import {HomeGuardianGuard} from "./service/home-guardian.guard";

const routes: Routes = [
    {
        path: '',
        component: HomeComponent,
        canActivate: [HomeGuardianGuard]
    },
    {
        path: 'home',
        component: HomeComponent,
    },
    {
        path: 'exchange',
        component: MobileHomeComponent,
    },
    {
        path: 'exchange/:sportId/event/:eventId',
        component: MarketComponent,
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes, {useHash: true, enableTracing: false})],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
