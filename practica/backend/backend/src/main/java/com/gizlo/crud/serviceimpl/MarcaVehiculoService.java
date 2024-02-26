package com.gizlo.crud.serviceimpl;
/*Definimos esta clase como un servicio implementado por su interface*/

import com.gizlo.crud.entity.MarcaVehiculo;
import com.gizlo.crud.entity.ModeloVehiculo;
import com.gizlo.crud.exception.ObjectNotFoundException;
import com.gizlo.crud.repository.MarcaVehiculoRepository;
import com.gizlo.crud.request.MarcaVehiculoRequest;
import com.gizlo.crud.response.MarcaVehiculoResponse;
import com.gizlo.crud.response.ModeloVehiculoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class MarcaVehiculoService {

    /*
     * Definimos los autowired para la inyección de dependencias en esta clase.
     * En este caso, se utiliza para inyectar el repositorio correspondiente.
     */
    @Autowired
    private MarcaVehiculoRepository repo;
    /*el resto de metodos son metodos basicos que se implementan mediante el Repositorio*/


    public List<MarcaVehiculoResponse> listar() {
        List<MarcaVehiculo> marca = this.repo.findAll();
        List<MarcaVehiculoResponse> marcaResponses = marca.stream().map(
                p -> new MarcaVehiculoResponse(p)).collect(Collectors.toList());
        return marcaResponses;
    }

    public Page<MarcaVehiculoResponse> listaPaginacion(Pageable pageable) {
        Page<MarcaVehiculo> marcaPage = this.repo.findAll(pageable);

        // Mapear cada MarcaVehiculo a MarcaVehiculoResponse
        Page<MarcaVehiculoResponse> responsePage = marcaPage.map(
                marcaVehiculo -> new MarcaVehiculoResponse(marcaVehiculo));

        return responsePage;
    }

    public String registrar (MarcaVehiculoRequest data) {
        MarcaVehiculo marcaVehiculo = new MarcaVehiculo((data));
        this.repo.save(marcaVehiculo);
        return "Registro correcto";
    }

    public String actualizar(Integer id,MarcaVehiculoRequest data) {

        MarcaVehiculo marcaVehiculo = repo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Registro no encontrado "+ id));

        marcaVehiculo.setNombre(data.getNombreMarca());
        this.repo.save(marcaVehiculo);
        return "Actualizado correctamente";
    }

    public void eliminar(Integer id) {
        MarcaVehiculo marcaVehiculo = repo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Registro no encontrado "+ id));
        repo.deleteById(marcaVehiculo.getIdMarca());
    }

    public MarcaVehiculoResponse listarPorId(Integer idP) {

        MarcaVehiculo modelo = repo.findById(idP)
                .orElseThrow(() -> new ObjectNotFoundException("Modelo no encontrado"));
        MarcaVehiculoResponse vehiculo = new MarcaVehiculoResponse(modelo);
        return vehiculo;
    }


    /*Métodos para la implementacion de la paginacion*/

/*
    public Page<MarcaVehiculo> listaPaginacion(Pageable pageable) {
        return repo.findAll(pageable);
    }*/
}
