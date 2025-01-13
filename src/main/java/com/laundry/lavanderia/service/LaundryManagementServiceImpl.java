package com.laundry.lavanderia.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.laundry.lavanderia.Model.serviceMangment.Category;
import com.laundry.lavanderia.Model.serviceMangment.ServiceLaundry;
import com.laundry.lavanderia.repository.CategoryRepository;
import com.laundry.lavanderia.repository.ServiceRepository;
import com.laundry.lavanderia.service.interfaces.ILaundryServiceManagement;

@Service
public class LaundryManagementServiceImpl implements ILaundryServiceManagement {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    /**
     * Retorna una lista de todas las categorias de servicios.
     * 
     * @return una lista de objetos de la clase Category
     */
    @Override
    public List<Category> getAllCategorys() {
        return categoryRepository.findAll();
    }

    /**
     * Retorna una lista de todos los servicios de lavanderia.
     * 
     * @return una lista de objetos de la clase ServiceLaundry
     */
    @Override
    public List<ServiceLaundry> getAllServices() {
        return serviceRepository.findAll();
    }

    /**
     * Guarda una nueva categor a en la base de datos. La categor a
     * se activa automaticamente al ser creada.
     * 
     * @param newCategory objeto de la clase Category que se
     *                    dese a guardar
     */
    @Override
    public void saveCategory(Category newCategory) {
        newCategory.setActive(true);
        categoryRepository.save(newCategory);
    }

    /**
     * Guarda un nuevo servicio de lavander a en la base de datos. El servicio
     * se activa automaticamente al ser creado.
     * 
     * @param newService objeto de la clase ServiceLaundry que se
     *                   desea guardar
     */
    @Override
    public void saveService(ServiceLaundry newService) {
        newService.setActive(true);
        serviceRepository.save(newService);
    }

    /**
     * Retorna una categor a por su id, o null si no existe.
     * 
     * @param id el id de la categor a a buscar
     * @return el objeto de la clase Category que se encontr , o null si no
     *         existe
     */
    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves a laundry service by its ID.
     *
     * @param id the ID of the laundry service to retrieve
     * @return the ServiceLaundry object if found, or null if not found
     */
    @Override
    public ServiceLaundry getServiceById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    /**
     * Desactiva la categor a con el id especificado.
     * 
     * @param id el id de la categor a a desactivar
     */
    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).ifPresent(category -> {
            category.setActive(false);
            categoryRepository.save(category);
        });
    }

    /**
     * Desactiva el servicio con el id especificado.
     * 
     * @param id el id del servicio a desactivar
     */
    @Override
    public void deleteService(Long id) {
        serviceRepository.findById(id).ifPresent(service -> {
            service.setActive(false);
            serviceRepository.save(service);
        });
    }

    @Override
    public void updateCategory(Category updatedCategory) {
        categoryRepository.findById(updatedCategory.getId()).ifPresent(category -> {
            category.setName(updatedCategory.getName());
            category.setDescription(updatedCategory.getDescription());
            category.setActive(updatedCategory.isActive());
            categoryRepository.save(category);
        });
    }

    @Override
    public void updateService(ServiceLaundry updatedService) {
        serviceRepository.findById(updatedService.getId()).ifPresent(service -> {
            service.setName(updatedService.getName());
            service.setDescription(updatedService.getDescription());
            service.setPrice(updatedService.getPrice());
            service.setCategory(updatedService.getCategory());
            service.setActive(updatedService.isActive());
            service.setDuration(updatedService.getDuration());
            serviceRepository.save(service);
        });
    }

}
