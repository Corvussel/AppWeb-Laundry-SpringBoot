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

    public List<cliente> getAllClients() {
        return clientRepository.findAll();
    }

    public List<cliente> getAllActiveClients() {
        return clientRepository.findByActivoTrue();
    }

    public Long getTotalClients() {
        return clientRepository.count();
    }

    public Long getActiveClients() {
        return clientRepository.findAll().stream().filter(c -> c.getActivo()).count();
    }

    public void registerClient(cliente newClient) {
        clientRepository.save(newClient);
    }

    public cliente getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public void deleteClientbyId(Long id) {
        clientRepository.deleteById(id);
    }

    public void updateClient(cliente updatedClient) {
        clientRepository.save(updatedClient);
    }
}
