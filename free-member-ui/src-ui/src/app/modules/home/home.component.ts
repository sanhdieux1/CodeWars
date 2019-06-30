import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {SportInfo} from "./model/sport-info";
import {EventInfo} from "./model/event-info";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

    sportList: Array<SportInfo>;
    private intervalRefreshOdds: number;

    constructor(private http: HttpClient) {
        this.intervalRefreshOdds = window.setInterval(() => this.getAllHighlight(), REFRESH_ODDS_INTERVAL);
    }

    ngOnInit() {
        this.getAllHighlight();
    }

    ngOnDestroy(): void {
        clearInterval(this.intervalRefreshOdds);
    }

    getAllHighlight() {
        this.http.get("/free-member-service/all-highlight.json").subscribe(rs => {
            if (rs) {
                this.sportList = rs as Array<SportInfo>;
            }
        } , error => {
            if(error.status == 401 && !!this.intervalRefreshOdds){
                clearInterval(this.intervalRefreshOdds);
            }
        });
    }

    getMaxRunner(events: Array<EventInfo>): number {
        return (events || []).map(e => e.listRunner.length).reduce((a, b) => Math.max(a, b), 2);
    }

    trackByIndex(index, item): number {
        return index;
    }
}
const REFRESH_ODDS_INTERVAL: number = 10000;
