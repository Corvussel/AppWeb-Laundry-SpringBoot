package com.laundry.lavanderia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.laundry.lavanderia.Model.payment.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    PaymentMethod findByName(String name);
}
