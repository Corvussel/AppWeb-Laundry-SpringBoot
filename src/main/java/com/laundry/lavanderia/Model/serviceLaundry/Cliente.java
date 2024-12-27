package com.laundry.lavanderia.Model.serviceLaundry;

import java.util.List;

import lombok.Data;
@Data
public class Cliente {

    private Long id;
    private String nombre;
    private String telefono;
    private String genero;
    private String observaciones;
    private String codigoCliente;
    private String direccion;
    private String distrito;
    private List <Cliente> clientes;
    private boolean activo;

    public Cliente(List<Cliente> clientes, String codigoCliente, String direccion, String distrito, String genero, Long id, String nombre, String observaciones, String telefono, boolean activo) {
        this.clientes = clientes;
        this.codigoCliente = codigoCliente;
        this.direccion = direccion;
        this.distrito = distrito;
        this.genero = genero;
        this.id = id;
        this.nombre = nombre;
        this.observaciones = observaciones;
        this.telefono = telefono;
        this.activo= activo;
    }


}
