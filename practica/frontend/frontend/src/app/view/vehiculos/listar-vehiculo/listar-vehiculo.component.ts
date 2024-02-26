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
import { VehiculoService } from 'src/app/service/vehiculo.service';
import { AgregarVehiculoComponent } from '../agregar-vehiculo/agregar-vehiculo.component';
import { EditarVehiculoComponent } from '../editar-vehiculo/editar-vehiculo.component';
import { Vehiculo } from 'src/app/model/vehiculo';
import { ModeloService } from 'src/app/service/modelo.service';

@Component({
  selector: 'app-listar-vehiculo',
  templateUrl: './listar-vehiculo.component.html',
  styleUrls: ['./listar-vehiculo.component.css']
})
export class ListarVehiculoComponent implements OnInit {
//declaracion de variables

dataSource: MatTableDataSource<Vehiculo>;
displayedColumns: string[] = [
  'Nro',
  'nombre',
  'descripcion',
  'anio',
  'modelo',
  'Acciones'
];

@ViewChild(MatPaginator) paginator!: MatPaginator;
@ViewChild(MatSort) sort!: MatSort;
vehiculo: Vehiculo;

isLogged = false;
isAdmin = false;
cantidad: number = 0;
indicePaginacion = 0;
cantidadPaginacion = 10;
nombre: string
apellido: string
foto: string


constructor(private vehiculoService: VehiculoService,
  private modeloService: ModeloService,
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
  this.obtenerDatosVehiculo(this.indicePaginacion, this.cantidadPaginacion)
}

/*metodo que nos permite obtener los datos de la tabla productos
  */

obtenerDatos() {
  this.vehiculoService.productoActualizar.subscribe(d => {
    this.dataSource = new MatTableDataSource(d);
  })
}

 
 /* metodo que nos permite paginar los datos de la tabla*/
 paginador(event: any) {
  this.obtenerDatosVehiculo(event.pageIndex, event.pageSize);
}

obtenerDatosVehiculo(index: number, cantidad: number): void {
  this.vehiculoService.paginacion(index, cantidad).subscribe(
    (p: any) => {
      this.cantidad = p.totalElements;
      this.dataSource = new MatTableDataSource<Vehiculo>(p.content);
      this.dataSource.sort = this.sort;
      console.log("Elementos traidos", this.dataSource);
      
      // Iterar sobre cada vehículo para obtener y agregar datos del modelo
      this.dataSource.data.forEach((vehiculo: Vehiculo) => {
        this.modeloService.buscarPorId(vehiculo.modelo).subscribe(
          (modelo: any) => {
            vehiculo.modelo = modelo.nombre; // Suponiendo que existe un campo nombreModelo en la clase Vehiculo
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
  this.dialog.open(AgregarVehiculoComponent, {
    maxWidth: '800px',
    maxHeight: '700px',
    data: { mensaje: "Agregar un nuevo Vehiculo" }
  })
}


/*metodo que nos permite llamar a otro componente el cual 
tiene un pequeño formulario donde puedes editar un producto
  */

editar(vehiculo?: Vehiculo) {
  let product = vehiculo != null ? vehiculo : new Vehiculo()
  console.log("producto ", product)
  this.dialog.open(EditarVehiculoComponent, {
    maxWidth: '800px',
    maxHeight: '700px',
    data: product
  })
}

eliminar(id: number) {
  this.openModalDelete('¿Desea Eliminar el Registro?').subscribe(result => {
    if (result) {
      this.vehiculoService.eliminar(id).subscribe(
        () => {
          // Manejar la eliminación exitosa
          console.log('Registro eliminado correctamente');
          this.toastr.error("", 'Datos Eliminados Correctamente', {
            timeOut: 3000,  positionClass: 'toast-bottom-left',
          });
          this.obtenerDatosVehiculo(this.indicePaginacion, this.cantidadPaginacion);
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
