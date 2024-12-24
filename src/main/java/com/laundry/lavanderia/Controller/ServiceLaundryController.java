package com.laundry.lavanderia.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laundry.lavanderia.Model.serviceLaundry.Categoria;
import com.laundry.lavanderia.Model.serviceLaundry.Servicio;

@Controller
@RequestMapping("/serviceLaundry")
public class ServiceLaundryController {

    private static final String SHARED_LAYOUT = "shared/layout";

    @GetMapping("index")
    public String showIndexPage(Model model) {
        model.addAttribute("content", "services-laundry/index.html");
        return SHARED_LAYOUT;
    }

    @GetMapping("/ListUsers")
    public String showUserListFragment() {
        return "services-laundry/user-list";
    }

    @GetMapping("/selection")
    public String showServiceSelectionFragment(Model model) {

        List<Servicio> servicios1 = Arrays.asList(
                new Servicio("Lavado", "Descripción del Servicio 1"),
                new Servicio("Servicio 2", "Descripción del Servicio 2"));

        List<Servicio> servicios2 = Arrays.asList(
                new Servicio("Servicio A", "Descripción del Servicio A"),
                new Servicio("Servicio B", "Descripción del Servicio B"));

        List<Categoria> categorias = Arrays.asList(
                new Categoria("a", servicios1),
                new Categoria("e", servicios2));

        model.addAttribute("categorias", categorias);

        return "services-laundry/service-selection";
    }
}
