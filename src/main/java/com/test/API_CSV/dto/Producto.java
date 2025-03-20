package com.test.API_CSV.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@Setter
@ToString
@EqualsAndHashCode
public class Producto {
    private String id;
    private String nombre;
    private Double precio;
    private Integer stock;

    public Producto(String id, String nombre, Double precio, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
}
