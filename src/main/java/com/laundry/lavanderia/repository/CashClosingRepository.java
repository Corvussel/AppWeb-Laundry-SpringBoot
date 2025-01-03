package com.laundry.lavanderia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.laundry.lavanderia.Model.serviceLaundry.CashClosing;

public interface CashClosingRepository extends JpaRepository<CashClosing, Long> {
    CashClosing findTopByOrderByClosingDateDesc();
}