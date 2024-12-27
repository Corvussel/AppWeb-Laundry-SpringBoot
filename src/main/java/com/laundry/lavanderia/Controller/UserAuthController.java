package com.laundry.lavanderia.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.laundry.lavanderia.Model.Employee.Employee;
import com.laundry.lavanderia.Model.Login.login;
import com.laundry.lavanderia.Repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/userAuth")
@RequiredArgsConstructor
public class UserAuthController {

    private final EmployeeRepository employeeRepository;

    // Redireccion para Login
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("login", new login());
        return "login/index";
    }

    // Con autenticacion
    @PostMapping("/authenticate")
    public String authenticate(@ModelAttribute("login") login login, Model model) {
        // Simulación de validación
        if ("admin".equals(login.getUsername()) && "123".equals(login.getPassword())) {
            model.addAttribute("message", "Inicio de sesión exitoso");
            model.addAttribute("content", "home/index.html");
        } else {
            model.addAttribute("message", "Usuario o contraseña incorrectos");
            model.addAttribute("content", "login/index.html");
        }
        return "authenticate";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("content", "auth/register-employee.html");
        model.addAttribute("isModification", false);
        return "shared/layout";
    }

    @GetMapping("/employees")
    public String showEmployeesPage(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        model.addAttribute("content", "auth/employees-list.html");
        return "shared/layout";
    }

    @GetMapping("/modify/{id}")
    public String getModificationPage(@PathVariable Long id, Model model) {
        // Buscar el empleado por ID
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con ID: " + id));

        // Agregar los datos al modelo
        model.addAttribute("employee", employee);
        model.addAttribute("content", "auth/register-employee.html");
        model.addAttribute("isModification", true);

        return "shared/layout";
    }

    @PostMapping("/save")
    public String saveEmployee(@RequestParam(required = false) Long id, // El ID será nulo en el registro
            @RequestParam String nombres,
            @RequestParam String apellidos,
            @RequestParam String correo,
            @RequestParam String telefono,
            @RequestParam String rol,
            @RequestParam boolean estado,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            Model model) {

        // Lógica de validación (por ejemplo, verificar si las contraseñas coinciden)
        if (!password.equals(confirmPassword)) {
            model.addAttribute("message", "Las contraseñas no coinciden");
            return "shared/layout";
        }

        // Verificar si es una modificación o un nuevo registro
        Employee employee = null;
        if (id == null) {
            // Crear un nuevo empleado
            employee = new Employee(
                    Long.valueOf(employeeRepository.findAll().size() + 1), // ID automático (para la simulación)
                    nombres,
                    apellidos,
                    correo,
                    telefono,
                    rol,
                    estado);
        } else {
            // Modificar un empleado existente
            employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
            employee.setNombres(nombres);
            employee.setApellidos(apellidos);
            employee.setCorreo(correo);
            employee.setTelefono(telefono);
            employee.setRol(rol);
            employee.setEstado(estado);
        }

        employeeRepository.save(employee);

        // Redireccionar a la lista de empleados
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("content", "auth/employees-list.html");
        return "shared/layout";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean deleted = employeeRepository.deleteById(id);

        if (deleted) {
            redirectAttributes.addFlashAttribute("message", "Empleado eliminado con éxito.");
        } else {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el empleado.");
        }

        // Redirigir a la lista de empleados
        return "redirect:/userAuth/employees";
    }
}