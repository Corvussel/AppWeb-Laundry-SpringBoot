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

    /**
     * Muestra la pantalla principal de clientes.
     * 
     * @param model El objeto que contiene los datos de la vista.
     * @return El nombre de la vista que se va a renderizar.
     */
    @GetMapping("/index")
    public String showClientsPage(Model model) {
        model.addAttribute("clientes", clientService.getAllClients());
        model.addAttribute("totalClientes", clientService.getTotalClients());
        model.addAttribute("clientesActivos", clientService.getActiveClients());
        model.addAttribute("cliente", new cliente());
        model.addAttribute("content", "clients/cliente.html");
        return SHARED_LAYOUT;
    }

    /**
     * Registra un nuevo cliente en la base de datos y redirige a la pantalla
     * principal de clientes.
     * 
     * @param newClient El nuevo cliente que se va a registrar.
     * @return Un redireccionamiento a la pantalla principal de clientes.
     */
    @PostMapping("/register")
    public String registerClient(@ModelAttribute cliente newClient) {
        clientService.registerClient(newClient);
        return "redirect:/client/index";
    }

    /**
     * Muestra la pantalla de edicin de un cliente.
     *
     * @param id    El id del cliente que se va a editar.
     * @param model El objeto que contiene los datos de la vista.
     * @return El nombre de la vista que se va a renderizar.
     */
    @GetMapping("/edit/{id}")
    public String editClient(@PathVariable Long id, Model model) {
        model.addAttribute("cliente", clientService.getClientById(id));
        model.addAttribute("content", "clients/edit-client.html");
        return SHARED_LAYOUT;
    }

    /**
     * Actualiza un cliente en la base de datos.
     * 
     * @param updatedClient El cliente actualizado con los nuevos datos.
     * @return Un redireccionamiento a la pantalla principal de clientes.
     */
    @PostMapping("/update")
    public String updateClient(@ModelAttribute cliente updatedClient) {
        clientService.updateClient(updatedClient);
        return "redirect:/client/index";
    }

    /**
     * Elimina un cliente de la base de datos por su Id y redirige a la pantalla
     * principal de clientes.
     * 
     * @param id El id del cliente que se va a eliminar.
     * @return Un redireccionamiento a la pantalla principal de clientes.
     */

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClientbyId(id);
        return "redirect:/client/index";
    }

}
