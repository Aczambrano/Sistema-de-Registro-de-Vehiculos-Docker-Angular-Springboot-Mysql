import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Vehiculo } from '../model/vehiculo';

//Servicio para obtener los datos de la api de la bdd interna
//Este servicio nos permite realizar los metodos crud y tambien la paginacion

@Injectable({
  providedIn: 'root'
})
export class VehiculoService {

  private url = "http://localhost:8080/vehiculos"
  
  productoActualizar = new Subject<Vehiculo[]>();


  constructor(private httpClient: HttpClient,) { }

  listar(): Observable<Vehiculo[]> {
    return this.httpClient.get<Vehiculo[]>(`${this.url + "/listar"}`);
  }
  guardar(parametros: Vehiculo): Observable<Object> {
    return this.httpClient.post(`${this.url + "/registrar"}`, parametros)
  }
  
  paginacion(pagina:number, tamaño:number):Observable<any>{
    return this.httpClient.get<any>(`${this.url}/paginacion?page=${pagina}&size=${tamaño}`)
  }

  modificar(id: number, parametros: Vehiculo): Observable<any> {
    return this.httpClient.put<any>(`${this.url}/actualizar/${id}`, parametros)
  }
  eliminar(id: number): Observable<any> {
    return this.httpClient.delete(`${this.url}/eliminar/${id}`, { responseType: 'text' });
  }

  buscarPorId(id: number): Observable<Vehiculo> {
    return this.httpClient.get<Vehiculo>(`${this.url}/buscarPorId/${id}`);
  }

}
