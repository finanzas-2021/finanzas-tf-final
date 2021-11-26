package com.upc.edu.facturas.map.save;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class SaveCarteraResource {
    @NotBlank
    private Double totalValorRecibido;
    @NotBlank
    private Double totalTCEA;
}
