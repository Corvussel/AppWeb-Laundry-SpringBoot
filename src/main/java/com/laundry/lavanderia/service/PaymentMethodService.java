package com.laundry.lavanderia.service;
   
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.laundry.lavanderia.Model.payment.PaymentMethod;
import com.laundry.lavanderia.repository.PaymentMethodRepository;
import java.util.List;

@Service
public class PaymentMethodService {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    public PaymentMethod getPaymentMethodById(Long id) {
        return paymentMethodRepository.findById(id).orElse(null);
    }

    public PaymentMethod savePaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod); 
    }

    public void deletePaymentMethod(Long id) {
        paymentMethodRepository.deleteById(id);
    }
}
 
