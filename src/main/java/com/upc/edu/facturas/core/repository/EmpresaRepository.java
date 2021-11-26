package com.upc.edu.facturas.core.repository;
import com.upc.edu.facturas.core.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, String>{
    Optional<Empresa> findByRuc(String ruc);
}
