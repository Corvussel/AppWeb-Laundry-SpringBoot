package com.laundry.lavanderia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.laundry.lavanderia.Model.client.cliente;

public interface ClientRepository extends JpaRepository<cliente, Long> {
}
