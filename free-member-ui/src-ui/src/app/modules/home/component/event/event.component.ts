import {ChangeDetectionStrategy, Component, Input, OnInit} from '@angular/core';
import {EventInfo} from "../../model/event-info";
import {BsModalService} from "ngx-bootstrap";
import {MarketRuleComponent, MarketRuleModel} from "../../../dialog/market-rule/market-rule.component";
import {AppService} from "../../../../service/app.service";

@Component({
    selector: 'app-event',
    templateUrl: './event.component.html',
    styleUrls: ['./event.component.scss'],
    changeDetection: ChangeDetectionStrategy.OnPush
})
export class EventComponent implements OnInit {

    _event: EventInfo;

    eventName: string = '';
    @Input()
    maxRunner: number;

    @Input()
    set event(obj: EventInfo) {
        if (obj !== undefined) {
            this._event = obj;
            this.eventName = this.appService.getEventName(this._event);
        }
    }

    constructor(private modalService: BsModalService,
                public appService: AppService) {
    }

    ngOnInit() {
    }


    clickRule(marketId: string): void {
        this.appService.getMarketRule(marketId, "Bodog").subscribe(
            data => {
                let result: MarketRuleModel = data;
                if (result.rules !== null) {
                    result.rules = result.rules.replace(/<a(?:.*?)>/g, '');
                    result.rules = result.rules.replace(/<\/a>/g, '');
                    result.rules = result.rules.replace(/Betfair SP is available for this market, standard Betfair SP rules apply:/g, '');
                    result.rules = result.rules.replace(/http:\/\/www.betfair.com\/en\/aboutUs\/Rules.and.Regulations\//g, '');
                    result.rules = result.rules.replace(/https:\/\/promo.betfair.com\/betfairsp\/FAQs_theBasics.html/g, '');
                    result.rules = result.rules.replace(/images\/en_GB/g, "/xch/public/images/en_GB");
                    result.rules = result.rules.replace(/Betfair/g, 'Faircricket ');
                    if (result.marketType.indexOf("PLACE") >= 0 || result.marketType.indexOf("EACH_WAY") >= 0) {
                        result.rulesDescription = result.numberOfWinner + " To be Placed";
                    } else {
                        result.rulesDescription = 'Win Only Market';
                    }
                } else {
                    result.rules = '';
                }
                this.modalService.show(MarketRuleComponent, {initialState: {data: result}});
            });
    }
}
