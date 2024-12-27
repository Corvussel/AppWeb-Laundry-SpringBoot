package com.laundry.lavanderia.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.laundry.lavanderia.Model.client.cliente;

@Service
public class ServiceLaundryServices {
    
    private List<cliente> clientes = new ArrayList<>();

    public ServiceLaundryServices() {
        // Datos de ejemplo
        cliente cliente1 = new cliente();
        cliente1.setId(1L);
        cliente1.setNombre("Russel Flores Solano");
        cliente1.setTelefono("992-676-985");
        cliente1.setDireccion("Av. Los Jardines 123");
        cliente1.setActivo(true);
        cliente1.setFechaRegistro("2024-01-20");

        cliente cliente2 = new cliente();
        cliente2.setId(2L);
        cliente2.setNombre("Maria García");
        cliente2.setTelefono("987-654-321");
        cliente2.setDireccion("Jr. Las Palmeras 456");
        cliente2.setActivo(false);
        cliente2.setFechaRegistro("2024-01-21");

        clientes.add(cliente1);
        clientes.add(cliente2);
    }

    public List<cliente> getAllClients() {
        return clientes;
    }

    public Long getTotalClients() {
        return (long) clientes.size();
    }

    public Long getActiveClients() {
        return clientes.stream().filter(c -> c.getActivo()).count();
    }

    public void deleteClient(Long id) {
        clientes.removeIf(c -> c.getId().equals(id));
    }

    public void registerClient(cliente newClient) {
        // Asignar ID (simulado)
        Long newId = clientes.stream()
                .mapToLong(cliente::getId)
                .max()
                .orElse(0L) + 1L;
        newClient.setId(newId);
        newClient.setActivo(
            newClient.getActivo() == null ? false : true
        );
        clientes.add(newClient);
    }
}
