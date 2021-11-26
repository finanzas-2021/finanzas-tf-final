package com.upc.edu.facturas.map;


import com.upc.edu.facturas.core.entity.AuditModel;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor

public class EmpresaResource extends AuditModel {
    private String id;
    private String razonSocial;
    private String ruc;

}
