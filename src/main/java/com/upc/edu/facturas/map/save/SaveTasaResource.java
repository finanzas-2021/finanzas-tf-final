package com.upc.edu.facturas.map.save;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class SaveTasaResource {
    private Double valorTasa;
    private Integer diasAño;
    private Date fechaDescuento;

    @Size(max = 15)
    private String periodoCapitalizacion;
}
