package com.gizlo.crud.response;

import com.gizlo.crud.entity.ModeloVehiculo;
import com.gizlo.crud.entity.Vehiculo;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiculoResponse {
    private int id;
    private String nombre;
    private String descripcion;
    private int anio;
    private int modelo;

    public VehiculoResponse(Vehiculo data){
        this.id = data.getId();
        this.nombre = data.getNombre();
        this.descripcion = data.getDescripcion();
        this.anio = data.getAnio();
        this.modelo = data.getModelo().getId();
    }
}
