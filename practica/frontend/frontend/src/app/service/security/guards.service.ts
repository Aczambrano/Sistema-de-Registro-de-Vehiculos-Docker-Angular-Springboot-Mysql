import { Injectable } from '@angular/core';
import { TokenService } from './token.service';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class GuardsService implements CanActivate {
  realRol: string;
  constructor(private tokenService: TokenService,
    private router: Router) {

  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const expectedRol = route.data['expectedRol'];
    this.realRol = this.tokenService.isAdmin() ? 'admin' : 'user';

    if (!this.tokenService.isLogged() || expectedRol.indexOf(this.realRol) < 0) {
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }

}
