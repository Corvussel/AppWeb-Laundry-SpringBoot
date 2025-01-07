package com.laundry.lavanderia.Model.Spending;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;
import com.laundry.lavanderia.Model.employee.Employee;

@Data
@Entity
@Table(name = "spendings")
public class Spending {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private String category;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    
    @Column(nullable = false)
    private Double amount;
}