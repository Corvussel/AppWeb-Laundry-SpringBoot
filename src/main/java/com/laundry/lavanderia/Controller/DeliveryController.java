package com.laundry.lavanderia.Controller;

import com.laundry.lavanderia.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import com.laundry.lavanderia.Model.Deliveries.Delivery;

@Controller
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;
    private final static String SHARED_LAYOUT = "shared/layout";

    // Entregas pendientes
    @GetMapping("/pending")
    public String showPendingDeliveriesPage(Model model) {
        model.addAttribute("deliveries", deliveryService.getPendingDeliveries());
        model.addAttribute("content", "deliveries/pending-deliveries.html");
        return SHARED_LAYOUT;
    }

    // Obtener detalles de una entrega pendiente
    @GetMapping("/pending/details/{id}")
    @ResponseBody
    public ResponseEntity<Delivery> getDeliveryDetails(@PathVariable Long id) {
        Delivery delivery = deliveryService.getDeliveryPendingById(id);
        if (delivery == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(delivery);
    }

    // Entregas realizadas
    @GetMapping("/completed")
    public String showCompletedDeliveriesPage(Model model) {
        model.addAttribute("deliveries", deliveryService.getCompletedDeliveries());
        model.addAttribute("content", "deliveries/completed-deliveries.html");
        return SHARED_LAYOUT;
    }

    // Obtener detalles de una entrega realizada
    @GetMapping("/completed/details/{id}")
    public String getDeliveryCompletedDetails(@PathVariable Long id, Model model) {
        model.addAttribute("delivery", deliveryService.getDeliveryCompletedById(id));
        model.addAttribute("content", "deliveries/delivery-details.html");
        return SHARED_LAYOUT;
    }

}
