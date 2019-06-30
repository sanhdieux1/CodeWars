import {RunnerInfo} from "./runner-info";

export interface EventInfo {
    competitionId: string,
    eventId: string,
    eventName: string,
    listRunner: Array<RunnerInfo>,
    openDate: number,
    status: string,
    inplay: boolean,
    link: string,
    marketId: string,
    liveStreaming: boolean,
    liveTV: boolean
}
