package com.laundry.lavanderia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/deliveries")
public class DeliveryController {

    private static final String SHARED_LAYOUT = "shared/layout";

    // Entregas pendientes
    @GetMapping("/pending")
    public String showPendingDeliveriesPage(Model model) {
        model.addAttribute("content", "deliveries/pending-deliveries.html");
        return SHARED_LAYOUT;
    }

    // Entregas realizadas
    @GetMapping("/completed")
    public String showCompletedDeliveriesPage(Model model) {
        model.addAttribute("content", "deliveries/completed-deliveries.html");
        return SHARED_LAYOUT;
    }
}
