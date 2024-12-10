package com.laundry.lavanderia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientController {

    @GetMapping("/register-client")
    public String getRegisterClient(Model model) {
        model.addAttribute("content", "clients/register-client.html");
        return "shared/layout";
    }
 
}
