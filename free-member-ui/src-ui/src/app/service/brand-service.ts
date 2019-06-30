import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/internal/Observable";
import {share, tap} from "rxjs/operators";
import {of} from "rxjs/internal/observable/of";

export interface BrandInfo{
    brandId:  number;
    brandName: string;
}

@Injectable()
export class BrandService {

    private observable: Observable<BrandInfo>;
    private brandInfo : BrandInfo;
    constructor(private http: HttpClient) {
    }

    public currentBrand() : Observable<BrandInfo>{
        if(!!this.brandInfo){
            return of(this.brandInfo);
        }
        if(!this.observable) {
            this.observable = this.http.get<BrandInfo>('/free-member-service/brand-info.json').pipe(tap(rs => this.brandInfo = rs), share());
        }
        return this.observable;
    }
}