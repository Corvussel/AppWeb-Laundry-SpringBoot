package com.laundry.lavanderia.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.laundry.lavanderia.Model.serviceLaundry.Categoria;
import com.laundry.lavanderia.Model.serviceLaundry.RegistroServicioLavanderia;
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
                new Servicio("Lavado", "Descripción del Servicio 1", 10),
                new Servicio("Servicio 2", "Descripción del Servicio 2", 120));

        List<Servicio> servicios2 = Arrays.asList(
                new Servicio("Servicio A", "Descripción del Servicio A", 150),
                new Servicio("Servicio B", "Descripción del Servicio B", 220));

        List<Categoria> categorias = Arrays.asList(
                new Categoria("Planchado", servicios1),
                new Categoria("Lavado", servicios2));

        model.addAttribute("categorias", categorias);

        return "services-laundry/service-selection";
    }

    @PostMapping("/register")
    @ResponseBody
    public String registerService(@RequestBody RegistroServicioLavanderia registro){
        try {
            // Procesar el registro completo
            System.out.println("Tipo de Servicio: " + registro.getTipoServicio());
            System.out.println("Cliente: " + registro.getNombreCliente());
            System.out.println("Método de Pago: " + registro.getMetodoPago());
            
            // Procesar servicios
            for (Servicio servicio : registro.getServicios()) {
                System.out.println("Servicio: " + servicio.getNombre());
                System.out.println("Precio: " + servicio.getPrecioUnidad());
                System.out.println("Cantidad: " + servicio.getCantidad());
                System.out.println("Subtotal: " + servicio.getSubTotal());
                System.out.println("Detalle: " + servicio.getDetalle());
            }

            System.out.println("Total Servicio: " + registro.getTotalServicio());
            System.out.println("Descuento: " + registro.getDescuento());
            System.out.println("Total a Cobrar: " + registro.getTotalCobrar());
            
            return "{\"message\":\"Registro de servicio procesado correctamente\"}";
        } catch (Exception e) {
            return "{\"error\":\"Error al procesar el registro: " + e.getMessage() + "\"}";
        }
    }
}
