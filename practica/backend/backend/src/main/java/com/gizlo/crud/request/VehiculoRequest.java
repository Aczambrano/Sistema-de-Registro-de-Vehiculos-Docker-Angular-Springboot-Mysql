package com.gizlo.crud.request;

import com.gizlo.crud.entity.MarcaVehiculo;
import com.gizlo.crud.entity.ModeloVehiculo;
import com.gizlo.crud.entity.Vehiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiculoRequest {

    private String nombreVehiculo;
    private String descripcionVehiculo;
    private int anioVehiculo;
    private int modelo;

}
