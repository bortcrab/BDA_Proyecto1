create database proyecto1;
use proyecto1;

create table Clientes(
idCliente int auto_increment primary key,
nombres varchar(50) not null,
apellidoPaterno varchar(50) not null,
apellidoMaterno varchar(50) not null,
codigoPostal varchar(5) not null,
colonia varchar(50) not null,
calle varchar(50) not null,
numExterior varchar(5) not null,
fechaNacimiento date not null
);

create table Cuentas(
numCuenta int auto_increment primary key,
saldo decimal(15, 2) not null,
fechaApertura date default (curdate()) not null,
estaEliminado bit default 0 not null,
idCliente int not null,
foreign key (idCliente) references Clientes(idCliente)
);

create table Operaciones(
folio int auto_increment primary key,
monto decimal(15, 2) not null,
tipo enum('Transferencia', 'Retiro sin cuenta') not null,
fechaHoraEjec datetime default now() not null,
numCuenta int not null,
foreign key (numCuenta) references Cuentas(numCuenta)
);

create table Transferencias(
folio int primary key,
foreign key (folio) references Operaciones(folio)
);

create table RetirosSinCuenta(
folio int primary key,
contrasenia int not null,
estado enum('Pendiente', 'Cobrado', 'No cobrado'),
foreign key (folio) references Operaciones(folio)
);

-- Inserts para la tabla Clientes
insert into Clientes (nombres, apellidoPaterno, apellidoMaterno, codigoPostal, colonia, calle, numExterior, fechaNacimiento) values
('Juan', 'Perez', 'Gonzalez', '12345', 'Centro', 'Calle Principal', '123', '1990-05-15'),
('Maria', 'Lopez', 'Martinez', '54321', 'Colonia Nueva', 'Avenida Principal', '456', '1988-10-25'),
('Pedro', 'Garcia', 'Sanchez', '67890', 'Otra Colonia', 'Calle Secundaria', '789', '1975-03-08'),
('Ana', 'Martinez', 'Gomez', '13579', 'Colonia Antigua', 'Avenida Antigua', '246', '1982-07-12'),
('Sofia', 'Hernandez', 'Rodriguez', '97531', 'Barrio Viejo', 'Calle de Piedra', '135', '1995-11-30'),
('Luis', 'Torres', 'Diaz', '24680', 'Barrio Nuevo', 'Calle de Madera', '246', '1978-09-18'),
('Fernanda', 'Diaz', 'Ruiz', '86420', 'Rinconada', 'Calle del Rio', '753', '1989-04-03'),
('Carlos', 'Sanchez', 'Perez', '45678', 'Colonia Moderna', 'Avenida de las Flores', '852', '1992-12-20'),
('Alejandra', 'Gomez', 'Luna', '98765', 'Nueva Zona', 'Calle del Sol', '369', '1980-08-15'),
('Javier', 'Gutierrez', 'Fernandez', '56789', 'Barrio Residencial', 'Avenida de la Luna', '147', '1973-06-28');

-- Inserts para la tabla Cuentas
insert into Cuentas (saldo, idCliente) values
(1000.00, 1),
(500.00, 2),
(2000.00, 3),
(1500.00, 4),
(700.00, 5),
(3000.00, 6),
(1200.00, 7),
(900.00, 8),
(2500.00, 9),
(1800.00, 10),
(800.00, 1),
(1600.00, 2),
(2200.00, 3),
(700.00, 4),
(3500.00, 5),
(1000.00, 6);

-- Inserts para la tabla Operaciones
insert into Operaciones (monto, tipo, numCuenta) values
(500.00, 'Transferencia', 1),
(200.00, 'Retiro sin cuenta', 3),
(100.00, 'Transferencia', 2),
(300.00, 'Retiro sin cuenta', 4),
(150.00, 'Transferencia', 3),
(200.00, 'Retiro sin cuenta', 8),
(400.00, 'Transferencia', 4),
(250.00, 'Retiro sin cuenta', 12),
(100.00, 'Transferencia', 14),
(150.00, 'Retiro sin cuenta', 16),
(200.00, 'Transferencia', 16),
(350.00, 'Retiro sin cuenta', 4),
(400.00, 'Transferencia', 14),
(200.00, 'Retiro sin cuenta', 2),
(100.00, 'Transferencia', 2),
(300.00, 'Retiro sin cuenta', 2),
(200.00, 'Transferencia', 11),
(150.00, 'Retiro sin cuenta', 5),
(250.00, 'Transferencia', 7),
(150.00, 'Retiro sin cuenta', 9),
(200.00, 'Transferencia', 11),
(300.00, 'Retiro sin cuenta', 13),
(100.00, 'Transferencia', 15),
(200.00, 'Retiro sin cuenta', 10),
(150.00, 'Transferencia', 12),
(250.00, 'Retiro sin cuenta', 9),
(200.00, 'Transferencia', 8),
(300.00, 'Retiro sin cuenta', 7),
(100.00, 'Transferencia', 6),
(150.00, 'Retiro sin cuenta', 5);

-- Inserts para la tabla Transferencias
insert into Transferencias (folio) values
(1),
(3),
(5),
(7),
(9),
(11),
(13),
(15),
(17),
(19),
(21),
(23),
(25),
(27),
(29);

-- Inserts para la tabla RetirosSinCuenta
insert into RetirosSinCuenta (folio, contrasenia, estado) values
(2, 1326, 'Pendiente'),
(4, 5678, 'No cobrado'),
(6, 4321, 'Cobrado'),
(8, 8765, 'No cobrado'),
(10, 9876, 'Pendiente'),
(12, 2345, 'Cobrado'),
(14, 6789, 'Pendiente'),
(16, 3456, 'No cobrado'),
(18, 7890, 'Cobrado'),
(20, 5432, 'Pendiente'),
(22, 8901, 'No cobrado'),
(24, 6543, 'Cobrado'),
(26, 2109, 'Pendiente'),
(28, 4567, 'No cobrado'),
(30, 1098, 'Cobrado');
