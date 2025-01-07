package com.laundry.lavanderia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.laundry.lavanderia.Model.client.cliente;
import java.util.List;

public interface ClientRepository extends JpaRepository<cliente, Long> {
    List<cliente> findByActivoTrue();
}
