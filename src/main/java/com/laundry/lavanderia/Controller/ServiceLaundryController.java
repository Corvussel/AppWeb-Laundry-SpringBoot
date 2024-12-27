package com.laundry.lavanderia.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.laundry.lavanderia.Model.serviceLaundry.Categoria;
import com.laundry.lavanderia.Model.serviceLaundry.RegistroServicioLavanderia;
import com.laundry.lavanderia.Model.serviceLaundry.Servicio;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/serviceLaundry")
public class ServiceLaundryController {

    private static final String SHARED_LAYOUT = "shared/layout";
    private List<Categoria> categorias = new ArrayList<>();
    private List<Servicio> servicios = new ArrayList<>();

    @Autowired
    private HttpSession httpSession;

    public ServiceLaundryController() {
        // Datos iniciales
        List<Servicio> serviciosIniciales = new ArrayList<>();
        serviciosIniciales.add(new Servicio("Lavado Simple", "Lavado básico", 15.0, true));
        serviciosIniciales.add(new Servicio("Planchado Rápido", "Planchado sencillo", 20.0, true));

        // Asegúrate de proporcionar un valor para la descripción al crear la categoría
        categorias .add(new Categoria("Ropa Normal", serviciosIniciales, true, "Descripción de la categoría Ropa Normal"));
    }

    // ** Vistas principales **

    @GetMapping("/index")
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

    // ** Gestión de categorías **

    @GetMapping("/categories")
    public String showCategories(Model model) {
        model.addAttribute("categorias", categorias);
        return "services-management/edit-category";
    }

    @PostMapping("/categories/add")
    @ResponseBody
    public Map<String, Object> addCategory(@RequestParam String nombre, @RequestParam String descripcion) {
        System.out.println("Nombre: " + nombre + ", Descripción: " + descripcion);
        Categoria nuevaCategoria = new Categoria(nombre, new ArrayList<>(), true, descripcion);
        categorias.add(nuevaCategoria);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("status", "success");
        respuesta.put("categoria", nuevaCategoria);
        return respuesta;
    }
    

    @PostMapping("/categories/edit")
    @ResponseBody
    public Map<String, Object> editCategory(@RequestParam String nombreOriginal, @RequestParam String nuevoNombre,
            @RequestParam String descripcion, @RequestParam boolean activo) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equals(nombreOriginal)) {
                categoria.setNombre(nuevoNombre);
                categoria.setDescripcion(descripcion);
                categoria.setActivo(activo);
            }
        }

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("status", "success");
        return respuesta;
    }

    @PostMapping("/categories/update")
    public String updateCategory(@RequestParam String nombre, @RequestParam boolean activo) {
        categorias.forEach(categoria -> {
            if (categoria.getNombre().equals(nombre)) {
                categoria.setActivo(activo);
            }
        });
        return "redirect:/serviceLaundry/categories";
    }
    

    @PostMapping("/categories/delete")
    public String deleteCategory(@RequestParam String nombre) {
        categorias.forEach(categoria -> {
            if (categoria.getNombre().equals(nombre)) {
                categoria.setActivo(false);
            }
        });
        return "redirect:/serviceLaundry/categories";
    }

    @PostMapping("/categories/deactivate")
    @ResponseBody
    public Map<String, Object> deactivateCategory(@RequestParam String nombre) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equals(nombre)) {
                categoria.setActivo(false);
            }
        }

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("status", "success");
        return respuesta;
    }

    // ** Gestión de servicios **

    @GetMapping("/services")
    public String showServices(Model model) {
        model.addAttribute("servicios", servicios);
        return "services-management/edit-service.html";
    }

    @PostMapping("/services/add")
    public ResponseEntity<?> addService(@RequestParam String nombre, @RequestParam String detalle,
            @RequestParam double precio, @RequestParam String categoriaNombre) {
        Optional<Categoria> categoriaOptional = categorias.stream()
                .filter(c -> c.getNombre().equals(categoriaNombre) && c.isActivo())
                .findFirst();

        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            Servicio nuevoServicio = new Servicio(nombre, detalle, precio, true);
            categoria.getServicios().add(nuevoServicio);
            servicios.add(nuevoServicio);
            return ResponseEntity.ok(Map.of("status", "success", "servicio", nuevoServicio));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("status", "error", "message", "Categoría no encontrada o inactiva"));
        }
    }

    @PostMapping("/services/delete")
    public String deleteService(@RequestParam String nombre) {
        servicios.forEach(servicio -> {
            if (servicio.getNombre().equals(nombre)) {
                servicio.setActivo(false);
            }
        });
        return "redirect:/serviceLaundry/services";
    }

    // ** Registro y boleta **

    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> registerService(@RequestBody RegistroServicioLavanderia registro) {
        try {
            String boletaId = generarNumeroBoleta();
            httpSession.setAttribute("boleta_" + boletaId, registro);

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("status", "success");
            respuesta.put("boletaId", boletaId);
            respuesta.put("boletaUrl", "/serviceLaundry/boleta/" + boletaId);

            return respuesta;
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
            RegistroServicioLavanderia registro = (RegistroServicioLavanderia) httpSession
                    .getAttribute("boleta_" + boletaId);

            if (registro == null) {
                throw new RuntimeException("Boleta no encontrada o está expirada");
            }

            model.addAttribute("numeroBoleta", boletaId);
            model.addAttribute("fecha", new java.util.Date());
            model.addAttribute("nombreCliente", registro.getNombreCliente());
            model.addAttribute("servicios", registro.getServicios());
            model.addAttribute("totalServicio", registro.getTotalServicio());
            model.addAttribute("descuento", registro.getDescuento());
            model.addAttribute("precioTotal", registro.getPrecioTotal());
            model.addAttribute("metodoPago", registro.getMetodoPago());

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
