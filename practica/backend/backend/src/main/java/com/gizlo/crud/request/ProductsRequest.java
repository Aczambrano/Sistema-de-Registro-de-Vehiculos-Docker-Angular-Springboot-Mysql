package com.gizlo.crud.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsRequest {
    private String nombre;
    private String descripcion;
    private int cantidad;
    private double precio;
    private String marca;
    private String categoria;
    private String imagen;
    private Character estado;

}
