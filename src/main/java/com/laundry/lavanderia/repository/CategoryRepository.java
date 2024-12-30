package com.laundry.lavanderia.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.laundry.lavanderia.Model.serviceMangment.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}

 
