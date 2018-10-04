import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable, from } from 'rxjs';
import { OktaAuthService } from '@okta/okta-angular';

@Injectable()
export class ApiInterceptor implements HttpInterceptor {

  constructor() {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return from(this.handleAccess(request, next));
  }

  private async handleAccess(request: HttpRequest<any>, next: HttpHandler): Promise<HttpEvent<any>> {

    console.log("PASSOU NO HANDLE API");
    //const baseUrl = 'http://localhost:9020';
    const baseUrl = 'https://api.ahorasw.rocks';
    if (request.urlWithParams.indexOf('/api') > -1) {
        request = request.clone({
            url: baseUrl + request.url
        });
    }
    return next.handle(request).toPromise();
  }  
}
