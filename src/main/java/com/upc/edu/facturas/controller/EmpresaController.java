package com.upc.edu.facturas.controller;

import com.upc.edu.facturas.core.entity.Empresa;
import com.upc.edu.facturas.core.service.EmpresaService;
import com.upc.edu.facturas.map.EmpresaResource;
import com.upc.edu.facturas.map.save.SaveEmpresaResource;
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
@RequestMapping("/api/empresas")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping()
    public ResponseEntity<Page<EmpresaResource>> getAllEmpresas(Pageable pageable) {
        try {
            List<EmpresaResource> empresas = empresaService.getAllEmpresas(pageable)
                    .getContent().stream()
                    .map(this::convertToResource)
                    .collect(Collectors.toList());
            if (empresas.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(new PageImpl<>(empresas, pageable, empresas.size()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{empresaId}")
    public ResponseEntity<EmpresaResource> getEmpresaById(@PathVariable(value = "empresaId") String empresaId) {
        try {
            Optional<Empresa> empresa = empresaService.getEmpresaById(empresaId);
            return empresa.map(value -> ResponseEntity.ok(convertToResource(value)))
                    .orElseGet(() -> ResponseEntity
                            .notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("?ruc=")
    public ResponseEntity<EmpresaResource> getEmpresaByRuc(@RequestParam(name = "ruc") String ruc) {
        try {
            Optional<Empresa> empresa = empresaService.getEmpresaByRuc(ruc);
            return empresa.map(value -> ResponseEntity.ok(convertToResource(value)))
                    .orElseGet(() -> ResponseEntity
                            .notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping()
    public ResponseEntity<EmpresaResource> createEmpresa(@Valid @RequestBody SaveEmpresaResource resource) {
        try {
            Empresa empresa = convertToEntity(resource);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToResource(empresaService.createEmpresa(empresa)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{empresaId}")
    public ResponseEntity<EmpresaResource> updateEmpresa(@PathVariable(name = "empresaId") String empresaId, @Valid @RequestBody SaveEmpresaResource resource) {
        try {
            Empresa empresa = convertToEntity(resource);
            Optional<Empresa> empresaOptional = empresaService.updateEmpresa(empresaId, empresa);
            return empresaOptional.map(value -> ResponseEntity.ok(convertToResource(value)))
                    .orElseGet(() -> ResponseEntity
                            .notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{empresaId}")
    public ResponseEntity<?> deleteEmpresa(@PathVariable(name = "empresaId") String empresaId) {
        try {
            return empresaService.deleteEmpresa(empresaId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    // Auto Mapper
    private Empresa convertToEntity(SaveEmpresaResource resource) {
        return mapper.map(resource, Empresa.class);
    }

    private EmpresaResource convertToResource(Empresa entity) {
        return mapper.map(entity, EmpresaResource.class);
    }
}
