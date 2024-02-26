import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/service/security/token.service';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})

/*
  Componente donde añadimos un toolbar para poder ser utilizado en la pagina
*/

export class ToolbarComponent implements OnInit {

  nombre = "Bienvenido"
  isLogged = false;
  constructor(
    private route: Router,
    private tokenService: TokenService
  ) { }

  ngOnInit() {
    this.isLogged = this.tokenService.isLogged();
  }

  onLogOut() {
    this.tokenService.logOut();
  }

  vehiculos(){
    this.route.navigate(['/vehiculos'])
  }

  modelos(){
    this.route.navigate(['/modelos'])
  }

  marca(){
    this.route.navigate(['/marca'])
  }
  login(){
    this.route.navigate(['/login'])
  }
}
