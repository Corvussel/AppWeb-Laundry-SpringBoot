package com.laundry.lavanderia.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laundry.lavanderia.Model.serviceLaundry.CashClosing;
import com.laundry.lavanderia.service.CashClosingService;

@Controller
@RequestMapping("/cashClosing")
public class CashClosingController {

    @Autowired
    private CashClosingService cashClosingService;

    private static final String SHARED_LAYOUT = "shared/layout";

    @GetMapping("/index")
    public String showCashClosingPage(Model model) {

        // Se obtiene el último cierre de caja para mostrarlo en la vista de cierre de
        CashClosing lastCashClosing = cashClosingService.getLastCashClosing();
        model.addAttribute("lastCashClosing", lastCashClosing);
        model.addAttribute("cashClosing", new CashClosing());

        // Se obtienen las ganancias por método de pago para mostrarlas en la vista de
        // cierre de caja y se obtiene el total de gastos de la lavandería

        model.addAttribute("efectivoEarnings", cashClosingService.getEarningsByPaymentMethod("Efectivo"));
        model.addAttribute("yapeEarnings", cashClosingService.getEarningsByPaymentMethod("Yape"));
        model.addAttribute("plinEarnings", cashClosingService.getEarningsByPaymentMethod("Plin"));
        model.addAttribute("transferenciaEarnings", cashClosingService.getEarningsByPaymentMethod("Transferencia"));
        model.addAttribute("posEarnings", cashClosingService.getEarningsByPaymentMethod("POS"));
        model.addAttribute("totalExpenses", cashClosingService.getTotalExpenses());
        model.addAttribute("content", "cash-closing/index.html");
        return SHARED_LAYOUT;
    }

    @PostMapping("/save")
    public String saveCashClosing(CashClosing cashClosing) {
        cashClosingService.saveCashClosing(cashClosing);
        return "redirect:/cashClosing/index";
    }
}
