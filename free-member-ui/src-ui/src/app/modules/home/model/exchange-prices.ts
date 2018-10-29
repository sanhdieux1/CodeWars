import {OddInfo} from "./odd-info";

export interface ExchangePrices {
    availableToBack: Array<OddInfo>,
    availableToLay: Array<OddInfo>,
}