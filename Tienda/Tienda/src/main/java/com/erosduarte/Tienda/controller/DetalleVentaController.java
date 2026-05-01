package com.erosduarte.Tienda.controller;

import com.erosduarte.Tienda.entity.DetalleVenta;
import com.erosduarte.Tienda.service.DetalleVentaService;
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
@RequestMapping("/detalleVentas")
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    public DetalleVentaController(DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping
    public String  listar(Model model){
        model.addAttribute("detalleVentas", detalleVentaService.listar());
        return "detalle_venta";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model){
        model.addAttribute("detalleVenta", new DetalleVenta());
        model.addAttribute("modoEdicion", false);
        return "detalle_venta-form";
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("detalleVenta") DetalleVenta detalleVenta,
                        BindingResult result,
                        Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "detalle_venta-form";
        }
        detalleVentaService.crear(detalleVenta);
        return "redirect:/detalleVentas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model){
        DetalleVenta detalleVenta = detalleVentaService.buscarPorId(id);
        model.addAttribute("detalleVenta", detalleVenta);
        model.addAttribute("modoEdicion", true);
        return  "detalle_venta-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable("id") Integer id, @Valid @ModelAttribute("detalleVenta") DetalleVenta detalleVenta, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion", true);
            return  "detalle_venta-form";
        }
        detalleVenta.setCodigoDetalleVenta(id);
        detalleVentaService.actualizar(id, detalleVenta);
        return "redirect:/detalleVentas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id){
        detalleVentaService.eliminar(id);
        return "redirect:/detalleVentas";
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam(value = "codigoDetalleVenta", required = false) Integer id, Model model){
        if(id != null){
            DetalleVenta detalleVenta = detalleVentaService.buscarPorId(id);
            if( detalleVenta != null){
                model .addAttribute("detalleVentas", List.of(detalleVenta));
            }else{
                model.addAttribute("error ", "Detalle de pedido con ID: " + id + "no encontrado");
                model.addAttribute("detalleVentas", detalleVentaService.listar());
            }

        }else{
            return "redirect:/detalleVentas";
        }
        return "detalle_venta";
    }
}