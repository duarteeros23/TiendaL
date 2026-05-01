package com.erosduarte.Tienda.controller;

import com.erosduarte.Tienda.entity.Ventas;
import com.erosduarte.Tienda.service.VentasService;
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
@Validated
@RequestMapping("/ventas")
public class VentasController {

    private final VentasService ventasService;

    public VentasController(VentasService ventasService) {
        this.ventasService = ventasService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ventas", ventasService.listar());
        return "venta";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model){
        model.addAttribute("venta", new Ventas());
        model.addAttribute("modoEdicion", false);
        return "venta-form";
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("venta") Ventas ventas, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion", false);
            return "venta-form";
        }
        ventasService.crear(ventas);
        return "redirect:/ventas";
    }

    @GetMapping("/{id}")
    public Ventas buscar(@PathVariable Integer id) {
        return ventasService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Ventas actualizar(@PathVariable Integer id, @Valid @RequestBody Ventas ventas) {
        return ventasService.actualizar(id, ventas);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        ventasService.eliminar(id);
    }
}