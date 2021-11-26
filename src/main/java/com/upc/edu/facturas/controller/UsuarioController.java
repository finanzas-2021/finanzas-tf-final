package com.upc.edu.facturas.controller;

import com.upc.edu.facturas.core.entity.Usuario;
import com.upc.edu.facturas.core.service.UsuarioService;
import com.upc.edu.facturas.map.UsuarioResource;
import com.upc.edu.facturas.map.save.SaveUsuarioResource;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
//@CrossOrigin
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping()
    public ResponseEntity<Page<UsuarioResource>> getAllUsuarios(Pageable pageable) {
        try {
            List<UsuarioResource> usuarios = usuarioService.getAllUsuarios(pageable)
                    .getContent().stream()
                    .map(this::convertToResource)
                    .collect(Collectors.toList());
            if (usuarios.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(new PageImpl<>(usuarios, pageable, usuarios.size()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioResource> getUsuarioById(@PathVariable(value = "usuarioId") String usuarioId) {
        try {
            Optional<Usuario> usuario = usuarioService.getUsuarioById(usuarioId);
            return usuario.map(value -> ResponseEntity.ok(convertToResource(value)))
                    .orElseGet(() -> ResponseEntity
                            .notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("?name=")
    public ResponseEntity<UsuarioResource> getUsuarioByName(@RequestParam(name = "name") String name) {
        try {
            Optional<Usuario> usuario = usuarioService.getUsuarioByName(name);
            return usuario.map(value -> ResponseEntity.ok(convertToResource(value)))
                    .orElseGet(() -> ResponseEntity
                            .notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping()
    public ResponseEntity<UsuarioResource> createUsuario(@Valid @RequestBody SaveUsuarioResource resource) {
        try {
            Usuario usuario = convertToEntity(resource);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToResource(usuarioService.createUsuario(usuario)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<UsuarioResource> updateUsuario(@PathVariable(name = "usuarioId") String usuarioId, @Valid @RequestBody SaveUsuarioResource resource) {
        try {
            Usuario usuario = convertToEntity(resource);
            Optional<Usuario> usuarioOptional = usuarioService.updateUsuario(usuarioId, usuario);
            return usuarioOptional.map(value -> ResponseEntity.ok(convertToResource(value)))
                    .orElseGet(() -> ResponseEntity
                            .notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<?> deleteUsuario(@PathVariable(name = "usuarioId") String usuarioId) {
        try {
            return usuarioService.deleteUsuario(usuarioId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    // Auto Mapper
    private Usuario convertToEntity(SaveUsuarioResource resource) {
        return mapper.map(resource, Usuario.class);
    }

    private UsuarioResource convertToResource(Usuario entity) {
        return mapper.map(entity, UsuarioResource.class);
    }
}
