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
import { Marca } from 'src/app/model/marca';
import { MarcaService } from 'src/app/service/marca.service';
import { AgregarMarcaComponent } from '../agregar-marca/agregar-marca.component';
import { EditarMarcaComponent } from '../editar-marca/editar-marca.component';

@Component({
  selector: 'app-listar-marca',
  templateUrl: './listar-marca.component.html',
  styleUrls: ['./listar-marca.component.css']
})
export class ListarMarcaComponent implements OnInit {

 //declaracion de variables

 dataSource: MatTableDataSource<Marca>;
 displayedColumns: string[] = [
   'Nro',
   'nombre',
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


 constructor(private marcaService: MarcaService,
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
   //this.obtenerDatosMarca()
   this.marcaService.productoActualizar.subscribe(d => {
     this.dataSource = new MatTableDataSource(d);
   })

   this.obtenerDatosMarca(this.indicePaginacion, this.cantidadPaginacion)
  
 }

  /* metodo que nos permite paginar los datos de la tabla*/
  paginador(event: any) {
    this.obtenerDatosMarca(event.pageIndex, event.pageSize);
  }

  obtenerDatosMarca(index: number, cantidad: number) {
    this.marcaService.paginacion(index, cantidad).subscribe(
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
/*metodo que nos permite obtener los datos de la tabla marca
   *//*
obtenerDatosMarca() {
  this.marcaService.listar().subscribe(
    p => {
      this.dataSource = new MatTableDataSource(p);
      this.dataSource.sort = this.sort;
      console.log("Elementos traidos", this.dataSource)
    },
    error => {
      console.log('Error al obtener la lista de marca:', error);
    }
  );
}*/


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
   this.dialog.open(AgregarMarcaComponent, {
     maxWidth: '800px',
     maxHeight: '700px',
     data: { mensaje: "Agregar una marca" }
   })
 }

 /*metodo que nos permite llamar a otro componente el cual 
tiene un pequeño formulario donde puedes editar un producto
   */

 editar(marca?: Marca) {
   let product = marca != null ? marca : new Marca()
   console.log("marca ",product)
   this.dialog.open(EditarMarcaComponent, {
     maxWidth: '800px',
     maxHeight: '700px',
     data: product
   })
 }

 eliminar(id: number) {
   this.openModalDelete('¿Desea Eliminar el Registro?').subscribe(result => {
     if (result) {
       this.marcaService.eliminar(id).subscribe(
         () => {
           // Manejar la eliminación exitosa
           console.log('Registro eliminado correctamente');
           this.toastr.error("", 'Datos Eliminados Correctamente', {
             timeOut: 3000,  positionClass: 'toast-bottom-left',
           });
           this.obtenerDatosMarca(this.indicePaginacion, this.cantidadPaginacion);
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


}
