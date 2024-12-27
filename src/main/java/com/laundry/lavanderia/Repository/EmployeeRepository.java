package com.laundry.lavanderia.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.laundry.lavanderia.Model.Employee.Employee;

@Repository
public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        // Datos simulados
        employees.add(new Employee(1L, "James", "Caballero", "james@email.com", "992-676-986", "Admin", true));
        employees.add(new Employee(2L, "María", "González", "maria@email.com", "989-123-456", "Empleado", false));
        employees.add(new Employee(3L, "James Nícolas", "Velezmoro Navarro", "jamesvelezmoro@email.com", "992-676-986", "Admin", true));
        employees.add(new Employee(4L, "Rusel Maríaaaaaaaaaaaaaaaa", "González Flores", "maria@email.com", "989-123-456", "Empleado", false));
    }

    // Obtener todos los empleados
    public List<Employee> findAll() {
        return employees;
    }

    // Buscar empleado por ID
    public Optional<Employee> findById(Long id) {
        return employees.stream().filter(emp -> emp.getId().equals(id)).findFirst();
    }

    // Agregar o actualizar un empleado
    public Employee save(Employee employee) {
        // Si el empleado existe, se actualiza
        findById(employee.getId()).ifPresent(existing -> {
            employees.remove(existing);
        });
        employees.add(employee);
        return employee;
    }

    // Eliminar empleado por ID
    public boolean deleteById(Long id) {
        return employees.removeIf(emp -> emp.getId().equals(id));
    }
    
}
