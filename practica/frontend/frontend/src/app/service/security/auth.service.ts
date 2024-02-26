import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtDTO } from 'src/app/model/jwt';
import { LoginUsuario } from 'src/app/model/login';
import { NuevoUsuario } from 'src/app/model/registro';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authURL = 'http://localhost:8080/auth/';

  constructor(private httpClient: HttpClient) { }


  public nuevo(nuevoUsuario: NuevoUsuario): Observable<any> {
    return this.httpClient.post<any>(this.authURL + 'nuevo', nuevoUsuario);
  }

  public login(loginUsuario: LoginUsuario): Observable<JwtDTO> {
    return this.httpClient.post<JwtDTO>(this.authURL + 'login', loginUsuario);
  }

  public refresh(dto: JwtDTO): Observable<JwtDTO> {
    return this.httpClient.post<JwtDTO>(this.authURL + 'refresh', dto);
  }
}
