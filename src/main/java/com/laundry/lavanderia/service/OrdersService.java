package com.laundry.lavanderia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laundry.lavanderia.Model.employee.Employee;
import com.laundry.lavanderia.Model.serviceLaundry.Boleta;
import com.laundry.lavanderia.Model.serviceLaundry.OrderService;
import com.laundry.lavanderia.Model.serviceLaundry.OrderDetails;
import com.laundry.lavanderia.repository.OrdersRepository;
import com.laundry.lavanderia.repository.OrderDetailsRepository;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private BoletaService boletaService;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

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
        registro.setStatus("Pendiente"); 
       
        // se guarda el registro en la base de datos
        OrderService savedOrder = ordersRepository.save(registro);

        // Save each OrderDetails associated with the saved OrderService
        for (OrderDetails details : registro.getServicios()){
            details.setOrderService(savedOrder);
            orderDetailsRepository.save(details);
        }

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
