import {Injectable} from "@angular/core";
import {HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";

@Injectable()
export class CachingInterceptor implements HttpInterceptor {
  constructor() {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {


    let headersNoCache = req.headers.set('Pragma', 'no-cache');
    headersNoCache = headersNoCache.set('Expires', (new Date(0)).toUTCString());
    headersNoCache = headersNoCache.set('Cache-Control', 'no-cache')
    let authReq = req.clone({
      headers : headersNoCache
    });
    // send cloned request with header to the next handler
    return next.handle(authReq);
  }
}
