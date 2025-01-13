package com.laundry.lavanderia.Config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException; 

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /**
     * Este método se ejecuta cuando la autenticación falla y redirige al usuario a
     * la página de inicio de sesión con un mensaje de error.
     * 
     * @param request   la solicitud que se envió al servidor
     * @param response  la respuesta que se enviará al cliente
     * @param exception la excepción que se lanzó durante la autenticación
     */
     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "Usuario o contraseña incorrectos";

        if (exception.getMessage().equalsIgnoreCase("User is disabled")) {
            errorMessage = "Su cuenta está deshabilitada";
        } else if (exception.getMessage().equalsIgnoreCase("User account has expired")) {
            errorMessage = "Su cuenta ha expirado";
        } else if (exception.getMessage().contains("Bad credentials")) {
            errorMessage = "Usuario o contraseña incorrectos";
        } 
        response.sendRedirect("/userAuth/login?error=" + errorMessage);
    }
}
