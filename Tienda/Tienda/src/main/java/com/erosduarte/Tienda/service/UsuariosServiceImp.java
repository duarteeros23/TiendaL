package com.erosduarte.Tienda.service;

import com.erosduarte.Tienda.entity.Usuarios;
import com.erosduarte.Tienda.exception.ResourceNotFoundException;
import com.erosduarte.Tienda.repository.UsuariosRepository;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UsuariosServiceImp implements UsuariosService{
    private final UsuariosRepository usuariosRepository;

    public UsuariosServiceImp(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public List<Usuarios> listar() {
        return usuariosRepository.findAll();
    }

    @Override
    public Usuarios crear( Usuarios usuarios) {
        usuarios.setCodigoUsuario(null);
        return usuariosRepository.save(usuarios);
    }

    @Override
    public Usuarios actualizar(Integer id, Usuarios usuarios) {
        Usuarios existente = buscarPorId(id);
        existente.setNombreUsuario(usuarios.getNombreUsuario());
        existente.setContrasenaUsuario(usuarios.getContrasenaUsuario());
        existente.setCorreoUsuario(usuarios.getCorreoUsuario());
        existente.setEstadoUsuario(usuarios.getEstadoUsuario());
        existente.setRol(usuarios.getRol());
        return usuariosRepository.save(existente);
    }

    @Override
    public Usuarios buscarPorId(Integer id) {
        return usuariosRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario con id " + id + " no encontrado"));
    }

    @Override
    public void eliminar(Integer id) {
        if(!usuariosRepository.existsById(id)){
            throw new ResourceNotFoundException(("Id de usuario no encontrado: " + id));
        }
        usuariosRepository.deleteById(id);
    }

}
