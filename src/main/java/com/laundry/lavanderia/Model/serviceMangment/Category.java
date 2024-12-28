package com.laundry.lavanderia.Model.serviceMangment;

import java.util.List;

import lombok.Data;

@Data
public class Category {
    private Long id;
    private String name;
    private String description;
    private boolean active;
    private List<ServiceLaundry> services;
    
    // Constructor
    public Category(Long id, String name, String description, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
    }
}
