package com.erosduarte.Tienda.controller;
import com.erosduarte.Tienda.entity.Usuarios;
import com.erosduarte.Tienda.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String mostrarRegister(Model model){
        model.addAttribute("user", new Usuarios());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") Usuarios usuario,
                           BindingResult result,
                           Model model) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println("Error: " + error.getDefaultMessage()));

            if (result.hasFieldErrors("nombreUsuario") ||
                    result.hasFieldErrors("correoUsuario") ||
                    result.hasFieldErrors("contrasenaUsuario")) {
                return "register";
            }
        }

        try {
            authService.register(usuario);
            return "redirect:/login?success";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
