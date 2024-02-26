package com.gizlo.crud.repository;

import com.gizlo.crud.entity.Products;
import com.gizlo.crud.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*Repositorio que sirve para poder acceder a los metodos de
 * JPAREpository*/
@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo,Integer> {
}
