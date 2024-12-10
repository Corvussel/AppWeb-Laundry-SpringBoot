package com.laundry.lavanderia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
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

    @GetMapping({ "/order-delivery-page" })
    public String getPedidosEntregadosPage(Model model) {
        
        model.addAttribute("content", "deliveries/PedidosEntregados.html");
        return "shared/layout";
    }

    @GetMapping("/client-page")
    public String getClientPage(Model model) {
        model.addAttribute("content", "clients/cliente.html");
        return "shared/layout";
    }

}
