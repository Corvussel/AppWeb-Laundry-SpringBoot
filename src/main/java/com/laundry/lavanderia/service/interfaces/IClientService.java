package com.laundry.lavanderia.service.interfaces;

import java.util.List;

import com.laundry.lavanderia.Model.client.cliente;

public interface IClientService {

    List<cliente> getAllActiveClients();

    List<cliente> getAllClients();

    Long getTotalClients();

    Long getActiveClients();

    void registerClient(cliente newClient); 

    cliente getClientById(Long id);

    void deleteClientbyId(Long id); 
    
    void updateClient(cliente updatedClient);
}
