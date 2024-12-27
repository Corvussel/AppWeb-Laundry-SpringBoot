package com.laundry.lavanderia.Controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management")
public class ServiceManagementController {

    private static final String SHARED_LAYOUT = "shared/layout";

    // Pagina principal de servicios
    @GetMapping("/services-laundry")
    public String showMainServicesPage(Model model) {
        model.addAttribute("content", "services-management/index.html");
        return SHARED_LAYOUT;
    }

    // Pagina de edicion de categoría
    @GetMapping("/categories")
    public String showCategoriesPage(Model model) {
        model.addAttribute("content", "services-management/edit-category.html");
        return SHARED_LAYOUT;
    }

    // Pagina de edicion de servicio
    @GetMapping("/services")
    public String showServicesPage(Model model) {
        model.addAttribute("content", "services-management/edit-service.html");
        return SHARED_LAYOUT;
    }

    
}