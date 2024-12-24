package com.laundry.lavanderia.Model.serviceLaundry;

import java.util.List;
import lombok.Data;

@Data
public class RegistroServicioLavanderia {
    private String tipoServicio;       
    private String clienteId;
    private String nombreCliente;
    private String observacion;
    private List<Servicio> servicios;
    private double totalServicio;
    private double totalCobro;
    private String metodoPago;         
    private double descuento;
    private double totalCobrar;
}
