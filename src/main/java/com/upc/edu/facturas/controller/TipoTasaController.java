package com.upc.edu.facturas.controller;

import com.upc.edu.facturas.core.entity.TipoTasa;
import com.upc.edu.facturas.core.service.TipoTasaService;
import com.upc.edu.facturas.map.TipoTasaResource;
import com.upc.edu.facturas.map.save.SaveTipoTasaResource;
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
@RequestMapping("/api/tipo_tasas")
public class TipoTasaController {
    @Autowired
    private TipoTasaService tipoTasaService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping()
    public ResponseEntity<Page<TipoTasaResource>> getAllTipoTasas(Pageable pageable) {
        try {
            List<TipoTasaResource> tipoTasas = tipoTasaService.getAllTipoTasas(pageable)
                    .getContent().stream()
                    .map(this::convertToResource)
                    .collect(Collectors.toList());
            if (tipoTasas.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(new PageImpl<>(tipoTasas, pageable, tipoTasas.size()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{tipoTasaId}")
    public ResponseEntity<TipoTasaResource> getTipoTasaById(@PathVariable(value = "tipoTasaId") String tipoTasaId) {
        try {
            Optional<TipoTasa> tipoTasa = tipoTasaService.getTipoTasaById(tipoTasaId);
            return tipoTasa.map(value -> ResponseEntity.ok(convertToResource(value)))
                    .orElseGet(() -> ResponseEntity
                            .notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping()
    public ResponseEntity<TipoTasaResource> createTipoTasa(@Valid @RequestBody SaveTipoTasaResource resource) {
        try {
            TipoTasa tipoTasa = convertToEntity(resource);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToResource(tipoTasaService.createTipoTasa(tipoTasa)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{tipoTasaId}")
    public ResponseEntity<TipoTasaResource> updateTipoTasa(@PathVariable(name = "tipoTasaId") String tipoTasaId, @Valid @RequestBody SaveTipoTasaResource resource) {
        try {
            TipoTasa tipoTasa = convertToEntity(resource);
            Optional<TipoTasa> tipoTasaOptional = tipoTasaService.updateTipoTasa(tipoTasaId, tipoTasa);
            return tipoTasaOptional.map(value -> ResponseEntity.ok(convertToResource(value)))
                    .orElseGet(() -> ResponseEntity
                            .notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{tipoTasaId}")
    public ResponseEntity<?> deleteTipoTasa(@PathVariable(name = "tipoTasaId") String tipoTasaId) {
        try {
            return tipoTasaService.deleteTipoTasa(tipoTasaId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    // Auto Mapper
    private TipoTasa convertToEntity(SaveTipoTasaResource resource) {
        return mapper.map(resource, TipoTasa.class);
    }

    private TipoTasaResource convertToResource(TipoTasa entity) {
        return mapper.map(entity, TipoTasaResource.class);
    }
}
