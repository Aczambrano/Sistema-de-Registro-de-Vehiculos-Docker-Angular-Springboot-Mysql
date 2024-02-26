import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Modelo } from '../model/modelo';
import { ModeloMarca } from '../model/modeloMarca';

//Servicio para obtener los datos de la api de la bdd interna
//Este servicio nos permite realizar los metodos crud y tambien la paginacion
//Adicional tambien se obtiene datos de la api externa para productos
@Injectable({
  providedIn: 'root'
})
export class ModeloService {

  private url = "http://localhost:8080/modelo"
  
  productoActualizar = new Subject<Modelo[]>();


  constructor(private httpClient: HttpClient,) { }

  listar(): Observable<Modelo[]> {
    return this.httpClient.get<Modelo[]>(`${this.url + "/listar"}`);
  }
  guardar(parametros: ModeloMarca): Observable<Object> {
    return this.httpClient.post(`${this.url + "/registrar"}`, parametros)
  }
 
  paginacion(pagina:number, tamaño:number):Observable<any>{
    return this.httpClient.get<any>(`${this.url}/paginacion?page=${pagina}&size=${tamaño}`)
  }

  modificar(id: number, parametros: ModeloMarca): Observable<any> {
    return this.httpClient.put<any>(`${this.url}/actualizar/${id}`, parametros)
  }
  eliminar(id: number): Observable<any> {
    return this.httpClient.delete(`${this.url}/eliminar/${id}`, { responseType: 'text' });
  }

  buscarPorId(id: number): Observable<ModeloMarca> {
    return this.httpClient.get<ModeloMarca>(`${this.url}/buscarPorId/${id}`);
  }

}
