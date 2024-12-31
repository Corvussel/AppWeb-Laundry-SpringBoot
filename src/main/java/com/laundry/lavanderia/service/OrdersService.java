package com.laundry.lavanderia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laundry.lavanderia.Model.employee.Employee;
import com.laundry.lavanderia.Model.serviceLaundry.Boleta;
import com.laundry.lavanderia.Model.serviceLaundry.OrderService;
import com.laundry.lavanderia.repository.OrdersRepository;


@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private BoletaService boletaService; 

    /**
     * Registra un servicio de lavanderia.
     * 
     * @param registro El objeto que contiene la informacion del servicio a
     *                 registrar.
     * @param employee El empleado que se registra en el orden del servicio.
     * @return El Id unico de la boleta generada.
     */
    public String RegisterOrder(OrderService registro, Employee employee) {

        
        Boleta boleta = new Boleta(generarNumeroBoleta()); // Crear una nueva boleta
        boletaService.saveBoleta(boleta); // Guardar la boleta en la base de datos

        registro.setBoleta(boleta);
        registro.setEmployee(employee);
 
        // se guarda el registro en la base de datos
        ordersRepository.save(registro);

        return boleta.getNumeroBoleta();
    }

    /**
     * Genera un numero unico para una boleta de lavanderia.
     * 
     * @return Un string que comienza con "B-" y continua con el timestamp actual
     *         en milisegundos.
     */
    private String generarNumeroBoleta() {
        return "B-" + System.currentTimeMillis();
    }
}
