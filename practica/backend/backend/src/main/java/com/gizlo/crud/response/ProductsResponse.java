package com.gizlo.crud.response;

import com.gizlo.crud.entity.Products;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsResponse {
    private int id;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private double precio;
    private String marca;
    private String categoria;
    private String imagen;
    private String  estado;

    public ProductsResponse(Products data) {
        this.id = data.getId();
        this.nombre = data.getNombre();
        this.descripcion = data.getDescripcion();
        this.cantidad= data.getCantidad();
        this.precio = data.getPrecio();
        this.marca = data.getMarca();
        this.categoria = data.getCategoria();
        this.imagen = data.getImagen();
        switch ( data.getEstado().toString().toLowerCase()){
            case "a":
                this.estado = "Activo";
                break;
            case "e":
                this.estado = "Eliminado";
                break;
            case "i":
                this.estado = "Inactivo";
                break;
        }
    }
}
