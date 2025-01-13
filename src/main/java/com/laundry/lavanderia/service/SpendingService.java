package com.laundry.lavanderia.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.laundry.lavanderia.Model.Spending.*;
import com.laundry.lavanderia.repository.SpendingRepository;
import com.laundry.lavanderia.service.interfaces.ISpendingService;
import com.laundry.lavanderia.repository.EmployeeRepository;
import com.laundry.lavanderia.Model.employee.Employee;

import java.time.LocalDate;
import java.util.List;

@Service
public class SpendingService implements ISpendingService {

    @Autowired
    private SpendingRepository spendingRepository; // Inyectar el repositorio de gastos

    @Autowired
    private EmployeeRepository employeeRepository; // Inyectar el repositorio de empleados

    /**
     * Obtiene un resumen de los gastos de la lavanderia. El resumen incluye:
     * - El total de gastos
     * - El monto total de gastos en Materiales
     * - El monto total de gastos en Servicios
     * - El monto total de gastos en Otros
     * - Los 10 gastos más recientes
     * 
     * @return Un objeto de tipo SpendingSummary que contiene el resumen de los
     *         gastos
     */

    @Override
    public SpendingSummary getSpendingSummary() {
        SpendingSummary summary = new SpendingSummary();

        // Obtener montos por categoría
        Double materialsAmount = spendingRepository.sumAmountByCategory("Materiales");
        Double servicesAmount = spendingRepository.sumAmountByCategory("Servicios");
        Double othersAmount = spendingRepository.sumAmountByCategory("Otros");
        Double totalAmount = spendingRepository.getTotalAmount();

        // Asignar montos al resumen de gastos
        summary.setTotalAmount(totalAmount != null ? totalAmount : 0.0);
        summary.setMaterialsAmount(materialsAmount != null ? materialsAmount : 0.0);
        summary.setServicesAmount(servicesAmount != null ? servicesAmount : 0.0);
        summary.setOthersAmount(othersAmount != null ? othersAmount : 0.0);

        // Obtener gastos recientes
        List<Spending> recentSpendings = spendingRepository.findTop10ByOrderByDateDesc();
        summary.setRecentSpendings(recentSpendings);

        return summary;
    }

    @Override
    public void saveSpending(Spending spending) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (userDetails == null) {
            return;
        }
        spending.setEmployee(employeeRepository.findByEmail(userDetails.getUsername()));
        spending.setDate(LocalDate.now());
        spendingRepository.save(spending);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}