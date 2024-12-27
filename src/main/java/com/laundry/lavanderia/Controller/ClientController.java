package com.laundry.lavanderia.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.laundry.lavanderia.Model.serviceLaundry.Cliente;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/client")
public class ClientController {

     private static final String SHARED_LAYOUT = "shared/layout";
    private  final List<Cliente> clientes = new ArrayList<>();
    private Long currentId = 1L;

    // Vista principal de clientes
    @GetMapping("/index")
    public String showClientsPage(Model model, HttpSession session) {
        model.addAttribute("content", "clients/cliente.html");
        model.addAttribute("clientes", clientes);
        model.addAttribute("totalClientes", clientes.size());
        model.addAttribute("clientesActivos", 
            clientes.stream().filter(Cliente::isActivo).count());
        return SHARED_LAYOUT;
    }

    // Vista del formulario de registro
    @GetMapping("/register")
    public String showRegisterClientPage(Model model) {
        model.addAttribute("content", "clients/register-client.html");
        return SHARED_LAYOUT;
    }

    // Crear nuevo cliente
    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> createClient(@RequestBody Cliente cliente) {
        Map<String, Object> response = new HashMap<>();
        try {
            cliente.setId(currentId++);
            cliente.setActivo(true);
            cliente.setCodigoCliente(generateClientCode());
            clientes.add(cliente);
            
            response.put("success", true);
            response.put("message", "Cliente registrado exitosamente");
            response.put("cliente", cliente);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al registrar cliente: " + e.getMessage());
        }
        return response;
    }

    // Obtener cliente para edición
    @GetMapping("/get/{id}")
    @ResponseBody
    public Map<String, Object> getClient(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Cliente cliente = findClienteById(id);
        
        if (cliente != null) {
            response.put("success", true);
            response.put("cliente", cliente);
        } else {
            response.put("success", false);
            response.put("message", "Cliente no encontrado");
        }
        return response;
    }

    // Actualizar cliente
    @PostMapping("/update/{id}")
    @ResponseBody
    public Map<String, Object> updateClient(@PathVariable Long id, @RequestBody Cliente cliente) {
        Map<String, Object> response = new HashMap<>();
        try {
            int index = findClienteIndex(id);
            if (index != -1) {
                cliente.setId(id);
                clientes.set(index, cliente);
                response.put("success", true);
                response.put("message", "Cliente actualizado exitosamente");
                response.put("cliente", cliente);
            } else {
                response.put("success", false);
                response.put("message", "Cliente no encontrado");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al actualizar cliente: " + e.getMessage());
        }
        return response;
    }

    // Eliminar cliente
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String, Object> deleteClient(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean removed = clientes.removeIf(c -> c.getId().equals(id));
            if (removed) {
                response.put("success", true);
                response.put("message", "Cliente eliminado exitosamente");
            } else {
                response.put("success", false);
                response.put("message", "Cliente no encontrado");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al eliminar cliente: " + e.getMessage());
        }
        return response;
    }

    // Cambiar estado del cliente
    @PostMapping("/toggle-status/{id}")
    @ResponseBody
    public Map<String, Object> toggleClientStatus(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Cliente cliente = findClienteById(id);
        
        if (cliente != null) {
            cliente.setActivo(!cliente.isActivo());
            response.put("success", true);
            response.put("message", "Estado actualizado exitosamente");
            response.put("activo", cliente.isActivo());
        } else {
            response.put("success", false);
            response.put("message", "Cliente no encontrado");
        }
        return response;
    }

    // Buscar clientes
    @GetMapping("/search")
    @ResponseBody
    public List<Cliente> searchClients(@RequestParam String query) {
        String searchQuery = query.toLowerCase();
        return clientes.stream()
            .filter(c -> c.getNombre().toLowerCase().contains(searchQuery) || 
                        c.getTelefono().contains(searchQuery))
            .toList();
    }

    // Métodos auxiliares
    private Cliente findClienteById(Long id) {
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private int findClienteIndex(Long id) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private String generateClientCode() {
        return "CLI-" + String.format("%04d", currentId);
    }
}
