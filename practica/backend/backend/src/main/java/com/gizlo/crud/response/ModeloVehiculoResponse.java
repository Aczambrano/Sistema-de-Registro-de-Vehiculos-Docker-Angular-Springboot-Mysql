package com.gizlo.crud.response;

import com.gizlo.crud.entity.MarcaVehiculo;
import com.gizlo.crud.entity.ModeloVehiculo;
import com.gizlo.crud.entity.Vehiculo;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModeloVehiculoResponse {

    private int id;
    private String nombre;
    private String descripcion;
    private int anio;
    private int marca;
    public ModeloVehiculoResponse(ModeloVehiculo data){
        this.id = data.getId();
        this.nombre = data.getNombre();
        this.descripcion = data.getDescripcion();
        this.anio = data.getAnio();
        if (data.getMarca() != null) {
            this.marca = data.getMarca().getIdMarca();
        }
    }
}
