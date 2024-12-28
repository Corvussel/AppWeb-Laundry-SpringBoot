package com.laundry.lavanderia.Model.Deliveries;

import lombok.Data;

@Data
public class DeliveryItem {
    private String name;
    private String serviceType;
    private double price;
    private int quantity;
    private String notes;
}