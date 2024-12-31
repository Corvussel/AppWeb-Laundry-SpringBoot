package com.laundry.lavanderia.Controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import com.laundry.lavanderia.Model.serviceLaundry.OrderService;
import com.laundry.lavanderia.Model.employee.Employee;
import com.laundry.lavanderia.service.ServiceMangmentServiceLaundry;
import com.laundry.lavanderia.Model.client.cliente;
import com.laundry.lavanderia.service.ClientService;
import com.laundry.lavanderia.service.OrdersService;
import jakarta.servlet.http.HttpSession;
import com.laundry.lavanderia.service.EmployeeService;

@Controller
@RequestMapping("/serviceLaundry")
public class LaundryServicesController {

    private static final String SHARED_LAYOUT = "shared/layout";

    @Autowired
    private ClientService clientService; // Inyectar el servicio de clientes

    @Autowired
    private ServiceMangmentServiceLaundry serviceMangmentServiceLaundry; // Inyectar el servicio de servicios

    @Autowired
    private OrdersService ordersService; // Inyectar el servicio de ordenes

    @Autowired
    private HttpSession httpSession; // Inyectar el objeto HttpSession

    @Autowired
    private EmployeeService employeeService; // Inyectar el servicio de empleados

    /**
     * Muestra la pantalla principal de servicios de lavanderia.
     * 
     * @param model El objeto que contiene los datos de la vista.
     * @return El nombre de la vista que se va a renderizar.
     */
    @GetMapping("index")
    public String showIndexPage(Model model) {
        model.addAttribute("content", "services-laundry/index.html");
        return SHARED_LAYOUT;
    }

    /**
     * Retorna una lista de todos los clientes activos.
     * 
     * @return una lista de clientes activos o un 404 si no hay clientes.
     */
    @GetMapping("/ListUsers")
    @ResponseBody
    public ResponseEntity<List<cliente>> getAllActiveClients() {
        List<cliente> clients = clientService.getAllActiveClients();
        if (clients == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clients);
    }

    /**
     * Muestra la pantalla parcial de seleccion de servicios de lavanderia.
     * 
     * @param model El objeto que contiene los datos de la vista.
     * @return El nombre de la vista que se va a renderizar.
     */
    @GetMapping("/selection")
    public String showServiceSelectionFragment(Model model) {
        model.addAttribute("categorias", serviceMangmentServiceLaundry.getAllCategorys());
        return "services-laundry/service-selection";
    }

    /**
     * Registra un servicio de lavanderia.
     * 
     * @param registro El objeto que contiene la informacion del servicio a
     *                 registrar.
     * @return Un mapa que contiene el Id unico de la boleta generada y la URL
     *         para ver la boleta generada.
     */
    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> registerService(@RequestBody OrderService registro) {
        try {

            // Obtener el empleado de la sesión
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            Employee employee = employeeService.getEmployeeByEmail(userDetails.getUsername());
            if (employee == null) {
                throw new RuntimeException("Empleado no encontrado en la sesión");
            }
            // Registrar el servicio en la base de datos y obtener el Id unico de la boleta
            String boletaId = ordersService.RegisterOrder(registro, employee);

            // Guardar el registro en sesión con un Id único
            httpSession.setAttribute("boleta_" + boletaId, registro);

            // devolver el Id unico de la boleta y la URL de la boleta generada en la
            // respuesta
            Map<String, Object> repuesta = new HashMap<>();
            repuesta.put("status", "success");
            repuesta.put("boletaId", boletaId);
            repuesta.put("boletaUrl", "/serviceLaundry/boleta/" + boletaId);

            return repuesta;
        } catch (Exception e) {
            // devolver un mensaje de error en la respuesta si ocurre un error
            Map<String, Object> error = new HashMap<>();
            error.put("status", "error");
            error.put("message", "Error: " + e.getMessage());
            return error;
        }
    }

    /**
     * Genera la boleta de un servicio de lavanderia
     * 
     * @param boletaId El Id unico de la boleta
     * @param model    El objeto que contiene los datos de la vista
     * @return El nombre de la vista que se va a renderizar, o un
     *         Server Error si ocurre un error al generar la boleta
     */
    @GetMapping("/boleta/{boletaId}")
    public String generarBoleta(@PathVariable String boletaId, Model model) {
        try {
            // Obtener el registro de la sesión con el Id unico de la boleta
            OrderService registro = (OrderService) httpSession
                    .getAttribute("boleta_" + boletaId);

            if (registro == null) {
                throw new RuntimeException("Boleta no encontrada o está expirada");
            }

            // Agregar datos al modelo
            model.addAttribute("numeroBoleta", boletaId);
            model.addAttribute("fecha", new java.util.Date());
            model.addAttribute("servicios", registro.getServicios());
            model.addAttribute("totalServicio", registro.getTotalServicio());
            model.addAttribute("descuento", registro.getDescuento());
            model.addAttribute("precioTotal", registro.getPrecioTotal());
            model.addAttribute("metodoPago", registro.getMetodoPago());

            // Limpiar la sesión después de usar
            httpSession.removeAttribute("boleta_" + boletaId);

            return "services-laundry/boleta";
        } catch (Exception e) {
            throw new RuntimeException("Error al generar la boleta: " + e.getMessage());
        }
    }

}
