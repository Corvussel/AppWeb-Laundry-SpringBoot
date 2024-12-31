package com.laundry.lavanderia.repository; 

import org.springframework.data.jpa.repository.JpaRepository;
import com.laundry.lavanderia.Model.serviceLaundry.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

}
 
