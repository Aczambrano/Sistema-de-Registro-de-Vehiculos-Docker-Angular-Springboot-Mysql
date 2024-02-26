package com.gizlo.crud.serviceimpl;

import com.gizlo.crud.entity.Products;
import com.gizlo.crud.exception.ObjectNotFoundException;
import com.gizlo.crud.repository.ProductsRepository;
import com.gizlo.crud.request.ProductsRequest;
import com.gizlo.crud.response.ProductsResponse;
import com.gizlo.crud.service.IProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*Definimos esta clase como un servicio implementado por su interface*/
@Service
public class ProductsService implements IProductosService {
    /*
     * Definimos los autowired para la inyección de dependencias en esta clase.
     * En este caso, se utiliza para inyectar el repositorio ProductsRepository.
     */
    @Autowired
    private ProductsRepository repo;
    /*el resto de metodos son metodos basicos que se implementan mediante el
     * Repositorio declarado en ProductsRepository*/

    @Override
    public List<ProductsResponse> listar() {
        List<Products> productoModelo = this.repo.findAll();
        List<ProductsResponse> productoResponses = productoModelo.stream().map(
                p -> new ProductsResponse(p)).collect(Collectors.toList());
        return productoResponses;
    }

    @Override
    public List<ProductsResponse> findProductsActives() {
        List<Products> productoModelo = this.repo.findProductsActives2('a');
        List<ProductsResponse> productoResponses = productoModelo.stream().map(
                p -> new ProductsResponse(p)).collect(Collectors.toList());
        return productoResponses;
    }


    @Override
    public Products registrar(ProductsRequest data) {
        Products producto = new Products(data);
        return repo.save(producto);
    }

    @Override
    public Products actualizar(Integer id,ProductsRequest data) {

        Products producto = repo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Registro no encontrado "+ id));

        producto.setNombre(data.getNombre());
        producto.setDescripcion(data.getDescripcion());
        producto.setCantidad(data.getCantidad());
        producto.setPrecio(data.getPrecio());
        producto.setMarca(data.getMarca());
        producto.setCategoria(data.getCategoria());
        producto.setImagen(data.getImagen());
        producto.setEstado(data.getEstado());

        return repo.save(producto);

    }

    @Override
    public Products deshabilitar(Integer id) {
        Products producto = repo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Registro no encontrado "+ id));
        producto.setEstado('i');
        return repo.save(producto);
    }

    @Override
    public void eliminar(Integer id) {
        Products producto = repo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Registro no encontrado "+ id));
        repo.deleteById(producto.getId());
    }
    @Override
    public Optional<Products> listarPorId(Integer idPersona) {
        return repo.findById(idPersona);
    }

    /*Métodos para la implementacion de la paginacion*/
    @Override
    public Page<Products> listaPaginacion(Pageable pageable) {
        return repo.findProductsActives(pageable);
    }
}
