import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ModalEliminarComponent } from './view/layouts/modal-eliminar/modal-eliminar.component';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';


import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ToolbarComponent } from './view/layouts/toolbar/toolbar.component';
import { MatTableModule } from '@angular/material/table';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms'
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';

import { LoginComponent } from './view/auth/login/login.component';
import { ModalInformacionComponent } from './view/layouts/modal-informacion/modal-informacion.component';
import { InterceptorService } from './service/security/interceptor.service';
import { ToastrModule } from 'ngx-toastr';
import { AgregarVehiculoComponent } from './view/vehiculos/agregar-vehiculo/agregar-vehiculo.component';
import { EditarVehiculoComponent } from './view/vehiculos/editar-vehiculo/editar-vehiculo.component';
import { ListarVehiculoComponent } from './view/vehiculos/listar-vehiculo/listar-vehiculo.component';
import { AgregarMarcaComponent } from './view/marca/agregar-marca/agregar-marca.component';
import { EditarMarcaComponent } from './view/marca/editar-marca/editar-marca.component';
import { ListarMarcaComponent } from './view/marca/listar-marca/listar-marca.component';
import { AgregarModeloComponent } from './view/modelo/agregar-modelo/agregar-modelo.component';
import { EditarModeloComponent } from './view/modelo/editar-modelo/editar-modelo.component';
import { ListarModeloComponent } from './view/modelo/listar-modelo/listar-modelo.component';

/*Modulo principal donde importamos nuestros componentes y algunos
otros componentes externos*/ 
@NgModule({
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  declarations: [
    AppComponent,
    ToolbarComponent,
    ModalEliminarComponent,

    AgregarVehiculoComponent,
    EditarVehiculoComponent,
    ListarVehiculoComponent,
    AgregarMarcaComponent,
    EditarMarcaComponent,
    ListarMarcaComponent,
    AgregarModeloComponent,
    EditarModeloComponent,
    ListarModeloComponent,
    LoginComponent,
    ModalInformacionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    MatIconModule,
    MatButtonModule,
    MatToolbarModule,
    HttpClientModule,
    MatTableModule,
    MatDialogModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule ,
    MatSelectModule,
    MatPaginatorModule,
    MatSortModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
