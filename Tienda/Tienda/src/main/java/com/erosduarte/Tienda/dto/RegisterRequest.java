package com.erosduarte.Tienda.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "El nombre no puede ir vacio")
    @Size(min = 2, max = 100, message = "Numero de caracteres invalido en el nombre")
    public String nombreUsuario;

    @Email(message = "Formato del correo invalido")
    @NotBlank(message = "El correo es obligatorio")
    @Size(min = 5, max = 100, message = "Numero de caracteres invalido en el correo" )
    public String correoUsuario;

    @NotBlank(message = "La contraseña no puede estar vacia")
    @Size(min = 8, max = 100, message = "Numero de caracteres invalido en la contraseña")
    public String contrasenaUsuario;

}
