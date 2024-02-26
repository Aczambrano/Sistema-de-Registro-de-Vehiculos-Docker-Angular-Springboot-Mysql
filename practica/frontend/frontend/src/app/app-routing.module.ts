import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './view/auth/login/login.component';
import { GuardsService } from './service/security/guards.service';
import { LoginGuardsService } from './service/security/login-guards.service';
import { ListarVehiculoComponent } from './view/vehiculos/listar-vehiculo/listar-vehiculo.component';
import { ListarModeloComponent } from './view/modelo/listar-modelo/listar-modelo.component';
import { ListarMarcaComponent } from './view/marca/listar-marca/listar-marca.component';


  
 /*Manejo de rutas de nuestra aplicacion web  */

const routes: Routes = [
  //{ path: 'registro', component: RegistroComponent, canActivate:[LoginGuardsService]},
  { path: 'login', component: LoginComponent , canActivate:[LoginGuardsService]},
  { path: '', redirectTo: '/login', pathMatch: 'full' },

  { path: 'vehiculos', component: ListarVehiculoComponent, canActivate: [GuardsService],
   data: { expectedRol: ['admin', 'user'] }  },
  { path: 'modelos', component: ListarModeloComponent, canActivate: [GuardsService],
   data: { expectedRol: ['admin', 'user'] }  },
  { path: 'marca', component: ListarMarcaComponent, canActivate: [GuardsService],
   data: { expectedRol: ['admin', 'user'] }  },
];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
