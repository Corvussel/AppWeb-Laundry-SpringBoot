package com.laundry.lavanderia.Controller;
 

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.laundry.lavanderia.Model.employee.Employee;
import com.laundry.lavanderia.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    private static final String SHARED_LAYOUT = "shared/layout";

    @GetMapping("/index")
    public String listEmployees(Model model) {
 
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("employee", new Employee()); 
        model.addAttribute("content", "auth/employees-list.html");

        return SHARED_LAYOUT;
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        model.addAttribute("content", "auth/register-employee.html"); 
        System.out.println("Editando empleado con ID: " + id);
        return "redirect:/employees/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        
        System.out.println("Eliminando empleado con ID: " + id);
        return "redirect:/employees/index";
    }

}
