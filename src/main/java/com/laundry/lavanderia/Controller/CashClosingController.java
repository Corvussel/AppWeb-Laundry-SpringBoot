package com.laundry.lavanderia.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.laundry.lavanderia.Model.employee.Employee;
import com.laundry.lavanderia.Model.serviceLaundry.CashClosing;
import com.laundry.lavanderia.Model.serviceLaundry.OrderService; 
import com.laundry.lavanderia.service.interfaces.ICashClosingService;
import com.laundry.lavanderia.service.interfaces.IEmployeeService;

@Controller
@RequestMapping("/cashClosing")
public class CashClosingController {

    @Autowired
    private ICashClosingService cashClosingService;

    @Autowired
    private IEmployeeService employeeService;

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
    @ResponseBody
    public ResponseEntity<OrderService> saveCashClosing(CashClosing cashClosing) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Employee employee = employeeService.getEmployeeByEmail(userDetails.getUsername());
        if (employee == null) {
            cashClosing.setEmployee(employee);
            return ResponseEntity.notFound().build();
        }
        cashClosingService.saveCashClosing(cashClosing);
        return ResponseEntity.ok().build(); 
    }
}
