package com.erosduarte.Tienda.service;
import com.erosduarte.Tienda.dto.LoginRequest;
import com.erosduarte.Tienda.dto.LoginResponse;
import com.erosduarte.Tienda.entity.Usuarios;

import com.erosduarte.Tienda.repository.UsuariosRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UsuariosRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuariosRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(Usuarios usuario) {
        if (usuarioRepository.findByCorreoUsuario(usuario.getCorreoUsuario()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        usuario.setRol("USER");
        usuario.setEstadoUsuario(true);
        String passwordHash = passwordEncoder.encode(usuario.getContrasenaUsuario());
        usuario.setContrasenaUsuario(passwordHash);
        usuarioRepository.save(usuario);
    }

    public LoginResponse login(LoginRequest req){
        Usuarios usuario = usuarioRepository.findByCorreoUsuario(req.correoUsuario)
                .orElseThrow(() -> new RuntimeException("Las credenciales son incorrectas o no existen"));

        boolean ok = passwordEncoder.matches(req.contrasenaUsuario, usuario.getContrasenaUsuario());
        if(!ok) throw new RuntimeException("Credenciales incorrectas: Contraseña incorrecta");
        return new LoginResponse("Bienvenido : ", usuario.getCodigoUsuario(), usuario.getNombreUsuario(), usuario.getCorreoUsuario()) ;
    }
}
