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

    // entregas
    @GetMapping("/deliveries")
    public String showDeliveriesPage(Model model) {
        model.addAttribute("content", "deliveries/order-delivery.html");
        return SHARED_LAYOUT;
    }

    // registro de servicio
    @GetMapping("/services")
    public String showServicesPage(Model model) {
        model.addAttribute("content", "services-laundry/index.html");
        return SHARED_LAYOUT;
    }

    // pedidos entregados
    @GetMapping("/orders/delivered")
    public String showDeliveredOrdersPage(Model model) {
        model.addAttribute("content", "deliveries/orders-delivered.html");
        return SHARED_LAYOUT;
    }

    // clientes
    @GetMapping("/clients")
    public String showClientsPage(Model model) {
        model.addAttribute("content", "clients/cliente.html");
        return SHARED_LAYOUT;
    }

    // gastos
    @GetMapping("/spending")
    public String showExpenseControlPage(Model model) {
        model.addAttribute("content", "spending/expense-control.html");
        return SHARED_LAYOUT;
    }

    // detalles ventas
    @GetMapping("/detailSales")
    public String showDetailsPage(Model model) {
        model.addAttribute("content", "details-sales/index.html");
        return SHARED_LAYOUT;
    }
}
