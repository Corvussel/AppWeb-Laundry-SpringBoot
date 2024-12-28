package com.laundry.lavanderia.service;

import java.util.ArrayList;
import java.util.List;
import com.laundry.lavanderia.Model.serviceMangment.Category;
import com.laundry.lavanderia.Model.serviceMangment.ServiceLaundry;

import org.springframework.stereotype.Service;

@Service
public class ServiceMangmentServiceLaundry {

    private List<Category> categories;
    private List<ServiceLaundry> services;

    public ServiceMangmentServiceLaundry() {
        initializeSampleData();
    }

    private void initializeSampleData() {

        categories = new ArrayList<>();
        Category ropaNormal = new Category(1L, "Ropa Normal", "Categoría para ropa de uso diario", true);
        Category ropaDelicada = new Category(2L, "Ropa Delicada",
                "Categoría para prendas que requieren cuidado especial", true);
        categories.add(ropaNormal);
        categories.add(ropaDelicada);

        services = new ArrayList<>();
        services.add(new ServiceLaundry(1L, "Lavado Simple", ropaNormal, 15.00, "Servicio de lavado básico", true, 2));
        services.add(new ServiceLaundry(2L, "Lavado y Planchado", ropaNormal, 25.00,
                "Servicio completo de lavado y planchado",
                true, 3));
        services.add(
                new ServiceLaundry(3L, "Lavado Delicado", ropaDelicada, 30.00, "Lavado especial para prendas delicadas",
                        true, 4));

        ropaNormal.setServices(services.stream()
                .filter(s -> s.getCategory().getId().equals(ropaNormal.getId()))
                .toList());
        ropaDelicada.setServices(services.stream()
                .filter(s -> s.getCategory().getId().equals(ropaDelicada.getId()))
                .toList());
    }

    // se obtiene la lista de categorias
    public List<Category> getAllCategorys() {
        return categories;
    }

    // se obtiene la lista de servicios
    public List<ServiceLaundry> getAllServices() {
        return services;
    }

    // se obtiene la categoria por id
    public Category getCategoryById(Long id) {
        System.out.println("Buscando categoría con ID: " + id);
        return categories.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    // se obtiene el servicio por id
    public ServiceLaundry getServiceById(Long id) {
        System.out.println("Buscando servicio con ID: " + id);
        return services.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    // metodo que elimina la categoria
    public void deleteCategory(Long id) {
        System.out.println("Eliminando categoría con ID: " + id);
        categories.removeIf(c -> c.getId().equals(id));
    }

    // metodo que elimina el servicio
    public void deleteService(Long id) {
        System.out.println("Eliminando servicio con ID: " + id);
        services.removeIf(s -> s.getId().equals(id));
    }

    // metodo que actualiza la categoria
    public void updateCategory(Category updatedCategory) {
        System.out.println("Actualizando categoría con ID: " + updatedCategory.getId());
        System.out.println(
                updatedCategory.getName() + " " + updatedCategory.getDescription() + " " + updatedCategory.isActive());
    }

    // metodo que actualiza el servicio
    public void updateService(ServiceLaundry updatedService) {
        System.out.println("Actualizando servicio con ID: " + updatedService.getId());
        System.out
                .println(updatedService.getName() + " " + updatedService.getPrice() + " " + updatedService.isActive());
    }
    
}
