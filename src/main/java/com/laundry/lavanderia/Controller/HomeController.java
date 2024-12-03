package com.laundry.lavanderia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping; 

@Controller
public class HomeController {

    @RequestMapping("/")
    public String getLayout(Model model) {

        model.addAttribute("content", "Home/index.html");
        return "Shared/_Layout";
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("content", "Home/index.html");
        return "Shared/_Layout";
    }

    @GetMapping("/DeliveryLaundry")
    public String getDeliveryLaundry(Model model) {
 
        model.addAttribute("content", "DeliveryLaundry/index.html");
        return "Shared/_Layout";
    }
 

}
