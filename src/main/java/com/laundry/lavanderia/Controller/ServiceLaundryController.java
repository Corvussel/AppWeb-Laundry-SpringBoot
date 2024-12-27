package com.laundry.lavanderia.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import com.laundry.lavanderia.Model.serviceLaundry.Categoria;
import com.laundry.lavanderia.Model.serviceLaundry.RegistroServicioLavanderia;
import com.laundry.lavanderia.Model.serviceLaundry.Servicio;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/serviceLaundry")
public class ServiceLaundryController {

    private static final String SHARED_LAYOUT = "shared/layout";

    @Autowired
    private HttpSession httpSession;

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
    public Map<String, Object> registerService(@RequestBody RegistroServicioLavanderia registro) {
        try {
            // Guardar el registro en sesion con un Id unico
            String boletaId = generarNumeroBoleta();
            httpSession.setAttribute("boleta_" + boletaId, registro);

            Map<String, Object> repuesta = new HashMap<>();
            repuesta.put("status", "success");
            repuesta.put("boletaId", boletaId);
            repuesta.put("boletaUrl", "/serviceLaundry/boleta/" + boletaId);

            return repuesta;
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("status", "error");
            error.put("message", "Error: " + e.getMessage());
            return error;
        }
    }

    @GetMapping("/boleta/{boletaId}")
    public String generarBoleta(@PathVariable String boletaId, Model model) {
        try {
            // Recuperar datos de la sesion
            RegistroServicioLavanderia registro = (RegistroServicioLavanderia) httpSession
                    .getAttribute("boleta_" + boletaId);

            if (registro == null) {
                throw new RuntimeException("boletaa no encontrada o esta expirada");
            }

            // agregar datos al modelo
            model.addAttribute("numeroBoleta", boletaId);
            model.addAttribute("fecha", new java.util.Date());
            model.addAttribute("nombreCliente", registro.getNombreCliente());
            model.addAttribute("servicios", registro.getServicios());
            model.addAttribute("totalServicio", registro.getTotalServicio());
            model.addAttribute("descuento", registro.getDescuento());
            model.addAttribute("precioTotal", registro.getPrecioTotal());
            model.addAttribute("metodoPago", registro.getMetodoPago());

            // se Limpiar la sesion despuess de usar
            httpSession.removeAttribute("boleta_" + boletaId);

            return "services-laundry/boleta";
        } catch (Exception e) {
            throw new RuntimeException("Error al generar la boleta: " + e.getMessage());
        }
    }

    private String generarNumeroBoleta() {
        return "B-" + System.currentTimeMillis();
    }
}
