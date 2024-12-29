
package com.laundry.lavanderia.service;

import org.springframework.stereotype.Service;
import com.laundry.lavanderia.Model.Spending.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SpendingService {
    
    public SpendingSummary getSpendingSummary() {
        SpendingSummary summary = new SpendingSummary();
        
        // Resumen general de gastos
        summary.setTotalAmount(2500.00);
        summary.setMaterialsAmount(1200.00);
        summary.setServicesAmount(800.00);
        summary.setOthersAmount(500.00);
        
        // Tendencias mensuales de gastos
        summary.setMonthlyTrends(Arrays.asList(2100.0, 1800.0, 2500.0, 2200.0, 2600.0, 1500.0));
        
        // Gastos recientes
        List<Spending> recentSpendings = new ArrayList<>();
        
        Spending spending1 = new Spending();
        spending1.setDate(LocalDate.of(2024, 1, 15));
        spending1.setDescription("Detergente Industrial");
        spending1.setCategory("Materiales");
        spending1.setEmployeeName("Flores");
        spending1.setAmount(450.00);
        recentSpendings.add(spending1);

        Spending spending2 = new Spending();
        spending2.setDate(LocalDate.of(2024, 1, 14));
        spending2.setDescription("Servicio de Agua");
        spending2.setCategory("Servicios");
        spending2.setEmployeeName("Caballero");
        spending2.setAmount(280.00);
        recentSpendings.add(spending2);

        summary.setRecentSpendings(recentSpendings);
        
        return summary;
    }
}