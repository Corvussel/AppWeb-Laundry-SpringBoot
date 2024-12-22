package com.laundry.lavanderia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientController {

    private static final String SHARED_LAYOUT = "shared/layout";
     // clientes
     @GetMapping("/index")
     public String showClientsPage(Model model) {
         model.addAttribute("content", "clients/cliente.html");
         return SHARED_LAYOUT;
     }
    @GetMapping("/register")
    public String showRegisterClientPage(Model model) {
        model.addAttribute("content", "clients/register-client.html");
        return "shared/layout";
    } 
}
