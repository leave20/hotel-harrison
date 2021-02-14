insert into tipo_documento(nombre) values ('DNI');
insert into tipo_documento(nombre) values ('CARNET DE EXTRANJERIA');
insert into tipo_documento(nombre) values ('PASAPORTE');

insert into huesped(nombre, apellido, correo, password, telefono, documento, tipo_documento_id, estado) values ('JOEL ALEXANDER', 'GONZALES CHICLAYO', 'joel@gmail.com', 'joel123', '949257963', '74859658', 1, true);
insert into huesped(nombre, apellido, correo, password, telefono, documento, tipo_documento_id, estado) values ('NICOLAS FELIX', 'PANIAGUA CHAVEZ', 'nico@gmail.com', 'nico123', '901758663', '001766062', 2, true);
insert into huesped(nombre, apellido, correo, password, telefono, documento, tipo_documento_id, estado) values ('MANUEL SANTIAGO', 'RIVERA RAMOS', 'manu@gmail.com', 'manu123', '971278953', 'ZAB000221', 3, true);
insert into huesped(nombre, apellido, correo, password, telefono, documento, tipo_documento_id, estado) values ('LEONARDO MATHIAS', 'BRIVIO CAHUANA', 'leomathi@gmail.com', 'leo123', '905678766', '43567890', 1, true);


insert into nivel(nombre) values ('SOTANO 1');
insert into nivel(nombre) values ('SOTANO 2');
insert into nivel(nombre) values ('PRIMER PISO');
insert into nivel(nombre) values ('SEGUNDO PISO');
insert into nivel(nombre) values ('TERCER PISO');
insert into nivel(nombre) values ('CUARTO PISO');
insert into nivel(nombre) values ('QUINTO PISO');
insert into nivel(nombre) values ('SEXTO PISO');

insert into tipo_habitacion(nombre, nro_camas, precio) values ('INDIVIDUAL', 1, 50.00);
insert into tipo_habitacion(nombre, nro_camas, precio) values ('QUEEN', 1, 70.00);
insert into tipo_habitacion(nombre, nro_camas, precio) values ('KING', 1, 85.00);
insert into tipo_habitacion(nombre, nro_camas, precio) values ('MINI SUITE', 2, 130.00);
insert into tipo_habitacion(nombre, nro_camas, precio) values ('MASTER SUITE', 2, 150.00);


insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('102', 'Una habitación comoda asignada a una sola persona.','https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/individual/i111.jpg', 'DISPONIBLE', 3, 1);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('103', 'Una habitación comoda asignada a una sola persona.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/individual/i222.jpg', 'DISPONIBLE', 3, 1);
insert into habitacion(nombre, descripcion, imagen, estado, descuento, promocion, nivel_id, tipo_habitacion_id) values ('104', 'Una habitación comoda asignada a una sola persona.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/individual/i444.jpg', 'DISPONIBLE', 0.15, true, 3, 1);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('105', 'Una Habitación con dos camas para pasarla en familia.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/mini-suite/mis444.jpg', 'DISPONIBLE', 3, 4);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('106', 'Una Habitación con dos camas para pasarla en familia.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/mini-suite/mis333.png', 'DISPONIBLE', 3, 4);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('107', 'Una Habitación con dos camas para pasarla en familia.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/mini-suite/mis111.jpg', 'DISPONIBLE', 3, 4);

insert into habitacion(nombre, descripcion, imagen, estado, descuento, promocion, nivel_id, tipo_habitacion_id) values ('201', 'Una habitación comoda asignada a una sola persona.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/individual/i333.jpg', 'DISPONIBLE', 0.15, true, 4, 1);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('202', 'Una habitación comoda asignada a una sola persona.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/individual/i555.jpg', 'DISPONIBLE', 4, 1);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('203', 'Una Habitación con dos camas para pasarla en familia.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/mini-suite/mis111.jpg', 'DISPONIBLE', 4, 4);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('204', 'Una Habitación con dos camas para pasarla en familia.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/mini-suite/mis222.jpg', 'DISPONIBLE', 4, 4);
insert into habitacion(nombre, descripcion, imagen, estado, descuento, promocion, nivel_id, tipo_habitacion_id) values ('205', 'Una Habitación con dos camas para pasarla en familia.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/mini-suite/mis444.jpg', 'DISPONIBLE', 0.25, true, 4, 4);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('206', 'Una Habitación con dos camas para pasarla en familia.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/mini-suite/mis555.jpg', 'DISPONIBLE', 4, 4);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('207', 'Una Habitación con dos camas para pasarla en familia.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/mini-suite/mis333.jpg', 'DISPONIBLE', 4, 4);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('208', 'Una Habitación con dos camas para pasarla en familia.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/mini-suite/mis444.jpg', 'DISPONIBLE', 4, 4);

insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('301', 'Una habitación comoda asignada a dos personas.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/queen/q222p.jpg', 'OCUPADO', 5, 2);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('302', 'Una habitación comoda asignada a dos personas.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/queen/q555p.jpg', 'DISPONIBLE', 5, 2);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('303', 'Una habitación comoda asignada a dos personas.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/queen/q777p.jpg', 'DISPONIBLE', 5, 2);
insert into habitacion(nombre, descripcion, imagen, estado, descuento, promocion, nivel_id, tipo_habitacion_id) values ('304', 'Una habitación comoda asignada a dos personas.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/queen/q333m.jpg', 'DISPONIBLE', 0.2, true, 5, 2);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('305', 'Una habitación comoda asignada a dos personas.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/queen/q888m.jpg', 'DISPONIBLE', 5, 2);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('306', 'Una habitación comoda Matrimonial.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/king/k111m.jpg', 'DISPONIBLE', 5, 3);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('307', 'Una habitación comoda Matrimonial.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/king/k333m.jpg', 'DISPONIBLE', 5, 3);
insert into habitacion(nombre, descripcion, imagen, estado, descuento, promocion, nivel_id, tipo_habitacion_id) values ('308', 'Una habitación comoda Matrimonial.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/king/k444m.jpg', 'DISPONIBLE', 0.2, true, 5, 3);

insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('401', 'Una habitación comoda asignada a dos personas.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/queen/q111m.jpg', 'DISPONIBLE', 6, 2);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('402', 'Una habitación comoda asignada a dos personas.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/king/k777m.jpg', 'DISPONIBLE', 6, 3);
insert into habitacion(nombre, descripcion, imagen, estado, descuento, promocion, nivel_id, tipo_habitacion_id) values ('403', 'Una habitación comoda Matrimonial.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/king/k888m.jpg', 'DISPONIBLE', 0.2, true, 6, 3);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('404', 'Una habitación comoda Matrimonial.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/king/k555p.jpg', 'DISPONIBLE', 6, 3);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('405', 'Una habitación comoda Matrimonial.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/king/k666p.jpg', 'DISPONIBLE', 6, 3);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('406', 'Una habitación mediana con una cama y una sala de estar.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/mini-suite/mis111.jpg', 'DISPONIBLE', 6, 4);
insert into habitacion(nombre, descripcion, imagen, estado, descuento, promocion, nivel_id, tipo_habitacion_id) values ('407', 'Una habitación mediana con una cama y una sala de estar', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/mini-suite/mis222.jpg', 'DISPONIBLE', 0.25, true, 6, 4);

insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('501', 'Una habitación mediana con una cama y una sala de estar', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/mini-suite/mis555.jpg', 'DISPONIBLE', 7, 4);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('502', 'Una habitación mediana con una cama y una sala de estar', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/mini-suite/mis222.jpg', 'DISPONIBLE', 7, 4);
insert into habitacion(nombre, descripcion, imagen, estado, descuento, promocion, nivel_id, tipo_habitacion_id) values ('503', 'Una habitación mediana con una cama y una sala de estar', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/mini-suite/mis111.jpg', 'DISPONIBLE', 0.25, true, 7, 4);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('504', 'Una habitación mediana con dos cama y una sala de estar', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/mini-suite/mis444.jpg', 'DISPONIBLE', 7, 4);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('505', 'Una habitacion grande con una sala de estar y una cama king.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/master-suite/mas222.jpg', 'DISPONIBLE', 7, 5);
insert into habitacion(nombre, descripcion, imagen, estado, descuento, promocion, nivel_id, tipo_habitacion_id) values ('506', 'Una habitacion grande con una sala de estar y una cama king.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/master-suite/mas111.jpg', 'DISPONIBLE', 0.25, true, 7, 5);

insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('601', 'Una habitacion grande con una sala de estar y una cama king.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/master-suite/mas333.jpg', 'DISPONIBLE', 8, 5);
insert into habitacion(nombre, descripcion, imagen, estado, descuento, promocion, nivel_id, tipo_habitacion_id) values ('602', 'Una habitacion grande con una sala de estar y una cama king.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/master-suite/mas222.jpg', 'DISPONIBLE', 0.25, true, 8, 5);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('603', 'Una habitacion grande con una sala de estar y una cama king.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/master-suite/mas333.jpg', 'DISPONIBLE', 8, 5);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('604', 'Una habitacion grande con una sala de estar y una cama king.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/master-suite/mas111.jpg', 'DISPONIBLE', 8, 5);
insert into habitacion(nombre, descripcion, imagen, estado, descuento, promocion, nivel_id, tipo_habitacion_id) values ('605', 'Una habitacion grande con una sala de estar y una cama king.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/master-suite/mas333.jpg', 'DISPONIBLE', 0.25, true, 8, 5);
insert into habitacion(nombre, descripcion, imagen, estado, nivel_id, tipo_habitacion_id) values ('606', 'Una habitacion grande con una sala de estar y una cama king.', 'https://whole-souled-shouts.000webhostapp.com/hotel-harrison/images/master-suite/mas222.jpg', 'LIMPIEZA', 8, 5);


insert into estacionamiento(zona, estado) values ('SE-01-A', true);
insert into estacionamiento(zona, estado) values ('SE-02-B', true);
insert into estacionamiento(zona, estado) values ('SE-03-C', true);
insert into estacionamiento(zona, estado) values ('SE-04-D', true);
insert into estacionamiento(zona, estado) values ('SE-05-E', true);
insert into estacionamiento(zona, estado) values ('SE-06-F', true);
insert into estacionamiento(zona, estado) values ('SE-07-G', true);
insert into estacionamiento(zona, estado) values ('SE-08-H', true);

insert into tipo_usuario(nombre) values ('ADMINISTRADOR');
insert into tipo_usuario(nombre) values ('RECEPCIONISTA');

insert into usuario(nombre, apellido, dni, correo, password, direccion, telefono, tipo_usuario_id, estado) values ('CRAIG AYMAR', 'CASTRO BERROCAL', '74236859', 'dekopi@gmail.com', 'dekopi12345', 'Av. El sol 333 - SJL', '998877665', 1, true);
insert into usuario(nombre, apellido, dni, correo, password, direccion, telefono, tipo_usuario_id, estado) values ('HARRISON APP', 'APLICATIVO MOVIL', '00000000', 'root@gmail.com', 'rootharrison69', 'HOTEL HARRISON', '000000000', 2, true);
insert into usuario(nombre, apellido, dni, correo, password, direccion, telefono, tipo_usuario_id, estado) values ('ALEX MANUEL', 'VEREAU CACERES', '71234569', 'd4si@gmail.com', 'd4si123', 'Av. bayovar huascar - SJL', '912345678', 2, true);

insert into reserva(fecha_inicio, fecha_final, creado_en, usuario_id, huesped_id, habitacion_id, igv, descuento, precio_total, pago_adelantado, estado, codigo_reserva) values ('2021-02-16', '2021-02-18', '2021-02-10T07:12:25', 3, 2, 1, 18.0, 0.0, 100.0, 50.0, 'PENDIENTE', '1A2B3C4D5E');
insert into reserva(fecha_inicio, fecha_final, creado_en, usuario_id, huesped_id, habitacion_id, igv, descuento, precio_total, pago_adelantado, estado, codigo_reserva) values ('2021-02-17', '2021-02-20', '2021-02-15T07:11:45', 3, 3, 15, 32.4, 0.0, 180.0, 90.0,'ACTIVO', '5E4D3C2B1A');
insert into reserva(fecha_inicio, fecha_final, placa_vehiculo, creado_en, usuario_id, huesped_id, estacionamiento_id, habitacion_id, igv, descuento, precio_total, pago_adelantado, estado, codigo_reserva) values ('2021-02-20', '2021-02-25', 'AEA-123', '2021-02-17T10:00:06', 3, 4, 2, 41, 135, 0.0, 750.0, 300.0, 'FINALIZADO', '1E2D3C4B5A');


insert into factura(razon_social, creado_en, reserva_id, estado) values ('HUESPED', '2020-11-24T07:10:16', 1, 'PENDIENTE');
insert into factura(ruc, razon_social, creado_en, late_check_out, reserva_id, estado) values ('12345678945', 'HUESPED VIP', '2020-11-24T07:10:16', 11.0, 2, 'PENDIENTE');

insert into producto(nombre, precio, stock) values ('GASEOSA INKA KOLA 3L', 8.0, 10);
insert into producto(nombre, precio, stock) values ('GALLETA MARGARITA GRANDE', 3.0, 10);
insert into producto(nombre, precio, stock) values ('GASEOSA COCA COLA 3L', 8.0, 10);
insert into producto(nombre, precio, stock) values ('GALLETA RITZ GRANDE', 3.0, 10);
insert into producto(nombre, precio, stock) values ('GASEOSA ORO 3L', 6.0, 10);
insert into producto(nombre, precio, stock) values ('GALLETA MOROCHAS GRANDE', 3.0, 10);
insert into producto(nombre, precio, stock) values ('GASEOSA KR 3L', 6.0, 10);
insert into producto(nombre, precio, stock) values ('GALLETA GLACITAS GRANDE', 3.0, 10);
insert into producto(nombre, precio, stock) values ('GASEOSA KOLA REAL 1L', 3.0, 10);
insert into producto(nombre, precio, stock) values ('PAPITAS LAYS', 3.0, 10);
insert into producto(nombre, precio, stock) values ('CHIZITOS CHEETOS', 3.0, 10);
insert into producto(nombre, precio, stock) values ('VINO ROSE', 25.0, 10);
insert into producto(nombre, precio, stock) values ('VINO BORGOÑA', 30.0, 10);
insert into producto(nombre, precio, stock) values ('VINO TINTO', 30.0, 10);
insert into producto(nombre, precio, stock) values ('CHAMPÁN ESPUMANTE', 30.0, 10);


insert into consumo(cantidad, total, reserva_id, producto_id) values (1, 8.0, 2, 1);
insert into consumo(cantidad, total, reserva_id, producto_id) values (1, 3.0, 2, 2);
