import { HTTP_INTERCEPTORS, HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, concat, concatMap, of, throwError } from 'rxjs';
import { TokenService } from './token.service';
import { ToastrService } from 'ngx-toastr';
import { JwtDTO } from 'src/app/model/jwt';
import { AuthService } from './auth.service';

const AUTHORIZATION = 'Authorization';
@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {
  constructor(private tokenService: TokenService,
    private toastr: ToastrService,
    private authService: AuthService,

  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!this.tokenService.isLogged()) {
      return next.handle(req);
    }

    let intReq = req;
    const token = this.tokenService.getToken();
    intReq = this.addToken(req,token)

    return next.handle(intReq).pipe(catchError((err: HttpErrorResponse) => {
      if (err.status === 401) {
        const token = this.tokenService.getToken();
        if (token !== null) {
          const dto: JwtDTO = new JwtDTO(token);
          // Utiliza dto aquí
          return this.authService.refresh(dto).pipe(concatMap((data: any) => {
            console.log("refreshing....");
            this.tokenService.setToken(data.token)
            intReq = this.addToken(req,data.token)
            return next.handle(intReq);
          }))
        }
      } else {
        this.tokenService.logOut();
        return throwError(err);

      }
      return throwError(err);

    }));
  }

  private addToken(req: HttpRequest<any>, token: string | null): HttpRequest<any>{
    return req.clone({headers: req.headers.set('Authorization', 'Bearer ' + token) })
  }
}



