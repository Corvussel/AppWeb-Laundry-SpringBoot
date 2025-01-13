package com.laundry.lavanderia.service.interfaces;

import java.util.List;

import com.laundry.lavanderia.Model.serviceLaundry.OrderService;

public interface ISalesService {

    OrderService getSaleDetailById(Long id);

    List<OrderService> getAllSales();

}
