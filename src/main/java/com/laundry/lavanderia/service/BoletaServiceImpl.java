package com.laundry.lavanderia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.laundry.lavanderia.Model.serviceLaundry.Boleta;
import com.laundry.lavanderia.repository.BoletaRepository;
import com.laundry.lavanderia.service.interfaces.IBoletaService;

@Service
public class BoletaServiceImpl implements IBoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    /**
     * guardar boleta en la base de datos 
     *
     * @param boleta la boleta que se va a guardar
     * @return la boleta guardada en la base de datos.
     */ 
    public Boleta saveBoleta(Boleta boleta) {
        return boletaRepository.save(boleta);
    }

    /**
     * buscar boleta por numero de boleta
     *
     * @param numeroBoleta el numero de boleta
     * @return la boleta encontrada
     */
    public Boleta findByNumeroBoleta(String numeroBoleta) {
        return boletaRepository.findByNumeroBoleta(numeroBoleta);
    }
}
