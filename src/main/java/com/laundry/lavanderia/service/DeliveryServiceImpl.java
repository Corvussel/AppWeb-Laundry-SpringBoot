package com.laundry.lavanderia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.laundry.lavanderia.Model.serviceLaundry.OrderService;
import com.laundry.lavanderia.repository.OrdersRepository;
import com.laundry.lavanderia.service.interfaces.IDeliveryService;

import java.util.List;

@Service
public class DeliveryServiceImpl implements IDeliveryService {

    @Autowired
    private OrdersRepository ordersRepository;

    /**
     * Obtiene todas las órdenes que se encuentran en estado Pendiente
     * 
     * @return una lista con todos los objetos OrderService que se encuentran en
     *         estado pendiente
     */
    @Override
    public List<OrderService> getAllPendingOrders() {
        return ordersRepository.findAll().stream()
                .filter(order -> "Pendiente".equals(order.getStatus()))
                .toList();
    }

    /**
     * Obtiene todas las órdenes que se han completado
     * 
     * @return una lista con todos los objetos OrderService que se han completado
     */
    @Override
    public List<OrderService> getAllCompletedOrders() {
        return ordersRepository.findAll().stream()
                .filter(order -> "Completado".equals(order.getStatus()))
                .toList();
    }

    /**
     * Obtiene los detalles de una orden pendiente por su ID
     * 
     * @param id el id de la orden
     * @return el objeto OrderService si existe y está pendiente, o null si no
     *         existe
     */
    @Override
    public OrderService getOrderPendingById(Long id) {
        return ordersRepository.findById(id)
                .filter(order -> "Pendiente".equals(order.getStatus()))
                .orElse(null);
    }

    /**
     * Obtiene los detalles de una orden completada por su ID
     * 
     * @param id el id de la orden
     * @return el objeto OrderService si existe y está completada, o null si no
     *         existe
     */
    @Override
    public OrderService getOrderCompletedById(Long id) {
        return ordersRepository.findById(id)
                .filter(order -> "Completado".equals(order.getStatus()))
                .orElse(null);
    }

    /**
     * Actualiza el estado de una orden a Completado
     * 
     * @param id el id de la orden
     * @return el objeto OrderService actualizado
     */
    @Override
    public void completeOrder(Long id) {
        ordersRepository.findById(id).ifPresent(order -> {
            order.setStatus("Completado");
            ordersRepository.save(order);
        });

    }
}
