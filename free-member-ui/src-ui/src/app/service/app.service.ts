import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/index";
import {EventInfo} from "../modules/home/model/event-info";
import {SportInfo} from "../modules/home/model/sport-info";
import {MarketRuleModel} from "../modules/dialog/market-rule/market-rule.component";
import * as moment from "moment";
@Injectable()
export class AppService {

    constructor(private http: HttpClient) {
    }

    getSportHighlight(): Observable<Array<SportInfo>>{
        return this.http.get<Array<SportInfo>>('/free-member-service/sport-highlight.json');
    }

    getEventHighlightForSport(sportId): Observable<Array<EventInfo>>{
        return this.http.post<Array<EventInfo>>('/free-member-service/event-highlight.json?sportId='+ sportId,{});
    }
    getMarketHighlight(eventId): Observable<EventInfo>{
        return this.http.post<EventInfo>('/free-member-service/market-highlight.json?eventId='+ eventId,{});
    }
    getMarketRule(marketId, brandName): Observable<MarketRuleModel>{
        return this.http.get<MarketRuleModel>(`/exchange-service/events/get-market-rule?marketId=${marketId}&sp=${brandName}`);
    }

    friendlyTime(time: number): string {
        if(!time){
            return "";
        }
        let currentDay = moment(new Date());
        let timeMoment = moment(time);
        let diffH = timeMoment.diff(currentDay, 'm');
        if (diffH < 60 && diffH >= 0) {
            return 'Starting in '.concat(String(diffH)).concat('\'');
        }
        if (diffH < 0) {
            return 'Starting soon';
        }
        let diffDay = moment(timeMoment.format('YYYY-MM-DD')).diff(moment(currentDay.format('YYYY-MM-DD')), 'd');

        let format = diffDay <= 1 ? "HH:mm" : "dddd HH:mm";
        let dayOfWeek = diffDay === 1 ? 'Tomorrow ' : '';

        return dayOfWeek.concat(timeMoment.format(format));
    }

    getEventName(_event: EventInfo): string{
        return _event.listRunner.length >= 2 ? _event.listRunner[0].runnerName.concat(' v ').concat(_event.listRunner[1].runnerName) : _event.eventName;
    }
}