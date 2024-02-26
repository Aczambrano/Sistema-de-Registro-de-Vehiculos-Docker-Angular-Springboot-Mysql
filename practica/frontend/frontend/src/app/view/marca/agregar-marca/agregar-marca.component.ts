import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Marca } from 'src/app/model/marca';
import { MarcaService } from 'src/app/service/marca.service';
@Component({
  selector: 'app-agregar-marca',
  templateUrl: './agregar-marca.component.html',
  styleUrls: ['./agregar-marca.component.css']
})
export class AgregarMarcaComponent implements OnInit {

  
  myForm: FormGroup;
  selectedPais: string
  marca: Marca
  constructor(private formBuilder: FormBuilder,
    private marcaService: MarcaService,
    private toastr: ToastrService,
    
    private dialogRef: MatDialogRef<AgregarMarcaComponent>) { 
      this.marca = new Marca();
    }
 /*meotodo que nos permite cargar todo lo mencionado dentro de este cuando se inicia 
  el componente*/
  ngOnInit() {
    this.initializeForm() 
    //this.obtenerDatosPais()
  }
  initializeForm() {
    
    this.myForm = this.formBuilder.group({
      nombre: ['', Validators.required],
    });
  }

  enviarSolicitud = false;

      /*metodo que nos permite guardar los datos del formulario siempre
      y cuando el formulario sea valido*/

  guardar() {
    if (this.myForm.valid  && !this.enviarSolicitud) {
      this.enviarSolicitud = true;
      // Procesar los datos del formulario
      this.marca.nombreMarca = this.myForm.value.nombre;
      console.log("enviado", this.marca)
      

      this.marcaService.guardar(this.marca)
        .subscribe(() => {
          this.toastr.success("", 'Datos Agregados Correctamente', {
            timeOut: 3000,  positionClass: 'toast-bottom-left',
          });
          // Emitir una notificación para actualizar el listado de componentes
          return this.obtenerDatosMarca();
        });
        this.cancelar(true);

    }
  }
    /*metodo que nos permite obtener los datos de la tabla productos
    */

    obtenerDatosMarca() {
    this.marcaService.listar().subscribe(
      p => {
        this.marcaService.productoActualizar.next(p);
      },
      error => {
        console.log('Error al obtener la lista de productos:', error);
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
