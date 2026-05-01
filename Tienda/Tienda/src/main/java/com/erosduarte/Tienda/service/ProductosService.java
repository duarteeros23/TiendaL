package com.erosduarte.Tienda.service;

import com.erosduarte.Tienda.entity.Productos;

import java.util.List;

public interface ProductosService {

    List<Productos> listar();
    Productos crear(Productos productos);
    Productos actualizar(Integer id, Productos productos);
    Productos buscarPorId(Integer id);
    void eliminar(Integer id);
}