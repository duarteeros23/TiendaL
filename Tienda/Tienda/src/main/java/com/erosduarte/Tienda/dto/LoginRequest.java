package com.erosduarte.Tienda.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @Email(message = "Formato de correo electronico invalido")
    @NotBlank(message = "El correo es obligatorio")
    public String correoUsuario;

    @NotBlank(message = "La contraseña no puede estar vacia")
    public String contrasenaUsuario;
}