import {EventInfo} from "./event-info";

export interface SportInfo {
    sportName: string,
    sportId: number,
    events: Array<EventInfo>
}