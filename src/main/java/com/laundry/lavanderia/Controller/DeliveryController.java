package com.laundry.lavanderia.Controller;

import com.laundry.lavanderia.service.DeliveryService;
import com.laundry.lavanderia.Model.serviceLaundry.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;
    private final static String SHARED_LAYOUT = "shared/layout";

    /**
     * Muestra la pagina de Entregas Pendientes
     * 
     * @param model objeto que almacena atributos para la vista
     * @return el nombre de la plantilla que se va a renderizar
     */
    @GetMapping("/pending")
    public String showPendingDeliveriesPage(Model model) {
        model.addAttribute("orders", deliveryService.getAllPendingOrders());
        model.addAttribute("content", "deliveries/pending-deliveries.html");
        return SHARED_LAYOUT;
    }

    /**
     * Displays the completed deliveries page.
     * 
     * @param model a Model object to store attributes for the view
     * @return the name of the template to be rendered
     */

    @GetMapping("/completed")
    public String showCompletedDeliveriesPage(Model model) {
        model.addAttribute("deliveries", deliveryService.getAllCompletedOrders());
        model.addAttribute("content", "deliveries/completed-deliveries.html");
        return SHARED_LAYOUT;
    }

    /**
     * Obtiene los detalles de una orden pendiente
     * 
     * @param id el id de la orden
     * @return el objeto OrderService si existe, o un 404 si no existe
     */

    @GetMapping("/pending/details/{id}")
    @ResponseBody
    public ResponseEntity<OrderService> getOrderPendingDetails(@PathVariable Long id) {
        OrderService order = deliveryService.getOrderPendingById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    /**
     * Obtiene los detalles de una orden completada
     * 
     * @param id el id de la orden
     * @return el objeto OrderService si existe, o un 404 si no existe
     */
    @GetMapping("/completed/details/{id}")
    @ResponseBody
    public ResponseEntity<OrderService> getOrderCompletedDetails(@PathVariable Long id) {
        OrderService order = deliveryService.getOrderCompletedById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

}
