package com.erosduarte.Tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "clientes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Clientes {

    @Id
    @NotNull(message = "El dpi no puede estar vacio")
    @Column(name = "dpi_cliente")
    private Integer dpíCliente;

    @NotBlank(message = "El nombre del cliente no puede estar vacio")
    @Size(min =2, max = 50, message = "El nombre del cliente tiene que estar entre 2 y 50 caracteres")
    @Column(name = "nombre_cliente")
    private String nombreCliente;

    @NotBlank(message = "El apellido del cliente no puede estar vacio")
    @Size(min = 2, max = 50, message = "El apellido debe de estar entre 2 y 50 caracteres")
    @Column(name = "apellido_cliente")
    private String apellidoCliente;

    @NotBlank(message = "La direccion no puede estar vacia")
    @Size(min = 10, max = 100, message = "La direccion debe de estar entre 10 y 100 caracteres")
    @Column(name = "direccion")
    private  String direccion;

    @NotNull
    @Column(name = "estado")
    private Boolean estadoCliente;

}
