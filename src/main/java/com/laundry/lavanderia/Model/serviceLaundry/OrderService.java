package com.laundry.lavanderia.Model.serviceLaundry;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import com.laundry.lavanderia.Model.client.cliente;
import com.laundry.lavanderia.Model.employee.Employee;

@Data
@Entity
public class OrderService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoServicio;
    @ManyToOne
    private cliente client;
    private String observacion;
    @OneToMany(mappedBy = "orderService")
    private List<OrderDetails> servicios;
    private double totalServicio;
    private double totalCobro;
    private String metodoPago;
    private double descuento;
    private double precioTotal;
    @ManyToOne
    private Employee employee;
    @ManyToOne
    private Boleta boleta;
}
