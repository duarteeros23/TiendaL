package com.erosduarte.Tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "codigo_usuario")
    private Integer codigoUsuario;

    @NotBlank(message = "El nombre del usuario no puede estar vacio")
    @Size(min = 2, max = 50, message = "El nombre del usuario debe estar entre 2 y 50 caracteres")
    @Column(name = "username")
    private String nombreUsuario;

    @NotBlank(message = "La contraseña no puede estar vacia")
    @Size(min = 8, max = 255)
    @Column(name = "password")
    private String contrasenaUsuario;

    @NotBlank(message = "El correo no puede estar vacia")
    @Email(message = "Formato de correo invalido")
    @Size(min =  5, max = 60, message = "El correo debe de estar entre 5 y 60 caracteres")
    @Column(name = "email")
    private String correoUsuario;

    @Column(name = "rol")
    private String rol;

    @NotNull
    @Column(name = "estado")
    private Boolean estadoUsuario;
}
