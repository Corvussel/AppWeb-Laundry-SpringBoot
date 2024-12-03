package com.laundry.lavanderia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getLayout() {

        return "Shared/_Layout";
    }

    @GetMapping("/home")
    public String getHome() {

        return "Home/index";
    }

    @GetMapping("/DeliveryLaundry")
    public String getDeliveryLaundry() {

        return "DeliveryLaundry/index";
    }
}
