package com.laundry.lavanderia.Controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laundry.lavanderia.Model.serviceLaundry.OrderDetails;
import com.laundry.lavanderia.Model.serviceLaundry.OrderService;
import com.laundry.lavanderia.service.SalesService;

@Controller
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;
    private static final String SHARED_LAYOUT = "shared/layout";

    /**
     * Muestra la pantalla de detalles de las ventas en la lavanderia.
     *
     * @param model El objeto que contiene los datos de la vista.
     * @return El nombre de la vista que se va a renderizar.
     * @throws JsonProcessingException Si ocurre un error al convertir los datos
     * en formato JSON.
     */
    @GetMapping("/details")
    public String showDetailsPage(Model model) throws JsonProcessingException {
        List<OrderService> sales = salesService.getAllSales();
        model.addAttribute("sales", sales);

        // grafico de ventas por tipo de servicio
        Map<String, Long> serviceCount = sales.stream()
                .flatMap(sale -> sale.getServicios().stream())
                .collect(Collectors.groupingBy(OrderDetails::getNombre, Collectors.counting()));

        List<String> chartLabels = serviceCount.keySet().stream().collect(Collectors.toList());
        List<Long> chartValues = serviceCount.values().stream().collect(Collectors.toList());

       // se convierten los datos a formato JSON para que puedan ser leidos por el grafico
        ObjectMapper objectMapper = new ObjectMapper();
        model.addAttribute("chartLabels", objectMapper.writeValueAsString(chartLabels));
        model.addAttribute("chartValues", objectMapper.writeValueAsString(chartValues));

        // informacion general de las ventas en la lavanderia 
        model.addAttribute("totalPrendasLavadas", sales.stream().mapToLong(OrderService::getId).sum());
        model.addAttribute("totalEntregado", sales.stream().filter(sale -> "Entregado".equals(sale.getStatus())).count());
        model.addAttribute("totalPendientes", sales.stream().filter(sale -> "Pendiente".equals(sale.getStatus())).count());
        model.addAttribute("totalClientesAtendidos", sales.stream().map(OrderService::getClient).distinct().count());

        model.addAttribute("content", "details-sales/index.html");
        return SHARED_LAYOUT;
    }

    /**
     * Devuelve el detalle de una venta por su id.
     *
     * @param id el id de la venta
     * @return el objeto OrderService si existe, o un 404 si no existe
     */
    @GetMapping("/details/{id}")
    @ResponseBody
    public ResponseEntity<OrderService> getSaleDetail(@PathVariable Long id) {
        OrderService detail = salesService.getSaleDetailById(id);
        if (detail == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detail);
    }
}
