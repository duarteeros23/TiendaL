# TiendaL
Mi proyecto de tienda con Login que auténtica  si es administrador o usuario lo cual determinara a las vistas que podra acceder
-Para acceder como admin primero hay que registrar el usuario desde la web y luego modificar el rol desde la base de datos
script sql para modificarlo:
UPDATE usuarios SET rol = 'ADMIN' WHERE email = 'tu_correo@gmail.com';