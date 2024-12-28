package com.laundry.lavanderia.Model.serviceMangment;

import lombok.Data;

@Data
public class ServiceLaundry {
    private Long id;
    private String name;
    private Category category;
    private double price;
    private String description;
    private boolean active;
    private int estimatedTime;

    // Constructor
    public ServiceLaundry(Long id, String name, Category category, double price, String description, boolean active, int estimatedTime) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.active = active;
        this.estimatedTime = estimatedTime;
    }
}
