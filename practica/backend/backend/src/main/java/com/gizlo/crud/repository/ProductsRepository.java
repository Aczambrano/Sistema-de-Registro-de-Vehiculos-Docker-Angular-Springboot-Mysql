package com.gizlo.crud.repository;

import com.gizlo.crud.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*Repositorio que sirve para poder acceder a los metodos de
* JPAREpository*/
@Repository
public interface ProductsRepository extends JpaRepository<Products,Integer> {
    @Query(value = "SELECT * FROM jwt.products WHERE estado = 'a'",
            countQuery = "SELECT COUNT(*) FROM jwt.products  WHERE estado = 'a'",
            nativeQuery = true)
    Page<Products> findProductsActives(Pageable Pageable);

    @Query(value = "SELECT * FROM jwt.products WHERE estado = 'a'", nativeQuery = true)
    List<Products> findProductsActives2(Character character);

}
