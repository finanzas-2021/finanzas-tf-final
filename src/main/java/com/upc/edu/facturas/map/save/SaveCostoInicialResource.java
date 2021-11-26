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
public class SaveCostoInicialResource {
    @NotBlank
    @Size(max = 20)
    private String motivo;

    @NotBlank
    private Double monto;
}
