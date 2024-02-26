import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Marca } from 'src/app/model/marca';
import { Modelo } from 'src/app/model/modelo';
import { ModeloMarca } from 'src/app/model/modeloMarca';
import { MarcaService } from 'src/app/service/marca.service';
import { ModeloService } from 'src/app/service/modelo.service';

@Component({
  selector: 'app-editar-modelo',
  templateUrl: './editar-modelo.component.html',
  styleUrls: ['./editar-modelo.component.css']
})
export class EditarModeloComponent implements OnInit {

   //declaracion  de variables

   myForm: FormGroup;
   selectedPais: string
   modelo: ModeloMarca
   marca: Marca[]
 
   constructor(private formBuilder: FormBuilder,
     private modeloService: ModeloService,
     private toastr: ToastrService,
     private marcaService: MarcaService,
     private dialogRef: MatDialogRef<EditarModeloComponent>,
     
     @Inject(MAT_DIALOG_DATA) private data: any
     ) {
       this.modelo = new ModeloMarca()
       this.modelo.id = this.data.id;
       this.modelo.nombreModelo = this.data.nombre;
       this.modelo.descripcionModelo = this.data.descripcion;
       this.modelo.anioModelo = this.data.anio;
       this.modelo.marcaVehiculo = this.data.id;
      }
  /*meotodo que nos permite cargar todo lo mencionado dentro de este cuando se inicia 
   el componente*/
   ngOnInit() {
     this.initializeForm() 
     this.obtenerDatosMarca()
   }
   initializeForm() {
 
     this.myForm = this.formBuilder.group({
       nombre: [this.modelo?.nombreModelo || '', Validators.required],
       descripcion: [this.modelo?.descripcionModelo || '', Validators.required],
       anio: [this.modelo?.anioModelo || '', Validators.required],
       marca: [this.modelo?.marcaVehiculo || '', Validators.required], 
     
     });
 
   }
 
   enviarSolicitud = false;
 
     /*metodo que nos permite guardar los datos del formulario siempre
       y cuando el formulario sea valido*/
   guardar() {
     if (this.myForm.valid  && !this.enviarSolicitud) {
       this.enviarSolicitud = true;
 
       // Procesar los datos del formulario
       
 
       this.modelo.nombreModelo = this.myForm.value.nombre;
       this.modelo.descripcionModelo = this.myForm.value.descripcion;
       this.modelo.anioModelo = this.myForm.value.anio;
       this.modelo.marcaVehiculo = this.myForm.value.marca
       console.log("enviado", this.modelo)
       
       this.modeloService.modificar(this.modelo.id,this.modelo)
         .subscribe(() => {
           console.log("enviado", this.modelo)
           this.toastr.success("", 'Datos Agregados Correctamente', {
             timeOut: 3000,  positionClass: 'toast-bottom-left',
           });
           // Emitir una notificación para actualizar el listado de componentes
           return this.obtenerDatosModelo();
         });
         this.cancelar(true);
 
     }
   }
 /*metodo que nos permite obtener los datos de la tabla productos
     */
   obtenerDatosModelo() {
     this.modeloService.listar().subscribe(
       p => {
         this.modeloService.productoActualizar.next(p);
       },
       error => {
         console.log('Error al obtener la lista de modelos:', error);
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
 
    /*meotodo que nos permite llamar a otro componente el cual 
  tiene un pequeño formulario donde te pregunta si cancelas o no
     */
   cancelar(confirmacion: boolean) {
 
     this.dialogRef.close(confirmacion);
 
   }

}
