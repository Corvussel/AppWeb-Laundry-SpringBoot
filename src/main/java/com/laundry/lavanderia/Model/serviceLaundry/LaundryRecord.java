package com.laundry.lavanderia.Model.serviceLaundry;

 
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.ManyToOne;
import java.util.List;
import lombok.Data;
import com.laundry.lavanderia.Model.employee.Employee;

@Data
public class LaundryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoServicio;
    private String clienteId;
    private String nombreCliente;
    private String observacion;
    @ElementCollection
    private List<Servicio> servicios;
    private double totalServicio;
    private double totalCobro;
    private String metodoPago;
    private double descuento;
    private double precioTotal;
    @ManyToOne
    private Employee employee;
}
