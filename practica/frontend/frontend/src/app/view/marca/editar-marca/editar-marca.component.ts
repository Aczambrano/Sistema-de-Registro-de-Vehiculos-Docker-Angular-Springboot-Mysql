import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Marca } from 'src/app/model/marca';
import { MarcaService } from 'src/app/service/marca.service';

@Component({
  selector: 'app-editar-marca',
  templateUrl: './editar-marca.component.html',
  styleUrls: ['./editar-marca.component.css']
})
export class EditarMarcaComponent implements OnInit {

  //declaracion  de variables

  myForm: FormGroup;
  marca: Marca

  constructor(private formBuilder: FormBuilder,
    private marcaService: MarcaService,
    private toastr: ToastrService,
    private dialogRef: MatDialogRef<EditarMarcaComponent>,
    
    @Inject(MAT_DIALOG_DATA) private data: any
    ) {
      this.marca = new Marca()
      this.marca.id = this.data.id;
      this.marca.nombreMarca = this.data.nombreMarca;
     }
 /*meotodo que nos permite cargar todo lo mencionado dentro de este cuando se inicia 
  el componente*/
  ngOnInit() {
    this.initializeForm() 
  }
  initializeForm() {

    this.myForm = this.formBuilder.group({
      nombre: [this.marca?.nombreMarca || '', Validators.required],
    
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
      
      this.marcaService.modificar(this.marca.id,this.marca)
        .subscribe(() => {
          console.log("enviado", this.marca)
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
    this.marcaService.listar().subscribe(
      p => {
        this.marcaService.productoActualizar.next(p);
      },
      error => {
        console.log('Error al obtener la lista de productos:', error);
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
