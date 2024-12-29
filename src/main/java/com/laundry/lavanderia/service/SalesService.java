package com.laundry.lavanderia.service;

import org.springframework.stereotype.Service;
import com.laundry.lavanderia.Model.Sales.SaleDetail;
import com.laundry.lavanderia.Model.Sales.SaleItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalesService {
    
    public List<SaleDetail> getAllSales() {
        List<SaleDetail> sales = new ArrayList<>();
         
        SaleDetail sale1 = new SaleDetail();
        sale1.setId(1L);
        sale1.setOrderNumber("B0001");
        sale1.setCustomerName("Russel Flores Solano");
        sale1.setServiceType("Lavado rápido");
        sale1.setTotalAmount(23.00);
        sale1.setStatus("Entregado");
        sale1.setDate(LocalDate.now());
        sales.add(sale1);

        SaleDetail sale2 = new SaleDetail();
        sale2.setId(2L);
        sale2.setOrderNumber("B0002");
        sale2.setCustomerName("Juan Pérez"); 
        sale2.setServiceType("Lavado y planchado");
        sale2.setTotalAmount(45.00);
        sale2.setStatus("Pendiente");
        sale2.setDate(LocalDate.now());
        sales.add(sale2);

        return sales;
    }
    
    public SaleDetail getSaleDetailById(Long id) {
     
        System.out.println("Buscando detalle de venta con ID: " + id);
        SaleDetail detail = new SaleDetail();
        detail.setId(id);
        detail.setOrderNumber("B0003");
        detail.setCustomerName("Russel Flores Solano");
        detail.setCustomerType("Cliente Regular");
        detail.setPhoneNumber("992-676-862");
        detail.setStatus("Completado");
        detail.setDate(LocalDate.of(2024, 1, 15));
        
        List<SaleItem> items = new ArrayList<>();
        SaleItem item1 = new SaleItem();
        item1.setName("Lavado Completo");
        item1.setServiceType("Premium");
        item1.setQuantity(2);
        item1.setPrice(25.00);
        item1.setNotes("Marca Nike, Color Negro");
        items.add(item1);
        
        detail.setItems(items);
        detail.setTotalAmount(50.00);
        
        return detail;
    }
}
