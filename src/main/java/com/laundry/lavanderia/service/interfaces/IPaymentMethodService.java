package com.laundry.lavanderia.service.interfaces;

import java.util.List;

import com.laundry.lavanderia.Model.payment.PaymentMethod;

public interface IPaymentMethodService {

    List<PaymentMethod> getAllPaymentMethods();

    PaymentMethod getPaymentMethodById(Long id);

    PaymentMethod savePaymentMethod(PaymentMethod paymentMethod);

    void deletePaymentMethod(Long id);
    
}
