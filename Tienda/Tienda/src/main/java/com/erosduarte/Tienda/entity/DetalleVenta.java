package com.erosduarte.Tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detalleVenta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_detalle_venta")
    private Integer codigoDetalleVenta;

    @NotNull(message = "La cantidad no puede estar vacía")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    @Column(name = "cantidad")
    private Integer cantidad;

    @NotNull(message = "El precio unitario no puede estar vacío")
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;

    @NotNull(message = "El subtotal no puede estar vacío")
    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @NotNull
    @Column(name = "productos_codigo_producto", nullable = false)
    private Integer idProducto;

    @NotNull
    @Column(name = "ventas_codigo_venta", nullable = false)
    private Integer idVenta;
}