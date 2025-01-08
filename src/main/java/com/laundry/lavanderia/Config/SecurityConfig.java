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
import org.springframework.security.web.access.AccessDeniedHandler;
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

    // se inyecta el datasource de la base de datos para el repositorio de tokens de
    // recuerdo de la sesion en la base de datos de la aplicacion de Spring Security
    @Autowired
    private DataSource dataSource;

    /**
     * Inyecta el servicio de autenticacion de usuario y se asigna al campo
     * de la clase. Este servicio se utiliza en la autenticacion y en el
     * control de acceso de la aplicacion.
     * 
     * @param userService el servicio de autenticacion de usuario
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Configura la seguridad de la aplicacion para que no se requiera csrf y
     * asigna los roles a las rutas de la aplicacion.
     * 
     * @param http objeto de configuracion de seguridad
     * @return configuracion de seguridad
     * @throws Exception si ocurre un error en la configuracion de seguridad
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                // se configura la seguridad de la aplicacion para que no se requiera csrf
                .csrf(csrf -> csrf.disable())
                // se configura la seguridad rutas de la aplicacion y se asignan los roles
                .authorizeHttpRequests(auth -> auth
                        // se permiten las rutas de los recursos estaticos
                        .requestMatchers("/userAuth/**", "/css/**", "/js/**", "/images/**").permitAll()
                        // se asignan los roles a las rutas de la aplicacion
                        .requestMatchers("/cashClosing/**", "/management/**", "/employees/**").hasRole("ADMIN")
                        // se asignan los roles a las rutas de la aplicacion
                        .requestMatchers("/deliveries/**").hasAnyRole("ADMIN", "EMPLEADO")
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

                // se configura el manejador de excepciones
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(accessDeniedHandler()))
                .build(); // se construye la configuracion de seguridad
    }

    /**
     * Se encarga de asignar el manager de autenticacion para
     * realizar la autenticacion de los usuarios. Se utiliza el
     * manager de autenticacion por defecto de Spring Security.
     * 
     * @param config la configuracion de autenticacion
     * @return el manager de autenticacion
     * @throws Exception si ocurre un error al obtener el manager
     *                   de autenticacion
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    

    /**
     * Manejador de excepciones de acceso denegado.
     * Se encarga de redirigir a la pagina de acceso denegado
     * cuando se produce una excepcion de acceso denegado.
     * 
     * @return el manejador de excepciones de acceso denegado
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.sendRedirect("/userAuth/access-denied");
        };
    }

    /**
     * Se encarga de asignar el servicio de autenticacion
     * para realizar la autenticacion de los usuarios.
     * Se utiliza el servicio de autenticacion por defecto
     * de Spring Security con el algoritmo de encriptacion
     * BCrypt.
     * 
     * @return el proveedor de autenticacion
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // se crea el proveedor de autenticacion
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // se asigna el servicio de autenticacion de usuario
        authProvider.setUserDetailsService(userService);
        // se asigna el encoder de contraseñas
        authProvider.setPasswordEncoder(passwordEncoder());
        // se retorna el proveedor de autenticacion
        return authProvider;
    }

    /**
     * Se encarga de encriptar las contraseñas de los usuarios.
     * Se utiliza el algoritmo de encriptacion BCrypt.
     * 
     * @return el encoder de contraseñas
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Se encarga de asignar el repositorio de tokens de recuerdo
     * de la sesion. Se utiliza el repositorio de tokens de recuerdo
     * por defecto de Spring Security que almacena los tokens
     * de recuerdo en la base de datos.
     * 
     * @return el repositorio de tokens de recuerdo
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        // se configura el repositorio de tokens de recuerdo de la sesion
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        // se configura el datasource de la base de datos
        tokenRepository.setDataSource(dataSource);
        // se retorna el repositorio de tokens de recuerdo de la sesion
        return tokenRepository;
    }
}
