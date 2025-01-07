package com.laundry.lavanderia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.laundry.lavanderia.Model.serviceLaundry.OrderService;

public interface OrdersRepository extends JpaRepository<OrderService, Long> {

    // este metodo es para el dashboard de la app de lavanderia para mostrar las
    // ganancias por metodo de pago en el cierre de caja de la lavanderia
    @Query("SELECT SUM(o.totalCobro) FROM OrderService o WHERE o.paymentMethod.name = :paymentMethodName")
    Double findTotalByPaymentMethod(@Param("paymentMethodName") String paymentMethodName);
}
