package com.laundry.lavanderia.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laundry.lavanderia.Model.Spending.SpendingSummary;
import com.laundry.lavanderia.service.SpendingService;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/spending")
public class SpendingController {

    @Autowired
    private SpendingService spendingService;
    private static final String SHARED_LAYOUT = "shared/layout";

    @GetMapping("/index")
    public String showExpenseControlPage(Model model) {
        SpendingSummary summary = spendingService.getSpendingSummary();
        model.addAttribute("summary", summary);
        model.addAttribute("content", "spending/index.html");
        return SHARED_LAYOUT;
    }

    // Registrar nuevo gasto
    @GetMapping("/register")
    public String showAddExpensePage(Model model) {
        model.addAttribute("content", "spending/add-expense.html");
        return SHARED_LAYOUT;
    }

}
