package com.laundry.lavanderia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sales")
public class SalesController {

    private static final String SHARED_LAYOUT = "shared/layout";
 
    // detalles ventas
    @GetMapping("/details")
    public String showDetailsPage(Model model) {
        model.addAttribute("content", "details-sales/index.html");
        return SHARED_LAYOUT;
    }
}
