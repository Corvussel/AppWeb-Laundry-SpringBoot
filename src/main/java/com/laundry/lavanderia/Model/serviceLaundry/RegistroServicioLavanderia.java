package com.laundry.lavanderia.Model.serviceLaundry;

import java.util.List;
import lombok.Data;
import com.laundry.lavanderia.Model.client.cliente;

@Data
public class RegistroServicioLavanderia {
    private String tipoServicio;       
    private cliente client;
    private String observacion;
    private List<Servicio> servicios;
    private double totalServicio;
    private double totalCobro;
    private String metodoPago;         
    private double descuento;
    private double precioTotal;
}
