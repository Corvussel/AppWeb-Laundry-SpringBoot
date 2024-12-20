package com.laundry.lavanderia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comms")
public class CommsController {

    @GetMapping("/whatsapp")
    public String whatsapp() {
        return "comms/whatsapp";
    }
}
