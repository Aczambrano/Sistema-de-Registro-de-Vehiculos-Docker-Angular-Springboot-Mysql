import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Marca } from '../model/marca';

//Servicio para obtener los datos de la api de la bdd interna
//Este servicio nos permite realizar los metodos crud y tambien la paginacion

@Injectable({
  providedIn: 'root'
})
export class MarcaService {

  private url = "http://localhost:8080/marca"
  
  productoActualizar = new Subject<Marca[]>();


  constructor(private httpClient: HttpClient,) { }

  listar(): Observable<Marca[]> {
    return this.httpClient.get<Marca[]>(`${this.url + "/listar"}`);
  }
  guardar(parametros: Marca): Observable<Object> {
    return this.httpClient.post(`${this.url + "/registrar"}`, parametros)
  }

  paginacion(pagina:number, tamaño:number):Observable<any>{
    return this.httpClient.get<any>(`${this.url}/paginacion?page=${pagina}&size=${tamaño}`)
  }

  modificar(id: number, parametros: Marca): Observable<any> {
    return this.httpClient.put<any>(`${this.url}/actualizar/${id}`, parametros)
  }
  eliminar(id: number): Observable<any> {
    return this.httpClient.delete(`${this.url}/eliminar/${id}`, { responseType: 'text' });
  }

  buscarPorId(id: number): Observable<Marca> {
    return this.httpClient.get<Marca>(`${this.url}/buscarPorId/${id}`);
  }
}
