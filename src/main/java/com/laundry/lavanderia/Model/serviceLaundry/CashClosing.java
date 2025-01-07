package com.laundry.lavanderia.Model.serviceLaundry;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.laundry.lavanderia.Model.employee.Employee;

@Data
@Entity
@NoArgsConstructor
public class CashClosing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime closingDate;
    private double totalIncome;
    private double totalExpenses;
    private double finalBalance;
    private String observations;  
    @ManyToOne
    private Employee employee;
}
