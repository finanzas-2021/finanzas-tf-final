package com.upc.edu.facturas.map;

import com.upc.edu.facturas.core.entity.AuditModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CarteraResource extends AuditModel {
    private String id;
    private Double totalValorRecibido;
    private Double totalTCEA;
}
