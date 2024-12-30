package com.laundry.lavanderia.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.laundry.lavanderia.Model.Security.Role;
import com.laundry.lavanderia.Model.employee.Employee;
import com.laundry.lavanderia.repository.EmployeeRepository;
import com.laundry.lavanderia.repository.RoleRepository;

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

    /**
     * Metodo para crear el rol admin y el usuario admin si no existen
     * 
     * @param args
     * @return void
     * @throws Exception
     * 
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

        // Crear usuario admin si no existe
        if (employeeRepository.findByEmail("admin@example.com") == null) {
            Employee admin = new Employee();
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setEmail("admin@example.com");
            admin.setPhone("1234567890");
            admin.setRole(adminRole);
            admin.setStatus("activo");
            admin.setPassword(passwordEncoder.encode("admin123"));
            employeeRepository.save(admin);
        }
    }
}
