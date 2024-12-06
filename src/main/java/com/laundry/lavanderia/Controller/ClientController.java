package com.laundry.lavanderia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClientController {

    @GetMapping
    public String getClientesList(Model model) {
        // Aquí debes obtener la lista de clientes desde la base de datos
        // De momento, vamos a pasar una lista de prueba.
        // model.addAttribute("clientes", listaDeClientes);
        
        // Por ahora, pasamos la vista que contiene la tabla de clientes
        model.addAttribute("content", "Clients/cliente.html");
        return "shared/layout";  // El layout compartido con la misma estructura
    }
}



