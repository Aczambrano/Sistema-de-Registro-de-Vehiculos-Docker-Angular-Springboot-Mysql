package com.gizlo.crud.security.repository;

import com.gizlo.crud.security.entity.Rol;
import com.gizlo.crud.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
