package com.laundry.lavanderia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laundry.lavanderia.Model.Login.login;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/userAuth")
@RequiredArgsConstructor
public class UserAuthController {

    // Redireccion para Login
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("login", new login());
        return "login/index";
    }

    // Con autenticacion
    @PostMapping("/authenticate")
    public String authenticate(@ModelAttribute("login") login login, Model model) {
        // Simulación de validación
        if ("admin".equals(login.getUsername()) && "123".equals(login.getPassword())) {
            model.addAttribute("message", "Inicio de sesión exitoso");
            model.addAttribute("content", "home/index.html");
        } else {
            model.addAttribute("message", "Usuario o contraseña incorrectos");
            model.addAttribute("content", "login/index.html");
        }
        return "authenticate";
    }
 
}
