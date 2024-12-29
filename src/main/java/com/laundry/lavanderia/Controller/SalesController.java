package com.laundry.lavanderia.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.laundry.lavanderia.service.SalesService;
import com.laundry.lavanderia.Model.Sales.SaleDetail;

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
    public ResponseEntity<SaleDetail> getSaleDetail(@PathVariable Long id) {
        SaleDetail detail = salesService.getSaleDetailById(id);
        if (detail == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detail);
    }
}
