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
        System.out.println("Buscando empleado con ID: " + id);
        return employees.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void updateEmployee(Employee employee) {
        System.out.println("Actualizando empleado con ID: " + employee.getId());
        System.out.println(employee.getFirstName() + " " + employee.getLastName() + " " + employee.getEmail() + " "
                + employee.getPhone() + " " + employee.getRole() + " " + employee.getStatus());
    }

    public void deleteEmployee(Long id) {
        System.out.println("Eliminando empleado con ID: " + id);
        employees.removeIf(emp -> emp.getId().equals(id));

    }

}
