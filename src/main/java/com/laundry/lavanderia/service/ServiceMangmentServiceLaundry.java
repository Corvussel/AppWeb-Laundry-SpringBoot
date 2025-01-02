package com.laundry.lavanderia.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.laundry.lavanderia.Model.serviceMangment.Category;
import com.laundry.lavanderia.Model.serviceMangment.ServiceLaundry;
import com.laundry.lavanderia.repository.CategoryRepository;
import com.laundry.lavanderia.repository.ServiceRepository;

@Service
public class ServiceMangmentServiceLaundry {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public List<Category> getAllCategorys() {
        return categoryRepository.findAll();
    }

    public List<ServiceLaundry> getAllServices() {
        return serviceRepository.findAll();
    }

    public void saveCategory(Category newCategory) {
        newCategory.setActive(true);
        categoryRepository.save(newCategory);
    }

    public void saveService(ServiceLaundry newService) {
        newService.setActive(true);
        serviceRepository.save(newService);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public ServiceLaundry getServiceById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    public void deleteCategory(Long id) {
        categoryRepository.findById(id).ifPresent(category -> {
            category.setActive(false);
            categoryRepository.save(category);
        });
    }

    public void deleteService(Long id) {
        serviceRepository.findById(id).ifPresent(service -> {
            service.setActive(false);
            serviceRepository.save(service);
        });
    }

    public void updateCategory(Category updatedCategory) {
        categoryRepository.findById(updatedCategory.getId()).ifPresent(category -> {
            category.setName(updatedCategory.getName());
            category.setDescription(updatedCategory.getDescription());
            category.setActive(updatedCategory.isActive());
            categoryRepository.save(category);
        });
    }

    public void updateService(ServiceLaundry updatedService) {
        serviceRepository.findById(updatedService.getId()).ifPresent(service -> {
            service.setName(updatedService.getName());
            service.setDescription(updatedService.getDescription());
            service.setPrice(updatedService.getPrice());
            service.setCategory(updatedService.getCategory());
            service.setActive(updatedService.isActive());
            serviceRepository.save(service);
        });
    }

}
