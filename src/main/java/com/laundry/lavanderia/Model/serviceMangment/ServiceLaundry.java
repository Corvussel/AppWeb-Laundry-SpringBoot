package com.laundry.lavanderia.Model.serviceMangment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ServiceLaundry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String description;
    private boolean active;
    private int duration;
    @ManyToOne
    private Category category;

    public ServiceLaundry() {
    }

    public ServiceLaundry(Long id, String name, Category category, double price, String description, boolean active, int duration) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.active = active;
        this.duration = duration;
    }
}
