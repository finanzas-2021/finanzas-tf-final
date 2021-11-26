package com.upc.edu.facturas.map;

import com.upc.edu.facturas.core.entity.AuditModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TasaResource extends AuditModel {
    private String id;
    private Double valorTasa;
    private Integer diasAño;
    private Date fechaDescuento;
    private String periodoCapitalizacion;
}
