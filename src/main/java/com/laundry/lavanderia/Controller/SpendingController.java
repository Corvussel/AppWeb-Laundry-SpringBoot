package com.laundry.lavanderia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/spending")
public class SpendingController {

    private static final String SHARED_LAYOUT = "shared/layout";

     // gastos menu principal
     @GetMapping("/index")
     public String showExpenseControlPage(Model model) {
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
