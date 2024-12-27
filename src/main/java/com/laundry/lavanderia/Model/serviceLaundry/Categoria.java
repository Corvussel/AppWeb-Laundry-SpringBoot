package com.laundry.lavanderia.Model.serviceLaundry;

import java.util.List;
import lombok.Data;

@Data
public class Categoria {

    private String nombre;
    private List<Servicio> servicios;
    private boolean activo; // Por defecto, activo
    private String descripcion; // Agregar campo de descripción

    // Constructor sin descripción para compatibilidad hacia atrás
    public Categoria(String nombre, List<Servicio> servicios) {
        this.nombre = nombre;
        this.servicios = servicios;
        this.activo = true;
    }

    // Constructor completo incluyendo descripción
    public Categoria(String nombre, List<Servicio> servicios, boolean activo, String descripcion) {
        this.nombre = nombre;
        this.servicios = servicios;
        this.activo = activo;
        this.descripcion = descripcion;
    }
}
