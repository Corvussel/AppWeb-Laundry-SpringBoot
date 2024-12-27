package com.laundry.lavanderia.Model.serviceLaundry;

import java.util.List;

import lombok.Data;

@Data
public class Categoria {

    private String nombre;    
    private List<Servicio> servicios;
    private boolean activo ; // Por defecto, activo
    
    public Categoria(String nombre, List<Servicio> servicios) {
        this.nombre = nombre;
        this.servicios = servicios;
        this.activo = true;
    }
    public Categoria(String nombre, List<Servicio> servicios, boolean activo) {
        this.nombre = nombre;
        this.servicios = servicios;
        this.activo = activo;
    }
}
