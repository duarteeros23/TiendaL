package com.erosduarte.Tienda.service;

import com.erosduarte.Tienda.entity.Clientes;

import java.util.List;

public interface ClientesService {
    List<Clientes> listar();
    Clientes crear(Integer id, Clientes clientes);
    Clientes actualizar(Integer id, Clientes clientes);
    Clientes buscarPorid(Integer id);
    void eliminar(Integer id);
}
