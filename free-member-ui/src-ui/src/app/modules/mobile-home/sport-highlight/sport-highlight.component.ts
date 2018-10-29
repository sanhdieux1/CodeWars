import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {EventInfo} from "../../home/model/event-info";
import {AppService} from "../../../service/app.service";

@Component({
    selector: 'app-sport-highlight',
    templateUrl: './sport-highlight.component.html',
    styleUrls: ['./sport-highlight.component.scss']
})
export class SportHighlightComponent implements OnInit, OnDestroy {

    private _sportId: number;
    eventHighlights: Array<EventInfo> = [];
    isLoaded: boolean = false;

    @Input() set sportId(value: number) {
        this.eventHighlights = [];
        if (value > 0) {
            this.isLoaded = false;
            this._sportId = value;
            this.getEventMarket();
        }
    }

    get sportId(): number {
        return this._sportId;
    }

    constructor(private http: HttpClient, private appService: AppService) {

    }

    ngOnInit(): void {
    }

    ngOnDestroy(): void {
    }

    getEventMarket(): void {
        this.appService.getEventHighlightForSport(this._sportId).subscribe(
            data => {
                this.eventHighlights = data;
                this.eventHighlights.forEach(m => {
                    m.link = `${this.sportId}/event/${m.eventId}`;
                });
                this.isLoaded = true;
            });
    }

    getEventName(event: EventInfo): string{
        return this.appService.getEventName(event);
    }

    friendlyTime(time: number){
        return this.appService.friendlyTime(time);
    }
}
