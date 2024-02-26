import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Marca } from 'src/app/model/marca';
import { Modelo } from 'src/app/model/modelo';
import { ModeloMarca } from 'src/app/model/modeloMarca';
import { Vehiculo } from 'src/app/model/vehiculo';
import { MarcaService } from 'src/app/service/marca.service';
import { ModeloService } from 'src/app/service/modelo.service';
import { VehiculoService } from 'src/app/service/vehiculo.service';

@Component({
  selector: 'app-agregar-modelo',
  templateUrl: './agregar-modelo.component.html',
  styleUrls: ['./agregar-modelo.component.css']
})
export class AgregarModeloComponent implements OnInit {

  //declaracion de variables

  myForm: FormGroup;
  selectedPais: string
  modelo: ModeloMarca
  marca: Marca[]
  constructor(private formBuilder: FormBuilder,
    private marcaService: MarcaService,
    private modeloService: ModeloService,

    private toastr: ToastrService,
    
    private dialogRef: MatDialogRef<AgregarModeloComponent>) { 
      this.modelo = new ModeloMarca();
    }
 /*meotodo que nos permite cargar todo lo mencionado dentro de este cuando se inicia 
  el componente*/
  ngOnInit() {
    this.initializeForm() 
    this.obtenerDatosMarca()
    //this.obtenerDatosPais()
  }
  initializeForm() {
    this.myForm = this.formBuilder.group({
      nombreModelo: ['', Validators.required],
      descripcionModelo: ['', Validators.required],
      anioModelo: ['', [Validators.required, Validators.pattern(/^-?\d+$/)]],
      marcaVehiculo: ['',[Validators.required, Validators.pattern(/^-?\d+\.?\d*$/)]],
    });
  }

  enviarSolicitud = false;

      /*metodo que nos permite guardar los datos del formulario siempre
      y cuando el formulario sea valido*/

  guardar() {
    if (this.myForm.valid  && !this.enviarSolicitud) {
      this.enviarSolicitud = true;
      // Procesar los datos del formulario
      this.modelo.marcaVehiculo = this.myForm.value.marcaVehiculo;
      this.modelo.descripcionModelo = this.myForm.value.descripcionModelo;
      this.modelo.anioModelo = this.myForm.value.anioModelo;
      this.modelo.nombreModelo = this.myForm.value.nombreModelo
      console.log("enviado", this.modelo)
      

      this.modeloService.guardar(this.modelo)
        .subscribe(() => {
          this.toastr.success("", 'Datos Agregados Correctamente', {
            timeOut: 3000,  positionClass: 'toast-bottom-left',
          });
          // Emitir una notificación para actualizar el listado de componentes
          return this.obtenerDatos();
        });
        this.cancelar(true);

    }
  }
    /*metodo que nos permite obtener los datos de la tabla productos
    */

  obtenerDatos() {
    this.modeloService.listar().subscribe(
      p => {
        this.modeloService.productoActualizar.next(p);
      },
      error => {
        console.log('Error al obtener la lista :', error);
      }
    );
  }


  obtenerDatosMarca() {
    this.marcaService.listar().subscribe(
      p => {
        this.marca = p;
      },
      error => {
        console.log('Error al obtener la lista :', error);
      }
    );
  }

  
 /*metodo que nos permite llamar a otro componente el cual 
 tiene un pequeño formulario donde te pregunta si cancelas o no
    */
  cancelar(confirmacion: boolean) {

    this.dialogRef.close(confirmacion);

  }

}
