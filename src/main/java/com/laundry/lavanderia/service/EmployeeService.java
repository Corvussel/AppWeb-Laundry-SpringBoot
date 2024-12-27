package com.laundry.lavanderia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.laundry.lavanderia.Model.employee.Employee;

@Service
public class EmployeeService {

    private List<Employee> employees; 

    public EmployeeService() {
        employees = new ArrayList<>(); 
        employees.add(new Employee(1L, "James", "Caballero", "james@email.com", "992-676-986", "admin", "activo"));
        employees.add(new Employee(2L, "Russel", "Flores", "russel@email.com", "992-676-986", "empleado", "inactivo"));
    }


    public List<Employee> getAllEmployees() {
        return employees;
    }
 
    public Employee getEmployeeById(Long id) {
        return employees.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
 
    public void saveEmployee(Employee employee) {
       
        Long newId = employees.stream()
                .mapToLong(Employee::getId)
                .max()
                .orElse(0L) + 1;
        employee.setId(newId);
        employees.add(employee);
    }
 
    public void deleteEmployee(Long id) {
        employees.removeIf(emp -> emp.getId().equals(id));
    }
 
    public void updateEmployee(Employee employee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(employee.getId())) {
                employees.set(i, employee);
                break;
            }
        }
    }
}
