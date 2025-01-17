package com.laundry.lavanderia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.laundry.lavanderia.Model.Security.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

