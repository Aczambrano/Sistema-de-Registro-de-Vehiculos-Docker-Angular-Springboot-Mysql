package com.gizlo.crud.security.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {
    private String token;

}
