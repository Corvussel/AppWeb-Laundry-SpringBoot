package com.laundry.lavanderia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import com.laundry.lavanderia.Model.employee.Employee;
import com.laundry.lavanderia.service.EmployeeService;
import com.laundry.lavanderia.service.RoleService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoleService roleService;

    private static final String SHARED_LAYOUT = "shared/layout";

    @GetMapping("/index")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("employee", new Employee());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("content", "auth/employees-list.html");
        return SHARED_LAYOUT;
    }

    @PostMapping("/register")
    public String saveEmployee(Employee employee) {
        if (employee.getStatus() == null || employee.getStatus().isEmpty()) {
            employee.setStatus("activo");
        }
        employeeService.saveEmployee(employee);
        return "redirect:/employees/index";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        employeeService.getEmployeeById(id);
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("content", "auth/edit-employee.html");
        return SHARED_LAYOUT;
    }

    @PostMapping("/update")
    public String updateEmployee(Employee employee) {
        if (employee.getStatus() == null || employee.getStatus().isEmpty()) {
            employee.setStatus("activo");
        }
        employeeService.updateEmployee(employee);
        return "redirect:/employees/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees/index";
    }

}
