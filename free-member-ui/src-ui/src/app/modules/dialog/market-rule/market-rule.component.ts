import {Component, Input, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";

@Component({
    selector: 'app-market-rule',
    templateUrl: './market-rule.component.html',
    styleUrls: ['./market-rule.component.scss']
})
export class MarketRuleComponent implements OnInit {

    @Input() data: MarketRuleModel;

    activeModal: BsModalService;

    constructor(activeModal: BsModalService,
        public bsModalRef: BsModalRef ) {
        this.activeModal = activeModal;
    }


    ngOnInit() {
    }

}

export interface MarketRuleModel {
    bettingType: string,
    marketId: string,
    marketName: string,
    marketStartTime: number,
    marketType: string,
    numberOfWinner: number,
    rules: string,
    rulesDescription:string
}