package com.upc.edu.facturas.core.service;
import com.upc.edu.facturas.core.entity.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface EmpresaService {
    Page<Empresa> getAllEmpresas(Pageable pageable);

    Optional<Empresa> getEmpresaById(String empresaId);

    Optional<Empresa> getEmpresaByRuc(String ruc);

    Empresa createEmpresa(Empresa empresa);

    Optional<Empresa> updateEmpresa(String empresaId, Empresa empresaRequest);

    ResponseEntity<?> deleteEmpresa(String empresaId);

}
