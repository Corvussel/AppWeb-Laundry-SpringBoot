package com.laundry.lavanderia.service.interfaces;

import com.laundry.lavanderia.Model.employee.Employee;
import com.laundry.lavanderia.Model.serviceLaundry.OrderService;

public interface IOrdersService {

    String RegisterOrder(OrderService registro, Employee employee);

}
