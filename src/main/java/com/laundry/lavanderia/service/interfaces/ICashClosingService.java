package com.laundry.lavanderia.service.interfaces;

import java.util.List;

import com.laundry.lavanderia.Model.serviceLaundry.CashClosing;

public interface ICashClosingService {

    List<CashClosing> getAllCashClosings();

    CashClosing saveCashClosing(CashClosing cashClosing);

    CashClosing getLastCashClosing();

    double getEarningsByPaymentMethod(String paymentMethod);

    double getTotalExpenses();
}
