package com.laundry.lavanderia.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.laundry.lavanderia.Model.Security.Role;
import com.laundry.lavanderia.Model.Security.User;

import java.util.Arrays;

@Service
public class UserService implements UserDetailsService {

    // Se encarga de encriptar la contraseña
    private PasswordEncoder passwordEncoder;

    // Inyecta el password encoder para encriptar la contraseña
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // búsqueda de usuario
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if ("admin".equals(username)) {
            User user = new User();
            user.setId(1L);
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("123"));
            user.setEmail("admin@example.com");
            user.setEnabled(true);

            Role adminRole = new Role();
            adminRole.setId(1L);
            adminRole.setName("ROLE_ADMIN");

            user.setRoles(Arrays.asList(adminRole));
            return user;
        }

        throw new UsernameNotFoundException("Usuario no encontrado");
    }
}