import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Observable } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { ModalEliminarComponent } from '../../layouts/modal-eliminar/modal-eliminar.component';
import { ActivatedRoute } from '@angular/router';
import { TokenService } from 'src/app/service/security/token.service';
import { ToastrService } from 'ngx-toastr';
import { Vehiculo } from 'src/app/model/vehiculo';
import { ModeloService } from 'src/app/service/modelo.service';
import { Modelo } from 'src/app/model/modelo';
import { AgregarModeloComponent } from '../agregar-modelo/agregar-modelo.component';
import { EditarModeloComponent } from '../editar-modelo/editar-modelo.component';
import { MarcaService } from 'src/app/service/marca.service';

@Component({
  selector: 'app-listar-modelo',
  templateUrl: './listar-modelo.component.html',
  styleUrls: ['./listar-modelo.component.css']
})
export class ListarModeloComponent implements OnInit {

 //declaracion de variables

dataSource: MatTableDataSource<Modelo>;
displayedColumns: string[] = [
  'Nro',
  'nombre',
  'descripcion',
  'anio',
  'marca',
  'Acciones'
];

@ViewChild(MatPaginator) paginator!: MatPaginator;
@ViewChild(MatSort) sort!: MatSort;

isLogged = false;
isAdmin = false;
cantidad: number = 0;
indicePaginacion = 0;
cantidadPaginacion = 10;
nombre: string
apellido: string
foto: string
modelo: Modelo;


constructor(private modeloService: ModeloService,
  private marcaService: MarcaService,
  private dialog: MatDialog,
  private router: ActivatedRoute,
  private toastr: ToastrService,
  private tokenService: TokenService,

  ) {

}
 /*meotodo que nos permite cargar todo lo mencionado dentro de este cuando se inicia 
el componente*/

ngOnInit() {
  if(this.tokenService.getToken()){
    this.isLogged = true;

  }else{
    this.isLogged = false;
  }

  this.isAdmin = this.tokenService.isAdmin();
  
/*aqui pasamos por rutas algunos parametros del usuario*/
  this.router.queryParams.subscribe(params => {
    this.nombre = params['nombre'];
    this.apellido = params['apellido'];
    this.foto = params['foto'];

  });


  //this.obtenerDatos(this.indicePaginacion, this.cantidadPaginacion)
  this.obtenerDatos()
  this.obtenerDatosModelo(this.indicePaginacion, this.cantidadPaginacion)
}

/*metodo que nos permite obtener los datos de la tabla productos
  */

obtenerDatos() {
  this.modeloService.productoActualizar.subscribe(d => {
    this.dataSource = new MatTableDataSource(d);
  })
}

 

  /* metodo que nos permite paginar los datos de la tabla*/
  paginador(event: any) {
    this.obtenerDatosModelo(event.pageIndex, event.pageSize);
  }
/*
  obtenerDatosModelo(index: number, cantidad: number) {
    this.modeloService.paginacion(index, cantidad).subscribe(
      p => {
        this.cantidad = p.totalElements;
        this.dataSource = new MatTableDataSource(p.content);
        this.dataSource.sort = this.sort;
        console.log("Elementos traidos", this.dataSource)
      },
      error => {
        console.log('Error al obtener la lista de productos:', error);
      }
    );
  }
*/
  obtenerDatosModelo(index: number, cantidad: number): void {
    this.modeloService.paginacion(index, cantidad).subscribe(
      (p: any) => {
        this.cantidad = p.totalElements;
        this.dataSource = new MatTableDataSource<Modelo>(p.content);
        this.dataSource.sort = this.sort;
        console.log("Elementos traidos", this.dataSource);
        
        // Iterar sobre cada vehículo para obtener y agregar datos del modelo
        this.dataSource.data.forEach((modelo: Modelo) => {
          this.marcaService.buscarPorId(modelo.marca).subscribe(
            (marca: any) => {
              modelo.marca = marca.nombreMarca; // Suponiendo que existe un campo nombreModelo en la clase Vehiculo
            },
            error => {
              console.log('Error al obtener el modelo del vehículo:', error);
            }
          );
        });
      },
      error => {
        console.log('Error al obtener la lista de productos:', error);
      }
    );
  }
 


/*metodo que nos permite llamar a otro componente el cual 
tiene un pequeño formulario donde te pregunta si eliminar un registro o no
  */
openModalDelete(mensaje: string): Observable<string> {
  const dialogRef = this.dialog.open(ModalEliminarComponent, {
    disableClose: true,
    data: { mensaje: mensaje }
  });
  return dialogRef.afterClosed();
}

/*metodo que nos permite llamar a otro componente el cual 
tiene un pequeño formulario donde puedes agregar un producto
  */

agregar() {
  this.dialog.open(AgregarModeloComponent, {
    maxWidth: '800px',
    maxHeight: '700px',
    data: { mensaje: "Agregar un nuevo Modelo" }
  })
}

/*metodo que nos permite llamar a otro componente el cual 
tiene un pequeño formulario donde puedes editar un producto
  */

editar(modelo?: Modelo) {
  let product = modelo != null ? modelo : new Vehiculo()
  this.dialog.open(EditarModeloComponent, {
    maxWidth: '800px',
    maxHeight: '700px',
    data: product
  })
}

eliminar(id: number) {
  this.openModalDelete('¿Desea Eliminar el Registro?').subscribe(result => {
    if (result) {
      this.modeloService.eliminar(id).subscribe(
        () => {
          // Manejar la eliminación exitosa
          console.log('Registro eliminado correctamente');
          this.toastr.error("", 'Datos Eliminados Correctamente', {
            timeOut: 3000,  positionClass: 'toast-bottom-left',
          });
          this.obtenerDatosModelo(this.indicePaginacion, this.cantidadPaginacion);
        },
        (error) => {
          // Manejar el error en caso de que ocurra
          console.error('Error al eliminar el registro', error);
        }
      );
    }
  });

}

/*metodo para filtrar la tabla mediante un input
  */

filtrar(event: Event) {
  const filterValue = (event.target as HTMLInputElement).value;
  this.dataSource.filter = filterValue.trim().toLowerCase();
}

/* metodo que nos permite paginar los datos de la tabla*/
/*
paginador(event: any) {
  this.obtenerDatos(event.pageIndex, event.pageSize);
}*/


}
