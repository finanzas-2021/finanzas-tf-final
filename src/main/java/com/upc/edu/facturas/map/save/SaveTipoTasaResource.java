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
public class SaveTipoTasaResource {
    @Size(max = 8)
    private String nombreTipoTasa;
}
