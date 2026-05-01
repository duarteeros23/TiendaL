package com.erosduarte.Tienda.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "productos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_producto")
        private  Integer codigoProducto;


    @NotBlank(message = "El nombre del producto no puede estar vacio")
    @Size(min = 2, max = 60, message = "El nombre del producto debe estar entre 2 y 60 caracteres")
    @Column(name = "nombre_producto")
    private String nombreProducto;

    @NotNull(message = "El precio no puede estar vacio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    @DecimalMax(value = "999999.99", message = "El precio es demasiado alto")
    @Column(name = "precio")
    private Double precioProducto;

    @NotNull(message = "El stock no puede estar vacio")
    @Min(0)
    @Max(1000)
    @Column(name = "stock")
    private  Integer stockProducto;

    @NotNull
    @Column(name = "estado")
    private Boolean estadoProducto;

}
