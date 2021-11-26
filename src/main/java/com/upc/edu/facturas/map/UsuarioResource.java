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
public class UsuarioResource extends AuditModel {
    private String id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String email;
    private String password;
}
