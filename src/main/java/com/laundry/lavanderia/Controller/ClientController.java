package com.laundry.lavanderia.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.laundry.lavanderia.service.ClientService;
import com.laundry.lavanderia.Model.client.cliente;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;
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
        clientService.getClientById(id);
        model.addAttribute("content", "clients/edit-client.html");
        return SHARED_LAYOUT;
    }

    @PostMapping("/update/")
    public String updateClient(@ModelAttribute cliente updatedClient) {
        clientService.updateClient(updatedClient);
        return "redirect:/client/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClientbyId(id);
        return "redirect:/client/index";
    }

    @PostMapping("/register")
    public String registerClient(@ModelAttribute cliente newClient) {
        clientService.registerClient(newClient);
        return "redirect:/client/index";
    }
     

}
