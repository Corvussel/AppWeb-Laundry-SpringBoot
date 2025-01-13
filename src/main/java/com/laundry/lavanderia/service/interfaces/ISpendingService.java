package com.laundry.lavanderia.service.interfaces;

import java.util.List;

import com.laundry.lavanderia.Model.Spending.Spending;
import com.laundry.lavanderia.Model.Spending.SpendingSummary;
import com.laundry.lavanderia.Model.employee.Employee;

public interface ISpendingService {

    SpendingSummary getSpendingSummary();
    
    void saveSpending(Spending spending);

    List<Employee> getAllEmployees();

}
