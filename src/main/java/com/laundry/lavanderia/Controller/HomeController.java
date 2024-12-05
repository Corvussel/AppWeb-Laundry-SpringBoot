package com.laundry.lavanderia.Controller;

 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.laundry.lavanderia.Model.Login.login; 

@Controller
@RequestMapping("/") //nombre del mapping
public class HomeController {

    @GetMapping("/")
    public String getLayout(Model model) {

        model.addAttribute("content", "home/index.html");
        return "shared/layout";
    } 

    @GetMapping("/home-page")
    public String getHome(Model model) {
        model.addAttribute("content", "home/index.html");
        return "shared/layout";
    }

    @GetMapping("/deliveries-page")
    public String getDeliveryPage(Model model) {
 
        model.addAttribute("content", "deliveries/index.html");
        return "shared/layout";
    }
    
    @GetMapping("/services-page")
    public String getServicesPage(Model model) {
 
        model.addAttribute("content", "services-laundry/index.html");
        return "shared/layout";
    }
    // Estoy creando la nueva redireccion para Login - Caballero
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("login", new login()); // Pasamos un objeto vacío de Login
        model.addAttribute("content", "login/index.html");
        return "login";
    }
    // Con autenticacion - Caballero
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
