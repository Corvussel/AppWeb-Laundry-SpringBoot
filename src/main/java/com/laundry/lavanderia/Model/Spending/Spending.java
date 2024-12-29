
package com.laundry.lavanderia.Model.Spending;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Spending {
    private Long id;
    private LocalDate date;
    private String description;
    private String category;
    private String employeeName;
    private Double amount;
}