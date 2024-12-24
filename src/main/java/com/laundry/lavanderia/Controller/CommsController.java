package com.laundry.lavanderia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comms")
public class CommsController {

    private static final String SHARED_LAYOUT = "shared/layout";

    @GetMapping("/whatsapp")
    public String showCommsPage(Model model) {
        model.addAttribute("content", "comms/index.html");
        return SHARED_LAYOUT;
    }

    
}
