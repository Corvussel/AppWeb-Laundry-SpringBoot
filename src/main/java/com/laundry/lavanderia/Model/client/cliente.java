package com.laundry.lavanderia.Model.client;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String telefono;
    private String direccion;
    private Boolean activo;
    private String fechaRegistro;

    public String ToString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Telefono: " + telefono + ", Direccion: " + direccion
                + ", Activo: " + activo + ", Fecha de Registro: " + fechaRegistro;
    }
}
