package com.erosduarte.Tienda.controller;

import com.erosduarte.Tienda.entity.Clientes;
import com.erosduarte.Tienda.service.ClientesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

    private final ClientesService clientesService;

    public ClientesController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }


    @GetMapping
    public String listar(Model model){
        model.addAttribute("clientes", clientesService.listar());
        return "cliente";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cliente", new Clientes());
        model.addAttribute("modoEdicion", false);
        return "cliente-form";
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("cliente") Clientes cliente,
                        BindingResult result,
                        Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "cliente-form";
        }
        clientesService.crear(cliente.getDpíCliente(), cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/{id}")
    public Clientes buscar(@PathVariable Integer id){
        return clientesService.buscarPorid(id);
    }

    @PutMapping("/{id}")
    public Clientes actualizar(@PathVariable Integer id, @Valid @RequestBody Clientes clientes){
        return clientesService.actualizar(id, clientes);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id){
        clientesService.eliminar(id);
    }
}