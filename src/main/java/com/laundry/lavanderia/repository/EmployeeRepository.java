package com.laundry.lavanderia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.laundry.lavanderia.Model.employee.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
