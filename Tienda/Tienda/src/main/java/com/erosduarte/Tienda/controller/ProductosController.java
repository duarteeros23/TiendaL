package com.erosduarte.Tienda.controller;

import com.erosduarte.Tienda.entity.Productos;
import com.erosduarte.Tienda.service.ProductosService;
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
@RequestMapping("/productos")
public class ProductosController {

    private final ProductosService productosService;

    public ProductosController(ProductosService productosService) {
        this.productosService = productosService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("productos", productosService.listar());
        return "producto";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model){
        model.addAttribute("producto", new Productos());
        model.addAttribute("modoEdicion", false);
        return "producto-form";
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("producto") Productos producto,
                        BindingResult result,
                        Model model){
        if(result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "producto-form";
        }
        productosService.crear(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model){
        Productos producto = productosService.buscarPorId(id);
        model.addAttribute("producto", producto);
        model.addAttribute("modoEdicion", true);
        return "producto-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable("id") Integer id, @Valid @ModelAttribute("producto") Productos producto,
                             BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("modoEdicion", true);
            return "producto-form";
        }
        producto.setCodigoProducto(id);
        productosService.actualizar(id, producto);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public  String eliminar(@PathVariable("id") Integer id){
        productosService.eliminar(id);
        return "redirect:/productos";
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam(value = "idProducto", required = false) Integer id, Model model){
        if(id != null){
            Productos producto = productosService.buscarPorId(id);
            if(producto != null){
                model.addAttribute("productos", List.of(producto));
            }else{
                model.addAttribute("error: ", "Producto con id: " + id + " no encontrado");
                model.addAttribute("productos", productosService.listar());
            }

        }else{
            return "redirect:/productos";
        }
        return "producto";
    }

}