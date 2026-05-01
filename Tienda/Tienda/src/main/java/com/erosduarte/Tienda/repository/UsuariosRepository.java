package com.erosduarte.Tienda.repository;

import com.erosduarte.Tienda.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    Optional<Usuarios> findByCorreoUsuario(String correoUsuario);
    boolean existsByCorreoUsuario(String correoUsuario);
}