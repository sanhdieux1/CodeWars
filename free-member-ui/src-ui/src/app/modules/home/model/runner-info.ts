import {ExchangePrices} from "./exchange-prices";

export interface RunnerInfo {
    runnerName: string,
    selectionId: number,
    handicap: number,
    sortPriority: number,
    ex: ExchangePrices
}
