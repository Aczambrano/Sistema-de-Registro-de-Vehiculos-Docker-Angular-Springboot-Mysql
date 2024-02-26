package com.gizlo.crud.security.controller;

import com.gizlo.crud.security.entity.Rol;
import com.gizlo.crud.security.enums.RolNombre;
import com.gizlo.crud.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/rol")
@CrossOrigin
public class RolController {
    @Autowired
    RolService rolService;

    @GetMapping("/crearRoles")
    public ResponseEntity<String> crearRoles() {
        try {

            Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
            Rol rolUser = new Rol(RolNombre.ROLE_USER);

            rolService.save(rolUser);
            rolService.save(rolAdmin);
            return new ResponseEntity<>("Roles guardados correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al guardar Roles: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
