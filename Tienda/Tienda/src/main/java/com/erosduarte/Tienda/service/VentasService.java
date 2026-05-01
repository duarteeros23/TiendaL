package com.erosduarte.Tienda.service;

import com.erosduarte.Tienda.entity.Ventas;

import java.util.List;

public interface VentasService {

    List<Ventas> listar();
    Ventas crear( Ventas ventas);
    Ventas actualizar(Integer id, Ventas ventas);
    Ventas buscarPorId(Integer id);
    void eliminar(Integer id);
}