package com.laundry.lavanderia.repository; 


import org.springframework.data.jpa.repository.JpaRepository;
import com.laundry.lavanderia.Model.serviceLaundry.OrderService;

public interface OrdersRepository extends JpaRepository<OrderService, Long> {

}
 
