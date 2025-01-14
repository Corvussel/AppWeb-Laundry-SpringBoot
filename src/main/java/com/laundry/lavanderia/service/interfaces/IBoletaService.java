package com.laundry.lavanderia.service.interfaces;

import com.laundry.lavanderia.Model.serviceLaundry.Boleta;

public interface IBoletaService {

    Boleta saveBoleta(Boleta boleta);

    Boleta findByNumeroBoleta(String numeroBoleta);
}
