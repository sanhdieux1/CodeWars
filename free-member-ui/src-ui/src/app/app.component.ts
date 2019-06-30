import {Component} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {BrandService} from "./service/brand-service";
import {DeviceDetectorService} from "ngx-device-detector";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {
    constructor(private title: Title, private brandService: BrandService, private deviceService: DeviceDetectorService) {
        this.brandService.currentBrand().subscribe(brand => {
            if(deviceService.isDesktop() && !!brand) {
                this.title.setTitle(''.concat(brand.brandName).concat(' Exchange'));
            }
        });
    }
}
