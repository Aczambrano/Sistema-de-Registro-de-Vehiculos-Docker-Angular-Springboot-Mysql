package com.gizlo.crud.controller;

import com.gizlo.crud.entity.Products;
import com.gizlo.crud.request.ProductsRequest;
import com.gizlo.crud.response.ProductsResponse;
import com.gizlo.crud.service.IProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*Controller de productos para poder gestionar las solicitudes http*/
@RestController
@CrossOrigin()
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private IProductosService service;

    /* Método para listar todos los productos */
    @GetMapping("/listar")
    public ResponseEntity<List<ProductsResponse>> listarProducts() {
        List<ProductsResponse> obj = service.listar();
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
    @GetMapping("/listarActivos")
    public ResponseEntity<List<ProductsResponse>> findProductsActives() {
        List<ProductsResponse> obj = service.findProductsActives();
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /* Método para paginación de productos */
    @GetMapping(value = "/paginacion")
    public ResponseEntity<Page<Products>> listaPaginacion(Pageable pageable) {
        // Obtiene una página de productos según la paginación especificada
        Page<Products> p = service.listaPaginacion(pageable);
        if (p.hasContent()) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        }
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    /* Método para registrar un nuevo producto */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registrar")
    public ResponseEntity<Products> registrarProducts(@RequestBody ProductsRequest productos) {
        Products obj = service.registrar(productos);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    /* Método para actualizar un producto por su ID */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Products> actualizarProducts(@PathVariable Integer id,
                                                       @RequestBody ProductsRequest products) {
        try {
            Products productActual = service.actualizar(id, products);
            return new ResponseEntity<>(productActual, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Método para eliminar un producto por su ID */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarProducts(@PathVariable Integer id) {
        try {
            service.eliminar(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Método para eliminar un producto por su ID */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deshabilitar/{id}")
    public ResponseEntity<Products> deshabilitar(@PathVariable Integer id) {
        try {
            service.deshabilitar(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    /* Método para listar un producto por su ID */

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Products> listarProductsPorId(@PathVariable Integer id) throws Exception {

        try {
            Optional<Products> obj = service.listarPorId(id);
            return new ResponseEntity<>(obj.get(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
