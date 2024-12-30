package com.laundry.lavanderia.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laundry.lavanderia.Model.serviceMangment.Category;
import com.laundry.lavanderia.Model.serviceMangment.ServiceLaundry;
import com.laundry.lavanderia.service.ServiceMangmentServiceLaundry;

@Controller
@RequestMapping("/management")
public class ServiceLaundryManagementController {

    @Autowired
    private ServiceMangmentServiceLaundry serviceMangmentServiceLaundry;
    private static final String SHARED_LAYOUT = "shared/layout";

    // Pagina principal de servicios-categorias
    @GetMapping("/services-laundry")
    public String showMainServicesPage(Model model) {
        model.addAttribute("categories", serviceMangmentServiceLaundry.getAllCategorys());
        model.addAttribute("services", serviceMangmentServiceLaundry.getAllServices());
        model.addAttribute("category", new Category());
        model.addAttribute("service", new ServiceLaundry());
        model.addAttribute("content", "services-management/index.html");
        return SHARED_LAYOUT;
    }

    // Registrar nueva categoría
    @PostMapping("/register-category")
    public String registerCategory(@ModelAttribute Category newCategory) {
        serviceMangmentServiceLaundry.saveCategory(newCategory);
        return "redirect:/management/services-laundry";
    }

    // Registrar nuevo servicio
    @PostMapping("/register-service")
    public String registerService(@ModelAttribute ServiceLaundry newService) {
        serviceMangmentServiceLaundry.saveService(newService);
        return "redirect:/management/services-laundry";
    }

    // Pagina de edicion de categoría
    @GetMapping("/categories/edit/{id}")
    public String showCategoryPage(@PathVariable Long id, Model model) {
        model.addAttribute("category", serviceMangmentServiceLaundry.getCategoryById(id));
        model.addAttribute("content", "services-management/edit-category.html");
        return SHARED_LAYOUT;
    }

    // Pagina de edicion de servicio
    @GetMapping("/services/edit/{id}")
    public String showServicePage(@PathVariable Long id, Model model) {
        model.addAttribute("service", serviceMangmentServiceLaundry.getServiceById(id));
        model.addAttribute("categories", serviceMangmentServiceLaundry.getAllCategorys());
        model.addAttribute("content", "services-management/edit-service.html");
        return SHARED_LAYOUT;
    }

    // Guardar cambios de categoría
    @PostMapping("/save-category")
    public String saveCategory(@ModelAttribute Category updatedCategory) {
        serviceMangmentServiceLaundry.updateCategory(updatedCategory);
        return "redirect:/management/services-laundry";
    }

    // Guardar cambios de servicio
    @PostMapping("/save-service")
    public String saveService(@ModelAttribute ServiceLaundry updatedService) {
        serviceMangmentServiceLaundry.updateService(updatedService);
        return "redirect:/management/services-laundry";
    }

    // Eliminar categoría
    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        serviceMangmentServiceLaundry.deleteCategory(id);
        return "redirect:/management/services-laundry";
    }

    // Eliminar servicio
    @GetMapping("/services/delete/{id}")
    public String deleteService(@PathVariable Long id) {
        serviceMangmentServiceLaundry.deleteService(id);
        return "redirect:/management/services-laundry";
    }
}