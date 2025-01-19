package com.laundry.lavanderia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.laundry.lavanderia.Model.payment.PaymentMethod;
import com.laundry.lavanderia.repository.PaymentMethodRepository;
import com.laundry.lavanderia.service.interfaces.IPaymentMethodService;

import java.util.List;

@Service
public class PaymentMethodServiceImpl implements IPaymentMethodService {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Override
    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public PaymentMethod getPaymentMethodById(Long id) {
        return paymentMethodRepository.findById(id).orElse(null);
    }

    @Override
    public PaymentMethod savePaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public void deletePaymentMethod(Long id) {
        paymentMethodRepository.deleteById(id);
    }
}
