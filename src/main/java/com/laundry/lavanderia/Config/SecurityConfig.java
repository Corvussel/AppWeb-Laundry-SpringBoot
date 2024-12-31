package com.laundry.lavanderia.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import com.laundry.lavanderia.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private UserService userService;

    // inyecta el datasource de la base de datos para la autenticacion y
    // almacenamiento de tokens
    @Autowired
    private DataSource dataSource;

    // inyecta el servicio de usuario
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    } 

    // Configuracion de seguridad de la aplicacion cuando se accede a las rutas
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        userService.setPasswordEncoder(passwordEncoder());
        return http
                // se configura la seguridad de la aplicacion para que no se requiera csrf
                .csrf(csrf -> csrf.disable())
                // se configura la seguridad rutas de la aplicacion y se asignan los roles
                .authorizeHttpRequests(auth -> auth
                        // se permiten las rutas de los recursos estaticos
                        .requestMatchers("/userAuth/**","/css/**", "/js/**", "/images/**").permitAll()
                        // se asignan los roles a las rutas de la aplicacion
                        .requestMatchers("/sales/**", "/spending/**").hasRole("ADMIN")
                        // se asignan los roles a las rutas de la aplicacion
                        .requestMatchers("/deliveries/**").hasAnyRole("ADMIN", "EMPLOYEE")
                        .anyRequest().authenticated()) // se requiere autenticacion para cualquier otra ruta

                // se configura el formulario de login
                .formLogin(form -> form
                        // se configura la pagina de login
                        .loginPage("/userAuth/login")
                        // se configura la ruta de autenticacion
                        .loginProcessingUrl("/userAuth/authenticate")
                        // se redirige a la pagina principal si la autenticacion es correcta
                        .defaultSuccessUrl("/home", true)
                        .permitAll()) // se permite el acceso a la pagina de login

                // se configura la pagina de logout
                .logout(logout -> logout
                        // se configura la ruta de logout para cerrar la sesion
                        .logoutUrl("/userAuth/logout")
                        // se redirige a la pagina de login si el logout es correcto
                        .logoutSuccessUrl("/userAuth/login")
                        .permitAll()) // se permite el acceso a la pagina de logout

                // se configura el recuerdo de la sesion
                .rememberMe(rememberMe -> rememberMe
                        // se configura el parametro de recuerdo
                        .rememberMeParameter("remember")
                        // se configura el token de recuerdo
                        .tokenRepository(persistentTokenRepository())
                        // se configura la duracion del token de recuerdo
                        .tokenValiditySeconds(1209600)
                        // se asigna el servicio de usuario para el recuerdo de la sesion
                        .userDetailsService(userService))
                .build(); // se construye la configuracion de seguridad
    }

    // se encripta la contraseña del usuario
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // se asigna el servicio de autenticacion
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // se asigna el manager de autenticacion
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // se asigna el repositorio de tokens de recuerdo de la sesion
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        // se configura el repositorio de tokens de recuerdo de la sesion
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        // se configura el datasource de la base de datos
        tokenRepository.setDataSource(dataSource); 
        return tokenRepository;
    }
}
