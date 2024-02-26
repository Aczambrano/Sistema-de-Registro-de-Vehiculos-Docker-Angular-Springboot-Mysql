package com.gizlo.crud.response;

import com.gizlo.crud.entity.MarcaVehiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcaVehiculoResponse {

    private int id;
    private String nombreMarca;
    public MarcaVehiculoResponse(MarcaVehiculo data){
        this.id = data.getIdMarca();
        this.nombreMarca = data.getNombre();
    }
}
