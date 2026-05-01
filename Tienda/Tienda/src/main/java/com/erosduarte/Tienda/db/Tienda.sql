drop database if exists sistema_ventas_in5bm;
create database sistema_ventas_in5bm;
use sistema_ventas_in5bm;
 
create table clientes (
    dpi_cliente int primary key not null,
    nombre_cliente varchar(50),
    apellido_cliente varchar(50),
    direccion varchar(100),
    estado boolean
);
 
create table usuarios (
    codigo_usuario int primary key auto_increment,
    username varchar(45),
    password varchar(45),
    email varchar(60),
    rol varchar(45),
    estado boolean
);
 
create table productos (
    codigo_producto int primary key auto_increment,
    nombre_producto varchar(60),
    precio decimal(10,2),
    stock int,
    estado boolean
);
 
create table ventas (
    codigo_venta int primary key auto_increment,
    fecha_venta date,
    total decimal(10,2),
    estado boolean,
    clientes_dpi_cliente int,
    usuarios_codigo_usuario int,
    constraint fk_ventas_clientes
	foreign key (clientes_dpi_cliente)
	references clientes(dpi_cliente),
    constraint fk_ventas_usuarios
	foreign key (usuarios_codigo_usuario)
	references usuarios(codigo_usuario)
);
 
create table detalleVenta (
    codigo_detalle_venta int primary key auto_increment,
    cantidad int,
    precio_unitario decimal(10,2),
    subtotal decimal(10,2),
    productos_codigo_producto int,
    ventas_codigo_venta int,
    constraint fk_detalle_producto
	foreign key (productos_codigo_producto)
	references productos(codigo_producto),
    constraint fk_detalle_venta
	foreign key (ventas_codigo_venta)
	references ventas(codigo_venta)
);
 