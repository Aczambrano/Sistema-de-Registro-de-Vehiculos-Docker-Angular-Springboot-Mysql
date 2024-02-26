package com.gizlo.crud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gizlo.crud.entity.MarcaVehiculo;
import com.gizlo.crud.entity.ModeloVehiculo;
import com.gizlo.crud.entity.Vehiculo;
import io.swagger.models.auth.In;
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
public class ModeloVehiculoRequest {

    private String nombreModelo;
    private String descripcionModelo;
    private int anioModelo;
    private int marcaVehiculo;
}
