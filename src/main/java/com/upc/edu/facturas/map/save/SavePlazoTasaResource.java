package com.upc.edu.facturas.map.save;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class SavePlazoTasaResource {
    @NotBlank
    @Size(max = 30)
    private String nombrePlazo;

    private Integer numDias;
}
