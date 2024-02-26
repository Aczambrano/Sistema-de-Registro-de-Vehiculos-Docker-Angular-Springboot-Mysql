import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Modelo } from 'src/app/model/modelo';

import { Vehiculo } from 'src/app/model/vehiculo';
import { ModeloService } from 'src/app/service/modelo.service';
import { VehiculoService } from 'src/app/service/vehiculo.service';

@Component({
  selector: 'app-editar-vehiculo',
  templateUrl: './editar-vehiculo.component.html',
  styleUrls: ['./editar-vehiculo.component.css']
})
export class EditarVehiculoComponent implements OnInit {

  //declaracion  de variables

  myForm: FormGroup;
  selectedPais: string
  vehiculo: Vehiculo
  modelo: Modelo[]

  constructor(private formBuilder: FormBuilder,
    private vehiculoService: VehiculoService,
    private toastr: ToastrService,
    private modeloService: ModeloService,
    private dialogRef: MatDialogRef<EditarVehiculoComponent>,
    
    @Inject(MAT_DIALOG_DATA) private data: any
    ) {
      this.vehiculo = new Vehiculo()
      this.vehiculo.id = this.data.id;
      this.vehiculo.nombreVehiculo = this.data.nombre;
      this.vehiculo.descripcionVehiculo = this.data.descripcion;
      this.vehiculo.anioVehiculo = this.data.anio;
      this.vehiculo.modelo = this.data.id;
     }
 /*meotodo que nos permite cargar todo lo mencionado dentro de este cuando se inicia 
  el componente*/
  ngOnInit() {
    this.initializeForm() 
    this.obtenerDatosModelo()
  }
  initializeForm() {

    this.myForm = this.formBuilder.group({
      nombre: [this.vehiculo?.nombreVehiculo || '', Validators.required],
      descripcion: [this.vehiculo?.descripcionVehiculo || '', Validators.required],
      anio: [this.vehiculo?.anioVehiculo || '', Validators.required],
      modelo: [this.vehiculo?.modelo || '', Validators.required], 
    
    });

  }

  enviarSolicitud = false;

    /*metodo que nos permite guardar los datos del formulario siempre
      y cuando el formulario sea valido*/
  guardar() {
    if (this.myForm.valid  && !this.enviarSolicitud) {
      this.enviarSolicitud = true;

      // Procesar los datos del formulario
      

      this.vehiculo.nombreVehiculo = this.myForm.value.nombre;
      this.vehiculo.descripcionVehiculo = this.myForm.value.descripcion;
      this.vehiculo.anioVehiculo = this.myForm.value.anio;
      this.vehiculo.modelo = this.myForm.value.modelo
      console.log("enviado", this.vehiculo)
      
      this.vehiculoService.modificar(this.vehiculo.id,this.vehiculo)
        .subscribe(() => {
          console.log("enviado", this.vehiculo)
          this.toastr.success("", 'Datos Agregados Correctamente', {
            timeOut: 3000,  positionClass: 'toast-bottom-left',
          });
          // Emitir una notificación para actualizar el listado de componentes
          return this.obtenerDatosVehiculo();
        });
        this.cancelar(true);

    }
  }
/*metodo que nos permite obtener los datos de la tabla productos
    */
  obtenerDatosVehiculo() {
    this.vehiculoService.listar().subscribe(
      p => {
        this.vehiculoService.productoActualizar.next(p);
      },
      error => {
        console.log('Error al obtener la lista de productos:', error);
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

   /*meotodo que nos permite llamar a otro componente el cual 
 tiene un pequeño formulario donde te pregunta si cancelas o no
    */
  cancelar(confirmacion: boolean) {

    this.dialogRef.close(confirmacion);

  }
}
