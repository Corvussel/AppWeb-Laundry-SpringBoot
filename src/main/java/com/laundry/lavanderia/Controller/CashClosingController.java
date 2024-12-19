package com.laundry.lavanderia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cashClosing")
public class CashClosingController {

    private static final String SHARED_LAYOUT = "shared/layout";

    @GetMapping("/cashClosing")
    public String cashClosing(Model model) {

        model.addAttribute("content", "cash-closing/cash-closing.html");
        return SHARED_LAYOUT;
    }
}
