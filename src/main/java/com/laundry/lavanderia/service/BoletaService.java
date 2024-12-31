package com.laundry.lavanderia.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.laundry.lavanderia.Model.serviceLaundry.Boleta;
import com.laundry.lavanderia.repository.BoletaRepository;

@Service
public class BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    public Boleta saveBoleta(Boleta boleta) {
        return boletaRepository.save(boleta);
    }

    public Boleta findByNumeroBoleta(String numeroBoleta) {
        return boletaRepository.findByNumeroBoleta(numeroBoleta);
    }
}

 
