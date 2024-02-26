package com.gizlo.crud.security.controller;

import com.gizlo.crud.request.Mensaje;
import com.gizlo.crud.security.entity.Rol;
import com.gizlo.crud.security.entity.Usuario;
import com.gizlo.crud.security.enums.RolNombre;
import com.gizlo.crud.security.jwt.JwtProvider;
import com.gizlo.crud.security.request.JwtRequest;
import com.gizlo.crud.security.request.LoginRequest;
import com.gizlo.crud.security.request.RegistroUsuarioRequest;
import com.gizlo.crud.security.service.RolService;
import com.gizlo.crud.security.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;


    @GetMapping("/usuariosExternos")
    public ResponseEntity<String> consumirYGuardarUsuarios() {
        try {
            usuarioService.consumirYGuardarUsuarios();
            return new ResponseEntity<>("Usuarios guardados correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al guardar usuarios: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody RegistroUsuarioRequest nuevoUsuario,
                                   BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("ese nombre de Usuario ya existe"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }



    @PostMapping("/login")
    public ResponseEntity<JwtRequest> login(@Valid @RequestBody LoginRequest loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        JwtRequest jwtDto = new JwtRequest(jwt);
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
/*
    @PostMapping("/refresh")
    public ResponseEntity<JwtRequest> refreshToken(@RequestBody JwtRequest jwtRequest) throws ParseException {
        String token = jwtProvider.refreshToken(jwtRequest);
        JwtRequest jwt = new JwtRequest(token);
        return  new ResponseEntity<>(jwt,HttpStatus.OK);
    }*/

    @PostMapping("/refresh")
    public ResponseEntity<JwtRequest> refreshToken(@RequestBody JwtRequest jwtDto) throws ParseException {
        return  new ResponseEntity<>(usuarioService.refreshToken(jwtDto),HttpStatus.OK);
    }

}
