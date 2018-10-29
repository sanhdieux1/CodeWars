import {Injectable, Inject} from '@angular/core';
import {DOCUMENT} from "@angular/common";

@Injectable()
export class CookieService {

  private accessible: boolean;

  constructor(@Inject(DOCUMENT) private document: any) {
    this.accessible = document
  }

  public getCookie(name: string, defVal: string = ''): string {
    if (!this.accessible) {
      return defVal;
    }
    let cookies: Array<string> = this.document.cookie.split(';');
    let caLen: number = cookies.length;
    let cookieName = `${name}=`;

    for (let i: number = 0; i < caLen; i += 1) {
      let cookie = cookies[i].replace(/^\s+/g, '');
      if (cookie.indexOf(cookieName) == 0) {
        return decodeURIComponent(cookie.substring(cookieName.length, cookie.length));
      }
    }
    return defVal;
  }

  public setCookie(name: string, value: string, expires: number = 0): void {
    let cookieString: string = name + '=' + encodeURIComponent(value) + ';';
    if (expires != 0) {
      const dateExpires: Date = new Date(new Date().getTime() + expires * 1000 * 60 * 60 * 24);
      cookieString += 'expires=' + dateExpires.toUTCString() + ';';
    }
    cookieString += 'path=/;';
    cookieString += 'domain=' + this.getDomain() + ';';
    this.document.cookie = cookieString;
  }

  public deleteCookie(name: string) {
    this.setCookie(name, '', -1);
  }

  private static isIP(input: string): boolean {
    return /^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(input);
  }

  public getDomain(): string {
    let hostname = this.document.location.hostname;
    if (CookieService.isIP(hostname)) {
      return hostname;
    }
    let fields = this.document.location.hostname.split(/\./);
    if (fields.length > 2) {
      fields.shift();
    }
    if (fields.length == 1) {
      return fields.join('.');
    }
    return `.${fields.join('.')}`;
  }

}
