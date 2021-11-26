package com.upc.edu.facturas.core.repository;

import com.upc.edu.facturas.core.entity.CostoInicial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostoInicialRepository extends JpaRepository<CostoInicial, String> {
}
