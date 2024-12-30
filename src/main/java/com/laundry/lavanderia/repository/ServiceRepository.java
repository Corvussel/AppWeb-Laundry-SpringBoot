package com.laundry.lavanderia.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.laundry.lavanderia.Model.serviceMangment.ServiceLaundry;

public interface ServiceRepository extends JpaRepository<ServiceLaundry, Long> {
}

 