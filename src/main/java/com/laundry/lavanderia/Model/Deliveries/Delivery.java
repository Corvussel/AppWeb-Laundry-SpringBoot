package com.laundry.lavanderia.Model.Deliveries;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class Delivery {
    private Long id;
    private String orderNumber;
    private String status;
    private String customerName;
    private LocalDate deliveryDate;
    private String phoneNumber;
    private String paymentMethod;
    private double amountPaid;
    private double amountPending;
    private double totalAmount;
    private List<DeliveryItem> items;
    private String employeeName;     
    private LocalDate completedDate; 
    private String deliveryStatus;  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}