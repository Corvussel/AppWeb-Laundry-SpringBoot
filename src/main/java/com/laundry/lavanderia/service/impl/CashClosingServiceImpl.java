package com.laundry.lavanderia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laundry.lavanderia.Model.serviceLaundry.CashClosing;
import com.laundry.lavanderia.repository.CashClosingRepository;
import com.laundry.lavanderia.repository.OrdersRepository;
import com.laundry.lavanderia.repository.SpendingRepository;
import com.laundry.lavanderia.service.interfaces.ICashClosingService;

@Service
public class CashClosingServiceImpl implements ICashClosingService {

    @Autowired
    private CashClosingRepository cashClosingRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private SpendingRepository spendingRepository;

    public List<CashClosing> getAllCashClosings() {
        return cashClosingRepository.findAll();
    }

    @Override
    public CashClosing saveCashClosing(CashClosing cashClosing) {
        return cashClosingRepository.save(cashClosing);
    }
    
    @Override
    public CashClosing getLastCashClosing() {
        return cashClosingRepository.findTopByOrderByClosingDateDesc();
    }

    /**
     * Obtiene el total de ganancias por un m todo de pago determinado.
     *
     * @param paymentMethod el nombre del m todo de pago a obtener
     * @return el total de ganancias con ese m todo de pago
     */
    @Override
    public double getEarningsByPaymentMethod(String paymentMethod) {
        Double total = ordersRepository.findTotalByPaymentMethod(paymentMethod);
        return total != null ? total : 0.0;
    }

    /**
     * Obtiene el total de gastos de la lavandería.
     *
     * @return el total de gastos de la lavandería
     */ 
    @Override
    public double getTotalExpenses() {
        Double totalAmount = spendingRepository.getTotalAmount();
        return totalAmount != null ? totalAmount : 0.0;
    }
}
