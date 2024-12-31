package com.laundry.lavanderia.Model.serviceLaundry;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
    private OrderService orderService;

    public OrderDetails(String nombre, String detalle, double precioUnidad) {
        this.nombre = nombre;
        this.detalle = detalle;
        this.precioUnidad = precioUnidad;
    }
}
