package com.laundry.lavanderia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class pruebasController {
    
    @GetMapping("/p")
    public String showRegisterClientPage(Model model) {
        return "shared/prueba";
    }
}
