package com.laundry.lavanderia.Model.serviceLaundry;

import lombok.Data;

@Data
public class Servicio {

    private String nombre;
    private String descripcion;

    public Servicio(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

}
