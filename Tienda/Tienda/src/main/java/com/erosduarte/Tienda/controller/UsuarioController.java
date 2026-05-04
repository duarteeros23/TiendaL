package com.erosduarte.Tienda.controller;

import com.erosduarte.Tienda.entity.Usuarios;
import com.erosduarte.Tienda.service.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuariosService usuariosService;

    public UsuarioController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping
    public String  listar(Model model){
        model.addAttribute("usuarios", usuariosService.listar());
        return "usuario";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuarios());
        model.addAttribute("modoEdicion", false);
        return "usuario-form";
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("usuario") Usuarios usuario,
                        BindingResult result,
                        Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "usuario-form";
        }
        usuariosService.crear(usuario);
        return "redirect:/usuarios";
    }


    @GetMapping("/{id}")
    public Usuarios buscar(@PathVariable Integer id){
        return  usuariosService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Usuarios actualizar(@PathVariable Integer id, @Valid @RequestBody Usuarios usuarios){
        return usuariosService.actualizar(id, usuarios);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  void eliminar(@PathVariable Integer id){
        usuariosService.eliminar(id);
    }
}
