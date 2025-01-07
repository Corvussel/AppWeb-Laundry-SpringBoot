package com.laundry.lavanderia.Model.serviceLaundry;

import java.util.List;

import lombok.Data;

@Data
public class Categoria {

    private String nombre;    
    private List<OrderDetails> servicios;
    
    public Categoria(String nombre, List<OrderDetails> servicios) {
        this.nombre = nombre;
        this.servicios = servicios;
    }
}
