package com.laundry.lavanderia.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.laundry.lavanderia.Model.client.cliente;
import com.laundry.lavanderia.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Obtiene una lista de todos los clientes
     * 
     * @return devuelve una lista de todos los clientes
     */
    public List<cliente> getAllClients() {
        return clientRepository.findAll();
    }

    /**
     * metodo que obtiene una lista de todos los clientes activos
     * 
     * @return devuelve una lista de todos los clientes activos
     */

    public List<cliente> getAllActiveClients() {
        return clientRepository.findByActivoTrue();
    }

    /**
     * Obtiene el total de clientes en la base de datos.
     * 
     * @return el total de clientes
     */
    public Long getTotalClients() {
        return clientRepository.count();
    }

    /**
     * Obtiene el total de clientes activos en la base de datos.
     * 
     * @return el total de clientes activos
     */
    public Long getActiveClients() {
        return clientRepository.findAll().stream().filter(c -> c.getActivo()).count();
    }

    /**
     * Registra un nuevo cliente en la base de datos.
     * 
     * @param newClient el nuevo cliente que se va a registrar
     */
    public void registerClient(cliente newClient) {
        clientRepository.save(newClient);
    }

    /**
     * Obtiene un cliente por su Id.
     * 
     * @param id el Id del cliente que se va a obtener
     * @return el cliente que se obtuvo o null si no se encontro
     */
    public cliente getClientById(Long id) {
        System.out.println("Prueba de simulacion: " + clientRepository.findById(id));
        return clientRepository.findById(id).orElse(null);
    }

    /**
     * Marca un cliente como inactivo en la base de datos.
     * 
     * @param id el Id del cliente que se va a marcar como inactivo
     */

    public void deleteClientbyId(Long id) {
        clientRepository.findById(id).ifPresent(cliente -> {
            cliente.setActivo(false);
            clientRepository.save(cliente);
        });
    }

    /**
     * Actualiza un cliente existente en la base de datos.
     * 
     * @param updatedClient el cliente actualizado que se va a guardar
     */

    public void updateClient(cliente updatedClient) {
        clientRepository.save(updatedClient);
    }
}
