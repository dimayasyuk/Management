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
import {TokenStorageService} from "./service/token/token.storage.service";
import {tap} from "rxjs/operators";

const TOKEN_HEADER_KEY = 'authorization';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  constructor(private token: TokenStorageService, private router: Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;

    if (this.token.getToken() != null) {
      authReq = req.clone({headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this.token.getToken())});
    }

    return next.handle(authReq).pipe(tap(() => {},
      (err: any) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status === 401) {
            this.router.navigate(['/authorization']);
            alert('Unauthorizated');
          }
        }
      }
    ));
  }
}
