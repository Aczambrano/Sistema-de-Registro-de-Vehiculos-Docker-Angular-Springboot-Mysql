package com.gizlo.crud.serviceimpl;
/*Definimos esta clase como un servicio implementado por su interface*/

import com.gizlo.crud.entity.MarcaVehiculo;
import com.gizlo.crud.entity.ModeloVehiculo;
import com.gizlo.crud.entity.Vehiculo;
import com.gizlo.crud.exception.ObjectNotFoundException;
import com.gizlo.crud.repository.MarcaVehiculoRepository;
import com.gizlo.crud.repository.ModeloVehiculoRepository;
import com.gizlo.crud.request.MarcaVehiculoRequest;
import com.gizlo.crud.request.ModeloVehiculoRequest;
import com.gizlo.crud.response.MarcaVehiculoResponse;
import com.gizlo.crud.response.ModeloVehiculoResponse;
import com.gizlo.crud.response.VehiculoResponse;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModeloVehiculoService {

    /*
     * Definimos los autowired para la inyección de dependencias en esta clase.
     * En este caso, se utiliza para inyectar el repositorio correspondiente.
     */
    @Autowired
    private ModeloVehiculoRepository repo;

    @Autowired
    private MarcaVehiculoRepository marcaRepository;
    /*el resto de metodos son metodos basicos que se implementan mediante el Repositorio*/


    public List<ModeloVehiculoResponse> listar() {
        List<ModeloVehiculo> marca = this.repo.findAll();

        List<ModeloVehiculoResponse> responses = marca.stream().map(
                p -> new ModeloVehiculoResponse(p)).collect(Collectors.toList());
        return responses;
    }


    public String registrar (ModeloVehiculoRequest data) {

        MarcaVehiculo marca = marcaRepository.findById(data.getMarcaVehiculo())
                .orElseThrow(() -> new ObjectNotFoundException("Marca no encontrada"));
        ModeloVehiculo modeloVehiculo = new ModeloVehiculo();

        modeloVehiculo.setNombre(data.getNombreModelo());
        modeloVehiculo.setDescripcion(data.getDescripcionModelo());
        modeloVehiculo.setAnio(data.getAnioModelo());

        modeloVehiculo.setMarca(marca);

        this.repo.save(modeloVehiculo);
        return "Registro correcto";
    }

    public String actualizar(Integer id,ModeloVehiculoRequest data) {

        ModeloVehiculo response = repo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Registro no encontrado "+ id));

        response.setNombre(data.getNombreModelo());
        response.setDescripcion(data.getDescripcionModelo());
        response.setAnio(data.getAnioModelo());

        // Actualizar la marca del modelo
        MarcaVehiculo marca = marcaRepository.findById(data.getMarcaVehiculo())
                .orElseThrow(() -> new ObjectNotFoundException("Marca no encontrada"));
        response.setMarca(marca);
        this.repo.save(response);
        return "Actualizado correctamente";
    }

    public void eliminar(Integer id) {
        ModeloVehiculo response = repo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Registro no encontrado "+ id));
        repo.deleteById(response.getId());
    }

    public ModeloVehiculoResponse listarPorId(Integer idP) {

        ModeloVehiculo modelo = repo.findById(idP)
                .orElseThrow(() -> new ObjectNotFoundException("Modelo no encontrado"));
        ModeloVehiculoResponse vehiculo = new ModeloVehiculoResponse(modelo);
        return vehiculo;
    }

    /*Métodos para la implementacion de la paginacion*/


    public Page<ModeloVehiculoResponse> listaPaginacion(Pageable pageable) {
        Page<ModeloVehiculo> marcaPage = this.repo.findAll(pageable);

        // Mapear cada MarcaVehiculo a MarcaVehiculoResponse
        Page<ModeloVehiculoResponse> responsePage = marcaPage.map(
                modeloVehiculo -> new ModeloVehiculoResponse(modeloVehiculo));

        return responsePage;
    }
}
