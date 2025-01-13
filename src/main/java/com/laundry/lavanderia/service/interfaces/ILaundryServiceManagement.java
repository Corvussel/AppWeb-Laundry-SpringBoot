package com.laundry.lavanderia.service.interfaces;

import java.util.List;

import com.laundry.lavanderia.Model.serviceMangment.Category;
import com.laundry.lavanderia.Model.serviceMangment.ServiceLaundry;

public interface ILaundryServiceManagement {

    List<Category> getAllCategorys();

    List<ServiceLaundry> getAllServices();

    void saveCategory(Category newCategory);

    void saveService(ServiceLaundry newService);

    Category getCategoryById(Long id);

    ServiceLaundry getServiceById(Long id);

    void deleteCategory(Long id);

    void deleteService(Long id);

    void updateCategory(Category updatedCategory);

    void updateService(ServiceLaundry updatedService);
}
