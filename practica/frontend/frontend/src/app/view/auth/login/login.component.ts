import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { LoginUsuario } from 'src/app/model/login';
import { TokenService } from 'src/app/service/security/token.service';
import { AuthService } from 'src/app/service/security/auth.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

/*
  Componente donde se muestra el login de la pagina
*/

export class LoginComponent implements OnInit {
  //añadiduras de variables
  loginForm: FormGroup;

  loginUsuario: LoginUsuario;
  nombreUsuario: string;
  password: string;
  errMsj: string;


  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private toastr: ToastrService,
    private fb: FormBuilder,
    private route: Router,
    private dialog: MatDialog
  ) {

  }
  /*meotodo que nos permite cargar todo lo mencionado dentro de este cuando se inicia 
  el componente*/
  ngOnInit(): void {
    
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });


  }

  onLogin(): void {
    this.loginUsuario = new LoginUsuario(this.loginForm.value.username,
      this.loginForm.value.password);

    this.authService.login(this.loginUsuario).subscribe(
      data => {
        this.tokenService.setToken(data.token);
        this.route.navigate(['/marca']);
      },
      err => {
        this.toastr.error(this.errMsj, 'Ingrese Datos Correctos', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
        //this.accesoDenegado()
      }
    );
  }
}
