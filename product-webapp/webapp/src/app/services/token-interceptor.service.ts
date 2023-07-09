import { Injectable,Injector } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from "./login.service";

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  constructor(private injector: Injector ) {}
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let loginservice = this.injector.get(LoginService);
    let authReq=req;
       const token=loginservice.getToken();
       if(token!=null){
        authReq=authReq.clone({
            setHeaders:{Authorization:`Bearer ${token}` }
        }

        );

       } return next.handle(authReq);

  }
}
