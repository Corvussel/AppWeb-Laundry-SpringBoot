package com.laundry.lavanderia.Controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laundry.lavanderia.Model.LoginDates.Login;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/userAuth")
@RequiredArgsConstructor
public class UserAuthController {

    private final AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new Login());
        return "/login/index";
    }

    // Se encarga de autenticar el usuario
    @PostMapping("/authenticate")
    public String authenticate(@ModelAttribute("loginForm") Login login, Model model) {

        try {
            /// se autentica el usuario con el nombre de usuario y contraseña ingresados en
            /// el formulario y en el securityConfig, el userService consulta a la base de
            /// datos para verificar si el usuario existe 
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

            if (authentication.isAuthenticated()) {
                // Si la autenticacion es correcta se redirige a la pagina principal
                return "redirect:/home";
            }else{
                model.addAttribute("error", "Nombre de usuario o contraseña incorrectos");
            }

        } catch (AuthenticationException e) {
            // Si la autenticacion es incorrecta se muestra un mensaje de error y se
            // redirige a la pagina de login
            model.addAttribute("error", "Nombre de usuario o contraseña incorrectos");
            return "/login/index";
        }
        return "/login/index";
    }

    // Se encarga de cerrar la sesion
    @GetMapping("/logout")
    public String logout() {
        
        return "redirect:/userAuth/login?logout";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "filters/access-denied";
    }
}
