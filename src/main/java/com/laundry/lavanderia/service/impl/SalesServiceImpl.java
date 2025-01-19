package com.laundry.lavanderia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laundry.lavanderia.Model.serviceLaundry.OrderService;
import com.laundry.lavanderia.repository.OrdersRepository;
import com.laundry.lavanderia.service.interfaces.ISalesService;

@Service
public class SalesServiceImpl implements ISalesService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public List<OrderService> getAllSales() {
        return ordersRepository.findAll();
    }

    @Override
    public OrderService getSaleDetailById(Long id) {
        return ordersRepository.findById(id).orElse(null);
    }
}
