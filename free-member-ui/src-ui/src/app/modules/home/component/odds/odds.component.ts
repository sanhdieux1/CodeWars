import {ChangeDetectionStrategy, Component, Input, OnInit} from '@angular/core';
import {RunnerInfo} from "../../model/runner-info";

@Component({
    selector: 'app-odds',
    templateUrl: './odds.component.html',
    styleUrls: ['./odds.component.scss'],
    changeDetection: ChangeDetectionStrategy.OnPush
})
export class OddsComponent implements OnInit {


    _runnerList: Array<RunnerInfo>;
    @Input()
    maxRunner: number;

    dummyRunner: RunnerInfo = {
        runnerName: "dummy",
        selectionId: 0,
        handicap: 0,
        sortPriority: 0,
        ex: undefined
    };

    @Input()
    set runnerList(obj: Array<RunnerInfo>) {
        if (obj !== undefined) {
            this._runnerList = obj;
            if (this._runnerList.length < 3 && this.maxRunner >= 3) {
                this._runnerList.push(this.dummyRunner);
            }
        }
    }

    constructor() {
    }

    ngOnInit() {
    }

    trackByIndex(index, item): number {
        return index;
    }
}
