package com.laundry.lavanderia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laundry.lavanderia.Model.serviceLaundry.OrderService;
import com.laundry.lavanderia.repository.OrdersRepository;

@Service
public class SalesService {

    @Autowired
    private OrdersRepository ordersRepository;

    public List<OrderService> getAllSales() {
        return ordersRepository.findAll();
    }

    public OrderService getSaleDetailById(Long id) {
        return ordersRepository.findById(id).orElse(null);
    }
}
