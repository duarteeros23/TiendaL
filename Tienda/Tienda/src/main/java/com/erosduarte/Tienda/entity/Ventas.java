package com.erosduarte.Tienda.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Entity
@Table(name = "ventas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ventas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_venta")
    private Integer codigoVenta;

    @NotNull(message = "La fecha de venta no puede estar vacía")
    @Column(name = "fecha_venta")
    private LocalDate fechaVenta;

    @NotNull(message = "El total no puede estar vacío")
    @Column(name = "total")
    private Double total;

    @NotNull(message = "El estado no puede estar vacío")
    @Column(name = "estado")
    private Boolean estado;

    @NotNull(message = "El id cliente no puede estar vacio")
    @Column(name = "clientes_dpi_cliente")
    private Integer idCliente;

    @NotNull(message = "El id usuario no puede estar vacio")
    @Column(name = "usuarios_codigo_usuario")
    private Integer idUsuario;
}

