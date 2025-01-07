package com.laundry.lavanderia.Model.serviceLaundry;

import java.util.List;

import com.laundry.lavanderia.Model.client.cliente;
import com.laundry.lavanderia.Model.employee.Employee;
import com.laundry.lavanderia.Model.payment.PaymentMethod;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor 
public class OrderService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Employee employee;
    @ManyToOne
    private cliente client;
    @ManyToOne
    private Boleta boleta; 
    private double totalServicio; 
    private double descuento; 
    private double precioTotal; 
    @ManyToOne
    private PaymentMethod paymentMethod;
    private String observacion;  
    private String status;  
    private String fecha;
    private double totalCobro;  
    @OneToMany(mappedBy = "orderService")
    private List<OrderDetails> servicios;
    private String tipoServicio;
}
