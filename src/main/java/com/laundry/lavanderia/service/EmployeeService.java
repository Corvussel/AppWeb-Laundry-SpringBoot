package com.laundry.lavanderia.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.laundry.lavanderia.Model.employee.Employee;
import com.laundry.lavanderia.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Retorna una lista de todos los empleados
     * 
     * @return lista de empleados
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Guarda un empleado en la base de datos. La contraseña
     * del empleado se encripta antes de ser guardada.
     *
     * @param employee el empleado a guardar
     */
    public void saveEmployee(Employee employee) {
        // Encriptar la contraseña antes de guardar 
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
    }

    /**
     * Retorna el empleado con el email especificado.
     * 
     * @param email el email del empleado a buscar
     * @return el empleado con el email especificado o null si no existe
     */
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    /**
     * Retorna el empleado con el Id especificado.
     * 
     * @param id el Id del empleado a buscar
     * @return el empleado con el Id especificado o null si no existe
     */

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    /**
     * Actualiza un empleado en la base de datos.
     * 
     * @param employee el empleado actualizado con los nuevos datos.
     */
    public void updateEmployee(Employee employee) {

        employeeRepository.findById(employee.getId()).ifPresent(updatedEmployee -> {
            updatedEmployee.setFirstName(employee.getFirstName());
            updatedEmployee.setLastName(employee.getLastName());
            updatedEmployee.setPhone(employee.getPhone());
            updatedEmployee.setRole(employee.getRole());
            updatedEmployee.setStatus(employee.getStatus()); 
            employeeRepository.save(updatedEmployee);
        });
    }

    /**
     * Elimina un empleado de la base de datos. En realidad, solo
     * cambia el estado del empleado a "false" para que no se
     * muestre en la lista de empleados.
     * 
     * @param id el Id del empleado a eliminar
     */
    public void deleteEmployee(Long id) {
        employeeRepository.findById(id).ifPresent(employee -> {
            employee.setStatus(false);
            employeeRepository.save(employee);
        });
    }
}
