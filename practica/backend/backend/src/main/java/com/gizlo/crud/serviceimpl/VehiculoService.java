package com.gizlo.crud.serviceimpl;
/*Definimos esta clase como un servicio implementado por su interface*/

import com.gizlo.crud.entity.MarcaVehiculo;
import com.gizlo.crud.entity.ModeloVehiculo;
import com.gizlo.crud.entity.Vehiculo;
import com.gizlo.crud.exception.ObjectNotFoundException;
import com.gizlo.crud.repository.ModeloVehiculoRepository;
import com.gizlo.crud.repository.VehiculoRepository;
import com.gizlo.crud.request.ModeloVehiculoRequest;
import com.gizlo.crud.request.VehiculoRequest;
import com.gizlo.crud.response.ModeloVehiculoResponse;
import com.gizlo.crud.response.VehiculoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehiculoService{
    /*
     * Definimos los autowired para la inyección de dependencias en esta clase.
     * En este caso, se utiliza para inyectar el repositorio correspondiente.
     */
    @Autowired
    private VehiculoRepository repo;
    /*el resto de metodos son metodos basicos que se implementan mediante el Repositorio*/

    @Autowired
    private ModeloVehiculoRepository modeloRepository;

    public List<VehiculoResponse> listar() {
        List<Vehiculo> marca = this.repo.findAll();
        List<VehiculoResponse> responses = marca.stream().map(
                p -> new VehiculoResponse(p)).collect(Collectors.toList());
        return responses;
    }

    public Vehiculo registrar (VehiculoRequest data) {

        ModeloVehiculo modelo = modeloRepository.findById(data.getModelo())
                .orElseThrow(() -> new ObjectNotFoundException("Modelo no encontrado"));

        Vehiculo response = new Vehiculo();
        response.setNombre(data.getNombreVehiculo());
        response.setAnio(data.getAnioVehiculo());
        response.setDescripcion(data.getDescripcionVehiculo());
        response.setModelo(modelo);

        return this.repo.save(response);
    }

    public Vehiculo actualizar(Integer id,VehiculoRequest data) {

        Vehiculo response = repo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Registro no encontrado "+ id));

        response.setNombre(data.getNombreVehiculo());
        response.setDescripcion(data.getDescripcionVehiculo());
        response.setAnio(data.getAnioVehiculo());

        // Actualizar la marca del modelo
        ModeloVehiculo modeloVehiculo = modeloRepository.findById(data.getModelo())
                .orElseThrow(() -> new ObjectNotFoundException("Marca no encontrada"));
        response.setModelo(modeloVehiculo);

        return this.repo.save(response);
    }

    public void eliminar(Integer id) {
        Vehiculo response = repo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Registro no encontrado "+ id));
        repo.deleteById(response.getId());
    }


    public VehiculoResponse listarPorId(Integer idP) {

        Vehiculo modelo = repo.findById(idP)
                .orElseThrow(() -> new ObjectNotFoundException("Modelo no encontrado"));
        VehiculoResponse vehiculo = new VehiculoResponse(modelo);
        return vehiculo;
    }

    /*Métodos para la implementacion de la paginacion*/


    public Page<VehiculoResponse> listaPaginacion(Pageable pageable) {
        Page<Vehiculo> marcaPage = this.repo.findAll(pageable);

        // Mapear cada MarcaVehiculo a MarcaVehiculoResponse
        Page<VehiculoResponse> responsePage = marcaPage.map(
                vehiculo -> new VehiculoResponse(vehiculo));

        return responsePage;
    }
}
