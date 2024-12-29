package com.laundry.lavanderia.service;

import org.springframework.stereotype.Service;
import com.laundry.lavanderia.Model.Deliveries.Delivery;
import com.laundry.lavanderia.Model.Deliveries.DeliveryItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService {

    // Método para obtener todas las entregas pendientes
    public List<Delivery> getAllPendingDeliveries() {
        List<Delivery> deliveries = new ArrayList<>();

        // Simular datos de ejemplo
        Delivery delivery = new Delivery();
        delivery.setId(1L);
        delivery.setOrderNumber("B0003");
        delivery.setStatus("Pendiente");
        delivery.setCustomerName("Flores Solano Russel");
        delivery.setDeliveryDate(LocalDate.of(2024, 12, 9));
        delivery.setPhoneNumber("992-676-986");
        delivery.setPaymentMethod("Efectivo");
        delivery.setAmountPaid(1299.00);
        delivery.setAmountPending(0.00);
        delivery.setTotalAmount(1299.00);

       
        List<DeliveryItem> items = new ArrayList<>();

        DeliveryItem item1 = new DeliveryItem();
        item1.setName("Camisa de Vestir");
        item1.setServiceType("Lavado y planchado");
        item1.setPrice(15.00);
        item1.setQuantity(2);
        item1.setNotes("Marca no existe, color transparente");

        DeliveryItem item2 = new DeliveryItem();
        item2.setName("Pantalon Jean");
        item2.setServiceType("Lavado especial");
        item2.setPrice(20.00);
        item2.setQuantity(1);
        item2.setNotes("Marca no existe, color transparente");

        items.add(item1);
        items.add(item2);

        delivery.setItems(items);

        deliveries.add(delivery);
        return deliveries;
    }

    // Método para obtener todas las entregas completadas
    public List<Delivery> getAllCompletedDeliveries() {
        List<Delivery> deliveries = new ArrayList<>();

        Delivery delivery = new Delivery();
        delivery.setId(1L);
        delivery.setOrderNumber("B0003");
        delivery.setStatus("Entregado");
        delivery.setCustomerName("Flores Solano Russel");
        delivery.setDeliveryDate(LocalDate.of(2024, 12, 9));
        delivery.setCompletedDate(LocalDate.of(2024, 12, 9));
        delivery.setPhoneNumber("992-676-986");
        delivery.setPaymentMethod("Efectivo");
        delivery.setAmountPaid(55.00);
        delivery.setAmountPending(0.00);
        delivery.setTotalAmount(55.00);
        delivery.setEmployeeName("James Caballero");

        List<DeliveryItem> items = new ArrayList<>();

        DeliveryItem item1 = new DeliveryItem();
        item1.setName("Camisa Formal");
        item1.setServiceType("Lavado y Planchado");
        item1.setPrice(25.00);
        item1.setQuantity(2);
        item1.setNotes("Tratamiento especial");

        DeliveryItem item2 = new DeliveryItem();
        item2.setName("Pantaloon Jean");
        item2.setServiceType("Lavado Especial");
        item2.setPrice(30.00);
        item2.setQuantity(1);
        item2.setNotes("Tratamiento especial");

        items.add(item1);
        items.add(item2);

        delivery.setItems(items);
        deliveries.add(delivery);

        return deliveries;
    }

    // Método para obtener los detalles de la entrega campletada
    public Delivery getDeliveryCompletedById(Long id) {

        Delivery delivery = new Delivery();

        delivery.setId(1L);
        delivery.setOrderNumber("B0003");
        delivery.setStatus("Entregado");
        delivery.setCustomerName("Flores Solano Russel");
        delivery.setDeliveryDate(LocalDate.of(2024, 12, 9));
        delivery.setCompletedDate(LocalDate.of(2024, 12, 9));
        delivery.setPhoneNumber("992-676-986");
        delivery.setPaymentMethod("Efectivo");
        delivery.setAmountPaid(55.00);
        delivery.setAmountPending(0.00);
        delivery.setTotalAmount(55.00);
        delivery.setEmployeeName("James Caballero");

        List<DeliveryItem> items = new ArrayList<>();

        DeliveryItem item1 = new DeliveryItem();
        item1.setName("Camisa Formal");
        item1.setServiceType("Lavado y Planchado");
        item1.setPrice(25.00);
        item1.setQuantity(2);
        item1.setNotes("Tratamiento especial");

        DeliveryItem item2 = new DeliveryItem();
        item2.setName("Pantaloon Jean");
        item2.setServiceType("Lavado Especial");
        item2.setPrice(30.00);
        item2.setQuantity(1);
        item2.setNotes("Tratamiento especial");

        items.add(item1);
        items.add(item2);

        delivery.setItems(items);

        return delivery;
    }

    // Método para obtener los detalles de entrega pendiente
    public Delivery getDeliveryPendingById(Long id) {
        Delivery delivery = new Delivery();
        delivery.setOrderNumber("B0003");
        delivery.setStatus("Pendiente");
        delivery.setCustomerName("Flores Solano Russel");
        delivery.setDeliveryDate(LocalDate.of(2024, 12, 9));
        delivery.setPhoneNumber("992-676-986");
        delivery.setPaymentMethod("Efectivo");
        delivery.setAmountPaid(55.00);
        delivery.setAmountPending(0.00);
        delivery.setTotalAmount(55.00);

        List<DeliveryItem> items = new ArrayList<>();

        DeliveryItem item1 = new DeliveryItem();
        item1.setName("Camisa Formal");
        item1.setServiceType("Lavado y Planchado");
        item1.setPrice(25.00);
        item1.setQuantity(2);
        item1.setNotes("Tratamiento especial");

        DeliveryItem item2 = new DeliveryItem();
        item2.setName("Pantaloon Jean");
        item2.setServiceType("Lavado Especial");
        item2.setPrice(30.00);
        item2.setQuantity(1);
        item2.setNotes("Tratamiento especial");

        DeliveryItem item3 = new DeliveryItem();
        item2.setName("Pantaloon Jean");
        item3.setServiceType("Lavado Especial");
        item3.setPrice(30.00);
        item3.setQuantity(1);
        item3.setNotes("Tratamiento especial");

        items.add(item1);
        items.add(item2);
        items.add(item3);
        delivery.setItems(items);

        return delivery;
    }

}
