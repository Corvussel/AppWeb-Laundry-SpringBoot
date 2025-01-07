package com.laundry.lavanderia.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.laundry.lavanderia.Model.Security.Role;
import com.laundry.lavanderia.Model.employee.Employee;
import com.laundry.lavanderia.Model.payment.PaymentMethod;
import com.laundry.lavanderia.repository.EmployeeRepository;
import com.laundry.lavanderia.repository.RoleRepository;
import com.laundry.lavanderia.repository.PaymentMethodRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    // Inyectar el repositorio de empleados para la autenticacion del admin
    @Autowired
    private EmployeeRepository employeeRepository;
    // Inyectar el repositorio de roles para la autenticacion del admin
    @Autowired
    private RoleRepository roleRepository;
    // Inyectar el encriptador de contraseñas para la autenticacion del admin
    @Autowired
    private PasswordEncoder passwordEncoder;
    // Inyectar el repositorio de métodos de pago
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    /**
     * Crea los datos iniciales de la base de datos.
     * <p>
     * Crea el rol de administrador y el usuario administrador si no existen.
     * Crea los métodos de pago por defecto si no existen.
     * </p>
     * 
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        // Crear rol admin si no existe
        Role adminRole = roleRepository.findByName("admin");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setName("admin");
            roleRepository.save(adminRole);
        }
        // Crear rol empleado si no existe
        Role employeeRole = roleRepository.findByName("empleado");
        if (employeeRole == null) {
            employeeRole = new Role();
            employeeRole.setName("empleado");
            roleRepository.save(employeeRole);
        }

        // Crear usuario admin si no existe
        if (employeeRepository.findByEmail("admin@example.com") == null) {
            Employee admin = new Employee();
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setEmail("admin@example.com");
            admin.setPhone("1234567890");
            admin.setRole(adminRole);
            admin.setStatus(true);
            admin.setPassword(passwordEncoder.encode("admin123"));
            employeeRepository.save(admin);
        }

        // Crear métodos de pago por defecto
        createDefaultPaymentMethod("Yape", "Pago mediante Yape");
        createDefaultPaymentMethod("Plin", "Pago mediante Plin");
        createDefaultPaymentMethod("Efectivo", "Pago en efectivo");
        createDefaultPaymentMethod("Transferencia", "Pago mediante transferencia bancaria");
        createDefaultPaymentMethod("POS", "Pago mediante POS");

    }

    /**
     * Crea un método de pago por defecto si no existe
     * 
     * @param name        nombre del método de pago
     * @param description descripción del método de pago
     */
    private void createDefaultPaymentMethod(String name, String description) {
        if (paymentMethodRepository.findByName(name) == null) {
            PaymentMethod paymentMethod = new PaymentMethod();
            paymentMethod.setName(name);
            paymentMethod.setDescription(description);
            paymentMethod.setActive(true);
            paymentMethodRepository.save(paymentMethod);
        }
    }

}
