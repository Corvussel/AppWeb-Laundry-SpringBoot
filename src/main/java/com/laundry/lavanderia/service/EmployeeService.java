package com.laundry.lavanderia.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.laundry.lavanderia.Model.employee.Employee;
import com.laundry.lavanderia.repository.EmployeeRepository;

@Service
public class EmployeeService {

    // se inyecta el repositorio de empleados
    @Autowired
    private EmployeeRepository employeeRepository;

    // se obtienen todos los empleados en la base de datos
    public List<Employee> getAllEmployees() { 
        return employeeRepository.findAll();
    }

    // se registra el empleado en la base de datos
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    // se obtiene el empleado por id de la base de datos
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // se actualiza el empleado en la base de datos
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    // se elimina el empleado por id de la base de datos
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
