package com.laundry.lavanderia.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laundry.lavanderia.Model.employee.Employee;
import com.laundry.lavanderia.Model.payment.PaymentMethod;
import com.laundry.lavanderia.Model.serviceLaundry.Boleta;
import com.laundry.lavanderia.Model.serviceLaundry.OrderService;
import com.laundry.lavanderia.Model.serviceLaundry.OrderDetails;
import com.laundry.lavanderia.repository.OrdersRepository;
import com.laundry.lavanderia.repository.PaymentMethodRepository;
import com.laundry.lavanderia.service.interfaces.IOrdersService;
import com.laundry.lavanderia.repository.OrderDetailsRepository;

@Service
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private BoletaService boletaService;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    /**
     * Registra un servicio de lavanderia.
     * 
     * @param registro El objeto que contiene la informacion del servicio a
     *                 registrar.
     * @param employee El empleado que se registra en el orden del servicio.
     * @return El Id unico de la boleta generada.
     */
    @Override
    public String RegisterOrder(OrderService registro, Employee employee) {

        Boleta boleta = new Boleta(generarNumeroBoleta()); // Crear una nueva boleta
        boletaService.saveBoleta(boleta); // Guardar la boleta en la b ase de datos

        // Buscar el metodo de pago en la base de datos
        PaymentMethod paymentMethod = paymentMethodRepository.findByName(registro.getPaymentMethod().getName());

        registro.setPaymentMethod(paymentMethod); // Asignar el metodo de pago
        registro.setBoleta(boleta); // Asignar la boleta
        registro.setEmployee(employee); // Asignar el empleado
        registro.setStatus("Pendiente"); // Asignar el estado del servicio
        registro.setFecha(new SimpleDateFormat("yyyy-MM-dd").format(new Date())); // Asignar la fecha del servicio

        // se guarda el registro en la base de datos
        OrderService savedOrder = ordersRepository.save(registro);

        // se guardan los detalles del servicio en la base de datos
        for (OrderDetails details : registro.getServicios()) {
            details.setOrderService(savedOrder);
            orderDetailsRepository.save(details);
        }
        // se retorna el numero de la boleta
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
