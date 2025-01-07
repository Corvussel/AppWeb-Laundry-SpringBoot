package com.laundry.lavanderia.Model.serviceLaundry;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data; 
import lombok.NoArgsConstructor;

@Data 
@Entity
@NoArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private double precioUnidad;
    private int cantidad;
    private double subTotal;
    private String detalle;
    @ManyToOne
    @JsonBackReference
    private OrderService orderService;

    public OrderDetails(String nombre, String detalle, double precioUnidad) {
        this.nombre = nombre;
        this.detalle = detalle;
        this.precioUnidad = precioUnidad;
    }
}
