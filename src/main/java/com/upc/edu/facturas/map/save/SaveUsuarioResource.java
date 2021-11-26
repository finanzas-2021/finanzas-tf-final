package com.upc.edu.facturas.map.save;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class SaveUsuarioResource {
    @Size(max = 25)
    private String nombre;

    @Size(max = 15)
    private String apellidoPaterno;

    @Size(max = 15)
    private String apellidoMaterno;

    @Size(max = 9)
    private String telefono;

    @Size(max = 40)
    private String email;

    @Size(min= 7, max = 20)
    private String password;
}
