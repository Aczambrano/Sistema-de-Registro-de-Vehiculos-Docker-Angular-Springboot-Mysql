package com.gizlo.crud.controller;

import com.gizlo.crud.entity.ModeloVehiculo;
import com.gizlo.crud.entity.Vehiculo;
import com.gizlo.crud.request.ModeloVehiculoRequest;
import com.gizlo.crud.request.VehiculoRequest;
import com.gizlo.crud.response.MarcaVehiculoResponse;
import com.gizlo.crud.response.ModeloVehiculoResponse;
import com.gizlo.crud.response.VehiculoResponse;
import com.gizlo.crud.serviceimpl.ModeloVehiculoService;
import com.gizlo.crud.serviceimpl.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@CrossOrigin()
@RequestMapping("/modelo")
public class ModeloVehiculoController {
    @Autowired
    private ModeloVehiculoService service;

    /* Método para listar todos los productos */
    @GetMapping("/listar")
    public ResponseEntity<List<ModeloVehiculoResponse>> listar() {
        List<ModeloVehiculoResponse> obj = service.listar();
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
    /* Método para paginación de productos */

    @GetMapping(value = "/paginacion")
    public ResponseEntity<Page<ModeloVehiculoResponse>> listaPaginacion(Pageable pageable) {
        // Obtiene una página de productos según la paginación especificada
        Page<ModeloVehiculoResponse> p = service.listaPaginacion(pageable);
        if (p.hasContent()) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        }
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    /* Método para registrar un nuevo producto */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registrar")
    public ResponseEntity<Map<String, String>> registrar(@RequestBody ModeloVehiculoRequest data) {
        this.service.registrar(data);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Vehículo registrado exitosamente");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /* Método para actualizar un producto por su ID */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Map<String, String>> actualizar(@PathVariable Integer id,
                                             @RequestBody ModeloVehiculoRequest data) {
        try {
            service.actualizar(id, data);
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Vehículo registrado exitosamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Método para eliminar un producto por su ID */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            service.eliminar(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Método para listar un producto por su ID */

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<ModeloVehiculoResponse> listarPorId(@PathVariable Integer id) throws Exception {

        try {
            ModeloVehiculoResponse obj = service.listarPorId(id);
            return new ResponseEntity<>(obj, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
