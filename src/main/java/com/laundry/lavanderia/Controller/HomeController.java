package com.laundry.lavanderia.Controller;

 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.RequestMapping; 

@Controller
@RequestMapping("/") //nombre del mapping
public class HomeController {

    @GetMapping("/")
    public String getLayout(Model model) {

        model.addAttribute("content", "home/index.html");
        return "shared/layout";
    } 

    @GetMapping("/home-page")
    public String getHome(Model model) {
        model.addAttribute("content", "home/index.html");
        return "shared/layout";
    }

    @GetMapping("/deliveries-page")
    public String getDeliveryPage(Model model) {
 
        model.addAttribute("content", "deliveries/index.html");
        return "shared/layout";
    }
    //agregue este nuevo controlador --Guillermo
    @GetMapping({"/deliveries/pedidos-entregados"})
    public String getPedidosEntregadosPage(Model model) {
        // Aquí le indicamos al modelo la ubicación de la nueva página de "Pedidos Entregados"
        model.addAttribute("content", "deliveries/PedidosEntregados.html");
        return "shared/layout";
    }
    //..
    @GetMapping("/services-page")
    public String getServicesPage(Model model) {
 
        model.addAttribute("content", "services-laundry/index.html");
        return "shared/layout";
    }
   
}
