package com.laundry.lavanderia.service.interfaces;

import java.util.List;

import com.laundry.lavanderia.Model.employee.Employee;

public interface IEmployeeService {

    List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);

    Employee getEmployeeByEmail(String email);

    Employee getEmployeeById(Long id);

    void updateEmployee(Employee employee);

    void deleteEmployee(Long id);
}
