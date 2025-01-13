package com.laundry.lavanderia.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.laundry.lavanderia.Model.Spending.*; 
import com.laundry.lavanderia.service.interfaces.ISpendingService;

@Controller
@RequestMapping("/spending")
public class SpendingController {

    @Autowired
    private ISpendingService spendingService;
    private static final String SHARED_LAYOUT = "shared/layout";

    /**
     * Muestra la pagina de control de gastos
     * 
     * @param model objeto que almacena atributos para la vista
     * @return el nombre de la plantilla que se va a renderizar
     */
    @GetMapping("/index")
    public String showExpenseControlPage(Model model) {
        SpendingSummary summary = spendingService.getSpendingSummary();
        model.addAttribute("summary", summary);
        model.addAttribute("content", "spending/index.html");
        return SHARED_LAYOUT;
    }

    /**
     * Muestra la pagina de registro de gasto
     * 
     * @param model objeto que almacena atributos para la vista
     * @return el nombre de la plantilla que se va a renderizar
     */
    @GetMapping("/register")
    public String showAddExpensePage(Model model) {
        model.addAttribute("spending", new Spending());
        model.addAttribute("content", "spending/add-expense.html");
        return SHARED_LAYOUT;
    }

    /**
     * Guarda un gasto en la base de datos
     * 
     * @param spending el gasto que se va a guardar
     * @return la ruta de redireccionamiento hacia la pagina principal de gastos
     */
    @PostMapping("/save")
    public String saveSpending(@ModelAttribute Spending spending) {
        spendingService.saveSpending(spending);
        return "redirect:/spending/index";
    }
}
