package com.erosduarte.Tienda.service;

import com.erosduarte.Tienda.entity.Usuarios;

import java.util.List;

public interface UsuariosService {
   List<Usuarios> listar();
   Usuarios crear(Usuarios usuarios);
   Usuarios actualizar(Integer id, Usuarios usuarios);
   Usuarios buscarPorId(Integer id);
   void eliminar(Integer id);
}
