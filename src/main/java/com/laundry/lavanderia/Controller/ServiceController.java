package com.laundry.lavanderia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/services-laundry")
public class ServiceController {

    @GetMapping("/user-list-fragment")
    public String getListUserFragment() {

        return "services-laundry/userListFragment";
    }
}