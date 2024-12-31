package com.laundry.lavanderia.repository; 

import org.springframework.data.jpa.repository.JpaRepository;
import com.laundry.lavanderia.Model.serviceLaundry.Boleta;

public interface BoletaRepository extends JpaRepository<Boleta, Long> {
    Boleta findByNumeroBoleta(String numeroBoleta);
}

 