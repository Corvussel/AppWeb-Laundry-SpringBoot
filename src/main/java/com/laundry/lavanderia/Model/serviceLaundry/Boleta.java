package com.laundry.lavanderia.Model.serviceLaundry;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroBoleta;
    private Date fechaGenerada;

    public Boleta() {
        this.fechaGenerada = new Date();
    }

    public Boleta(String numeroBoleta) {
        this.numeroBoleta = numeroBoleta;
        this.fechaGenerada = new Date();
    }
}
