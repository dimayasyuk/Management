import {Injectable} from '@angular/core';
import {Router} from "@angular/router";
import {
  HttpErrorResponse, HttpEvent,
  HttpHandler, HttpHeaderResponse,
  HttpInterceptor, HttpProgressEvent,
  HttpRequest,
  HttpResponse, HttpSentEvent,
  HttpUserEvent
} from "@angular/common/http";
import {Observable} from "rxjs";
import {TokenStorageService} from "../token/token.storage.service";

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  constructor(private token: TokenStorageService, private router: Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpSentEvent> {
    let authReq = req;
    if (this.token.getToken() != null) {
      authReq = req.clone({headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this.token.getToken())});
    }
    // return next.handle(authReq).do(
    //   (err: any) => {
    //     if (err instanceof HttpErrorResponse) {
    //       console.log(err);
    //       console.log('req url :: ' + req.url);
    //       if (err.status === 401) {
    //         this.router.navigate(['projects']);
    //       }
    //     }
    //   }
    // );
    return null;
  }
}
