import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Modelo } from 'src/app/model/modelo';
import { Vehiculo } from 'src/app/model/vehiculo';
import { ModeloService } from 'src/app/service/modelo.service';
import { VehiculoService } from 'src/app/service/vehiculo.service';

@Component({
  selector: 'app-agregar-vehiculo',
  templateUrl: './agregar-vehiculo.component.html',
  styleUrls: ['./agregar-vehiculo.component.css']
})
export class AgregarVehiculoComponent implements OnInit {

  //declaracion de variables

  myForm: FormGroup;
  selectedPais: string
  products: Vehiculo
  modelo: Modelo[]
  constructor(private formBuilder: FormBuilder,
    private vehiculoService: VehiculoService,
    private modeloService: ModeloService,

    private toastr: ToastrService,
    
    private dialogRef: MatDialogRef<AgregarVehiculoComponent>) { 
      this.products = new Vehiculo();
    }
 /*meotodo que nos permite cargar todo lo mencionado dentro de este cuando se inicia 
  el componente*/
  ngOnInit() {
    this.initializeForm() 
    this.obtenerDatosModelo()
    //this.obtenerDatosPais()
  }
  initializeForm() {
    this.myForm = this.formBuilder.group({
      nombreVehiculo: ['', Validators.required],
      descripcionVehiculo: ['', Validators.required],
      anioVehiculo: ['', [Validators.required, Validators.pattern(/^-?\d+$/)]],
      modelo: ['',[Validators.required, Validators.pattern(/^-?\d+\.?\d*$/)]],
    });
  }

  enviarSolicitud = false;

      /*metodo que nos permite guardar los datos del formulario siempre
      y cuando el formulario sea valido*/

  guardar() {
    if (this.myForm.valid  && !this.enviarSolicitud) {
      this.enviarSolicitud = true;
      // Procesar los datos del formulario
      this.products.nombreVehiculo = this.myForm.value.nombreVehiculo;
      this.products.descripcionVehiculo = this.myForm.value.descripcionVehiculo;
      this.products.anioVehiculo = this.myForm.value.anioVehiculo;
      this.products.modelo = this.myForm.value.modelo
      console.log("enviado", this.products)
      

      this.vehiculoService.guardar(this.products)
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
    this.vehiculoService.listar().subscribe(
      p => {
        this.vehiculoService.productoActualizar.next(p);
      },
      error => {
        console.log('Error al obtener la lista :', error);
      }
    );
  }


  obtenerDatosModelo() {
    this.modeloService.listar().subscribe(
      p => {
        this.modelo = p;
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

