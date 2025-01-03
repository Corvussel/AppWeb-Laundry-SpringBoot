package com.laundry.lavanderia.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.laundry.lavanderia.Model.serviceLaundry.OrderService;
import com.laundry.lavanderia.service.SalesService;

@Controller
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;
    private static final String SHARED_LAYOUT = "shared/layout";

    @GetMapping("/details")
    public String showDetailsPage(Model model) {
        model.addAttribute("sales", salesService.getAllSales());
        model.addAttribute("content", "details-sales/index.html");
        return SHARED_LAYOUT;
    }

    @GetMapping("/details/{id}")
    @ResponseBody
    public ResponseEntity<OrderService> getSaleDetail(@PathVariable Long id) {
        OrderService detail = salesService.getSaleDetailById(id);
        if (detail == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detail);
    }
}
