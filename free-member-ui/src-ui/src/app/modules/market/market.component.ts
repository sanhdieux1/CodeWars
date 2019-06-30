import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {EventInfo} from "../home/model/event-info";
import {AppService} from "../../service/app.service";

@Component({
    selector: 'app-market',
    templateUrl: './market.component.html',
    styleUrls: ['./market.component.scss']
})
export class MarketComponent implements OnInit, OnDestroy {

    loadReady: boolean = false;
    eventId: number;
    sportId: number;
    market: EventInfo;
    isLoaded: boolean = false;
    private intervalRefreshOdds: number;

    constructor(private http: HttpClient,
                private route: ActivatedRoute,
                private appService: AppService,
                private router: Router) {
        route.params.subscribe(params => {

            this.market = null;
            this.eventId = params.eventId;
            this.sportId = params.sportId;
            this.getMarkets();
        });
        this.intervalRefreshOdds = window.setInterval(() => this.getMarkets(), REFRESH_ODDS_INTERVAL);
    }

    ngOnInit(): void {
    }

    ngOnDestroy(): void {
        clearInterval(this.intervalRefreshOdds);
    }

    getMarkets(): void {
        this.appService.getMarketHighlight(this.eventId).subscribe(
            data => {
                this.market = data;
                this.isLoaded = true;
            }, error =>{
                if(error.status == 401 && !!this.intervalRefreshOdds){
                    clearInterval(this.intervalRefreshOdds);
                }
            });
    }
    goBack(sportId):void {
        //window.history.back();
        this.router.navigate(['/exchange'], { queryParams: { sportId: sportId } });
    };
    trackByIndex(index, item): number {
        return index;
    }
    friendlyTime(time: number){
        return this.appService.friendlyTime(time);
    }
}
const REFRESH_ODDS_INTERVAL: number = 10000;
