package com.laundry.lavanderia.Model.serviceLaundry;

import java.util.List;

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

    public Cliente(List<Cliente> clientes, String codigoCliente, String direccion, String distrito, String genero, Long id, String nombre, String observaciones, String telefono) {
        this.clientes = clientes;
        this.codigoCliente = codigoCliente;
        this.direccion = direccion;
        this.distrito = distrito;
        this.genero = genero;
        this.id = id;
        this.nombre = nombre;
        this.observaciones = observaciones;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }






}
