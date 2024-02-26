package com.gizlo.crud.security.service;

import com.gizlo.crud.security.entity.Rol;
import com.gizlo.crud.security.entity.Usuario;
import com.gizlo.crud.security.enums.RolNombre;
import com.gizlo.crud.security.jwt.JwtProvider;
import com.gizlo.crud.security.repository.RolRepository;
import com.gizlo.crud.security.repository.UsuarioRepository;
import com.gizlo.crud.security.request.JwtRequest;
import com.gizlo.crud.security.response.userapi.Root;
import com.gizlo.crud.security.response.userapi.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RolRepository rolRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    JwtProvider jwtProvider;
    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public JwtRequest refreshToken(JwtRequest jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        return new JwtRequest(token);
    }
    public void consumirYGuardarUsuarios() {
        String url = "https://dummyjson.com/users";
        Root root = restTemplate.getForObject(url, Root.class);

        for (User user : root.getUsers()) {
            Usuario usuario = new Usuario();
            usuario.setNombre(user.getFirstName());
            usuario.setEmail(user.getEmail());
            usuario.setNombreUsuario(user.getUsername());
            usuario.setPassword(passwordEncoder.encode(user.getPassword()));
            // Crear el rol "admin" si no existe
            Rol rolAdmin = rolRepository.findByRolNombre(RolNombre.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Rol ROLE_ADMIN no encontrado"));
            Set<Rol> roles = new HashSet<>();
            roles.add(rolAdmin);
            usuario.setRoles(roles);
            usuarioRepository.save(usuario);
        }
    }
}
