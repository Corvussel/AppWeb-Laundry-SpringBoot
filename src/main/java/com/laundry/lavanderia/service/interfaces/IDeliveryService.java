package com.laundry.lavanderia.service.interfaces;

import java.util.List;

import com.laundry.lavanderia.Model.serviceLaundry.OrderService;

public interface IDeliveryService {


    List<OrderService> getAllPendingOrders();

    List<OrderService> getAllCompletedOrders();

    OrderService getOrderPendingById(Long id);

    OrderService getOrderCompletedById(Long id);

    void completeOrder(Long id);
}
