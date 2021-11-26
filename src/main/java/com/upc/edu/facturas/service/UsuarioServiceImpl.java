package com.upc.edu.facturas.service;

import com.upc.edu.facturas.core.entity.Usuario;
import com.upc.edu.facturas.core.repository.UsuarioRepository;
import com.upc.edu.facturas.core.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public Page<Usuario> getAllUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Override
    public Optional<Usuario> getUsuarioById(String usuarioId) {
        return usuarioRepository.findById(usuarioId);
    }

    @Override
    public Optional<Usuario> getUsuarioByName(String name) {
        return usuarioRepository.findByName(name);
    }

    @Override
    @Transactional
    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Optional<Usuario> updateUsuario(String usuarioId, Usuario usuarioRequest) {
        return usuarioRepository.findById(usuarioId).map(usuario -> {
            usuario.setNombre(usuarioRequest.getNombre());
            usuario.setApellidoPaterno(usuarioRequest.getApellidoPaterno());
            usuario.setApellidoMaterno(usuarioRequest.getApellidoMaterno());
            usuario.setTelefono(usuarioRequest.getTelefono());
            usuario.setEmail(usuarioRequest.getEmail());
            usuario.setPassword(usuarioRequest.getPassword());
            return usuarioRepository.save(usuario);
        });
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteUsuario(String usuarioId) {
        return usuarioRepository.findById(usuarioId).map(usuario -> {
            usuarioRepository.delete(usuario);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

}
