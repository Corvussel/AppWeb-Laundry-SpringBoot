package com.laundry.lavanderia.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.laundry.lavanderia.Model.LoginDates.Login;
import com.laundry.lavanderia.service.AuthServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/userAuth")
@RequiredArgsConstructor
public class UserAuthController {

    
    @Autowired
    private AuthServiceImpl authService;

    /**
     * Muestra la vista de autenticaci n de un usuario.
     * 
     * @param model El modelo de la vista.
     * @return La vista de autenticaci n del usuario.
     */
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new Login());
        return "/login/index";
    }

    /**
     * Autentica un usuario con su nombre de usuario y contraseña.
     * 
     * Si el usuario y la contrase a son correctos, redirige al home,
     * de lo contrario, muestra un mensaje de error en la vista /login/index.
     * 
     * @param login El nombre de usuario y la contrase a del usuario.
     * @param model El modelo de la vista.
     * @return La vista /login/index si el usuario no se autentica
     *         correctamente, o redirect:/home si se autentica
     *         correctamente.
     */
    @PostMapping("/authenticate")
    public String authenticate(@ModelAttribute("loginForm") Login login, RedirectAttributes redirectAttributes) {
        try {
            Authentication authentication = authService.authenticate(login.getUsername(), login.getPassword());

            if (!authentication.isAuthenticated()) {
                redirectAttributes.addFlashAttribute("error", "Nombre de usuario o contraseña incorrectos");
            }
        } catch (AuthenticationException e) {
            redirectAttributes.addFlashAttribute("error", "Nombre de usuario o contraseña incorrectos");
        }
        return "/login/index";
    }

    /**
     * Cierra la sesion actual del usuario y redirige a la pantalla de
     * autenticaci n.
     * 
     * @return La URL de la pantalla de autenticaci n.
     */
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/userAuth/login?logout";
    }

    /**
     * Muestra la pantalla de acceso denegado.
     * 
     * Esta pantalla se muestra cuando el usuario intenta acceder a una
     * pantalla que requiere permisos que no tiene.
     * 
     * @return La vista de acceso denegado.
     */
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "filters/access-denied";
    }
}
