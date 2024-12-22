package com.laundry.lavanderia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private static final String SHARED_LAYOUT = "shared/layout";

    // menu principal por defecto
    @GetMapping
    public String showHomePage(Model model) {
        model.addAttribute("content", "home/index.html");
        return SHARED_LAYOUT;
    }
    // menu principal
    @GetMapping("/home")
    public String showHomePageAlternate(Model model) {
        model.addAttribute("content", "home/index.html");
        return SHARED_LAYOUT;
    }
    
}
