import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {DeviceDetectorService} from "ngx-device-detector";


@Injectable({
    providedIn: 'root'
})

export class HomeGuardianGuard implements CanActivate {
    deviceInfo = null;
    constructor(private router: Router,private deviceService: DeviceDetectorService) {
    }

    canActivate(next: ActivatedRouteSnapshot,
                state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        this.deviceInfo = this.deviceService.getDeviceInfo();
        const isMobile = this.deviceService.isMobile();
        const isTablet = this.deviceService.isTablet();
        const isDesktopDevice = this.deviceService.isDesktop();
        if(isMobile) {
            this.router.navigate(['/exchange']);
        } else {
            this.router.navigate(['/home']);
        }
        return true;
    }

}
