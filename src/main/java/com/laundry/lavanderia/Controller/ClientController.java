package com.laundry.lavanderia.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.laundry.lavanderia.service.ServiceLaundryServices;
import com.laundry.lavanderia.Model.client.cliente;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ServiceLaundryServices clientService;
    private static final String SHARED_LAYOUT = "shared/layout";

    @GetMapping("/index")
    public String showClientsPage(Model model) {
        model.addAttribute("clientes", clientService.getAllClients());
        model.addAttribute("totalClientes", clientService.getTotalClients());
        model.addAttribute("clientesActivos", clientService.getActiveClients());
        model.addAttribute("content", "clients/cliente.html");
        return SHARED_LAYOUT;
    }
  
    @GetMapping("/edit/{id}")
    public String editClient(@PathVariable Long id, Model model) {
        System.out.println("Editando cliente con ID: " + id);
        model.addAttribute("content", "clients/edit-client.html");
        return SHARED_LAYOUT;
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        System.out.println("Eliminando cliente con ID: " + id);
        clientService.deleteClient(id);
        return "redirect:/client/index";
    }

    @PostMapping("/register")
    public String registerClient(@ModelAttribute cliente newClient) {
       
        newClient.setFechaRegistro(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        clientService.registerClient(newClient);
        return "redirect:/client/index";
    }

}
