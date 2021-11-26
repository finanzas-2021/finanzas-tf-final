package com.upc.edu.facturas.core.service;

import com.upc.edu.facturas.core.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UsuarioService {

    Page<Usuario> getAllUsuarios(Pageable pageable);

    Optional<Usuario> getUsuarioById(String usuarioId);

    Optional<Usuario> getUsuarioByName(String name);

    Usuario createUsuario(Usuario usuario);

    Optional<Usuario> updateUsuario(String usuarioId, Usuario usuarioRequest);

    ResponseEntity<?> deleteUsuario(String usuarioId);
}
