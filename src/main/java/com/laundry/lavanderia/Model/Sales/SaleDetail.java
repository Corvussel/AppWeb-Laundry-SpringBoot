package com.laundry.lavanderia.Model.Sales;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class SaleDetail {
    private Long id;
    private String orderNumber;
    private String customerName;
    private String customerType;
    private String phoneNumber;
    private String status;
    private LocalDate date;
    private List<SaleItem> items;
    private Double totalAmount;
    private String serviceType;  
}
