import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HomeComponent} from './modules/home/home.component';
import {MobileHomeComponent} from "./modules/mobile-home/mobile-home.component";
import {ModalModule, TabsModule} from "ngx-bootstrap";
import {AppService} from "./service/app.service";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {SportHighlightComponent} from "./modules/mobile-home/sport-highlight/sport-highlight.component";
import {MarketComponent} from "./modules/market/market.component";
import {OddsComponent} from './modules/home/component/odds/odds.component';
import {EventComponent} from './modules/home/component/event/event.component';
import {HomeGuardianGuard} from "./service/home-guardian.guard";
import {DeviceDetectorModule} from "ngx-device-detector";
import {CachingInterceptor} from "./caching-interceptor";
import {MarketRuleComponent} from "./modules/dialog/market-rule/market-rule.component";
import {HighlightDirective} from "./directive/highlight.directive";
import {BrandService} from "./service/brand-service";
import {CookieService} from "./service/cookie.service";

export const httpInterceptorProviders = [
    {provide: HTTP_INTERCEPTORS, useClass: CachingInterceptor, multi: true}
];
@NgModule({
    declarations: [
        AppComponent,
        HomeComponent,
        MobileHomeComponent,
        SportHighlightComponent,
        MarketComponent,
        OddsComponent,
        EventComponent,
        MarketRuleComponent,
        HighlightDirective
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        TabsModule.forRoot(),
        HttpClientModule,
        DeviceDetectorModule.forRoot(),
        ModalModule.forRoot()
    ],
    providers: [
        AppService,
        httpInterceptorProviders,
        BrandService,
        HomeGuardianGuard,
        { provide: "Window", useValue: window},
        CookieService
    ],
    bootstrap: [AppComponent],
    entryComponents: [MarketRuleComponent]
})
export class AppModule {
}
