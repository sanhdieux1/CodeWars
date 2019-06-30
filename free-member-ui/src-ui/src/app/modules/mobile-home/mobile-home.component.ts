import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AppService} from "../../service/app.service";
import {SportInfo} from "../home/model/sport-info";
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'app-mobile-home',
    templateUrl: './mobile-home.component.html',
    styleUrls: ['./mobile-home.component.scss'],
    encapsulation:ViewEncapsulation.None
})
export class MobileHomeComponent implements OnInit {

    listSport: Array<SportInfo>;
    selectedSport: number = 0;

    constructor(private appService: AppService,
                private route: ActivatedRoute,) {
        route.queryParams.subscribe(queryParams => {
            this.selectedSport = queryParams.sportId || 0;
        });
        this.appService.getSportHighlight().subscribe(
            data => {
                this.listSport = data || [];
                if (this.selectedSport === 0 && this.listSport.length > 0) {
                    this.selectedSport = this.listSport[0].sportId;
                }
            });
    }

    ngOnInit() {
    }

    callClick(sportId) {
        this.selectedSport = sportId;
    }

}
