package com.laundry.lavanderia.Model.Sales;

import lombok.Data;

@Data
public class SaleItem {
    private String name;
    private String serviceType;
    private Integer quantity;
    private Double price;
    private String notes;
}
