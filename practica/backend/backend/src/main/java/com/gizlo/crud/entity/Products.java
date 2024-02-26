package com.gizlo.crud.entity;

import com.gizlo.crud.request.ProductsRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*Utilizamos lombok para no mostrar tanto código como los constructores
* y los gettes y setters, una manera facil para mantener nuestra clase limpia*/
@Entity // Indica que esta clase es una entidad JPA y se mapea a una tabla en la base de datos
@Data // Genera automáticamente los métodos getter, setter, toString, equals y hashCode
@Builder // Patrón de diseño que facilita la construcción de objetos complejos
@AllArgsConstructor // Genera un constructor con todos los argumentos
@NoArgsConstructor // Genera un constructor sin argumentos
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private double precio;
    private String marca;
    private String categoria;
    private String imagen;
    private Character estado;

    public Products(ProductsRequest data) {
        this.nombre = data.getNombre();
        this.descripcion = data.getDescripcion();
        this.cantidad= data.getCantidad();
        this.precio = data.getPrecio();
        this.marca = data.getMarca();
        this.categoria = data.getCategoria();
        this.imagen = data.getImagen();
        this.estado = data.getEstado();
    }

    public Products(Products data) {
        this.nombre = data.getNombre();
        this.descripcion = data.getDescripcion();
        this.cantidad= data.getCantidad();
        this.precio = data.getPrecio();
        this.marca = data.getMarca();
        this.categoria = data.getCategoria();
        this.imagen = data.getImagen();
        this.estado = data.getEstado();
    }

}
