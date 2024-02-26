package com.gizlo.crud.service;

import com.gizlo.crud.entity.Products;
import com.gizlo.crud.request.ProductsRequest;
import com.gizlo.crud.response.ProductsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/*Interfaz de el servicio producto, aqui ponemos nuestros meotodos (en su mayoría CRUD)
que nuestro servicio
* implementará y tambien empezamos a realizar la paginacion*/
public interface IProductosService {
    List<ProductsResponse> listar();

    Products registrar(ProductsRequest p);
    Products actualizar (Integer id,ProductsRequest p);
    void eliminar (Integer idP);

    Products deshabilitar (Integer idP);

    Optional<Products> listarPorId(Integer idP);
    List<ProductsResponse> findProductsActives();


    Page<Products> listaPaginacion(Pageable pageable);
}
