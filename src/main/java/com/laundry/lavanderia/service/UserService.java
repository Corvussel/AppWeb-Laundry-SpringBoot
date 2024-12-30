package com.laundry.lavanderia.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.laundry.lavanderia.Model.employee.Employee;
import com.laundry.lavanderia.repository.EmployeeRepository;

@Service
public class UserService implements UserDetailsService {

    /**
     * Repositorio de empleados para la autenticacion
     * 
     * @param employeeRepository
     * @return repositorio de empleados para la autenticacion
     */
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Encriptador de contraseña para la autenticacion
     * 
     * @param passwordEncoder
     * @return devuelve el encriptador de contraseña
     */

    private PasswordEncoder passwordEncoder;

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Metodo para obtener el usuario por email y encriptar la contraseña
     * antes de devolverlo para la autenticacion del usuario en la aplicacion
     * 
     * @param email
     * @return UserDetails del usuario
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return User.builder()
                .username(employee.getEmail())
                .password(employee.getPassword())
                .roles(employee.getRole().getName().toUpperCase())
                .build();
    }
}