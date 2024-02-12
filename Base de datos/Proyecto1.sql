CREATE DATABASE proyecto1;
USE proyecto1;

CREATE TABLE Clientes (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(50) NOT NULL,
    apellidoPaterno VARCHAR(50) NOT NULL,
    apellidoMaterno VARCHAR(50) NOT NULL,
    fechaNacimiento DATE NOT NULL,
    correo VARCHAR(255) UNIQUE NOT NULL,
    contrasenia VARCHAR(128) NOT NULL
);

CREATE TABLE Direcciones (
	codigoDireccion INT AUTO_INCREMENT PRIMARY KEY,
    codigoPostal VARCHAR(5) NOT NULL,
    colonia VARCHAR(50) NOT NULL,
    calle VARCHAR(50) NOT NULL,
    numExterior VARCHAR(5) NOT NULL,
    idCliente INT,
    FOREIGN KEY (idCliente)
    REFERENCES Clientes (idCliente)
);

CREATE TABLE Cuentas (
    numCuenta INT AUTO_INCREMENT PRIMARY KEY,
    saldo DECIMAL(15 , 2) NOT NULL,
    fechaApertura DATE NOT NULL DEFAULT (CURRENT_DATE()),
    estaEliminado BIT DEFAULT 0 NOT NULL,
    idCliente INT NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES Clientes (idCliente)
);

CREATE TABLE Operaciones (
    folio INT AUTO_INCREMENT PRIMARY KEY,
    monto DECIMAL(15 , 2 ) NOT NULL,
    tipo ENUM('Transferencia', 'Retiro', 'Retiro sin cuenta', 'Depósito') NOT NULL,
    fechaHoraEjec DATETIME DEFAULT NOW() NOT NULL,
    numCuentaEmisora INT NOT NULL,
    FOREIGN KEY (numCuentaEmisora) REFERENCES Cuentas (numCuenta)
);

CREATE TABLE Transferencias (
    folio INT PRIMARY KEY,
    numCuentaReceptora INT NOT NULL,
    FOREIGN KEY (folio) REFERENCES Operaciones (folio)
);

CREATE TABLE Retiros (
    folio INT PRIMARY KEY,
    FOREIGN KEY (folio) REFERENCES Operaciones (folio)
);

CREATE TABLE RetirosSinCuenta (
    folio INT PRIMARY KEY,
    contrasenia INT NOT NULL,
    estado ENUM('Pendiente', 'Cobrado', 'No cobrado'),
    fechaHoraCobro DATETIME,
    FOREIGN KEY (folio) REFERENCES Operaciones (folio)
);

CREATE TABLE Depositos (
    folio INT PRIMARY KEY,
    FOREIGN KEY (folio) REFERENCES Operaciones (folio)
);

CREATE VIEW todasOperaciones AS
SELECT o.folio, o.tipo, o.monto, o.fechaHoraEjec, o.numCuentaEmisora, t.numCuentaReceptora,
rsc.contrasenia, rsc.fechaHoraCobro 
FROM Operaciones o
LEFT JOIN Transferencias t on o.folio = t.folio
LEFT JOIN Retiros r on o.folio = r.folio
LEFT JOIN RetirosSinCuenta rsc on o.folio = rsc.folio
LEFT JOIN Depositos d on o.folio = d.folio;

-- Inserts para la tabla Clientes
INSERT INTO Clientes (nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, correo, contrasenia) VALUES
('Juan', 'Perez', 'Gonzalez', '1990-05-15', 'a@a.com', sha2('a', 512)),
('Maria', 'Lopez', 'Martinez', '1988-10-25', 'b@b.com', sha2('a', 512)),
('Pedro', 'Garcia', 'Sanchez', '1975-03-08', 'c@c.com', sha2('a', 512)),
('Ana', 'Martinez', 'Gomez', '1982-07-12', 'd@d.com', sha2('a', 512)),
('Sofia', 'Hernandez', 'Rodriguez', '1995-11-30', 'e@e.com', sha2('a', 512)),
('Luis', 'Torres', 'Diaz', '1978-09-18', 'f@f.com', sha2('a', 512)),
('Fernanda', 'Diaz', 'Ruiz', '1989-04-03', 'g@g.com', sha2('a', 512)),
('Carlos', 'Sanchez', 'Perez', '1992-12-20', 'h@h.com', sha2('a', 512)),
('Alejandra', 'Gomez', 'Luna', '1980-08-15', 'i@i.com', sha2('a', 512)),
('Javier', 'Gutierrez', 'Fernandez', '1973-06-28', 'j@j.com', sha2('a', 512));

-- Inserts para la tabla Direcciones
INSERT INTO Direcciones (codigoPostal, colonia, calle, numExterior, idCliente) VALUES
('12345', 'Centro', 'Calle Principal', '123', 1),
('54321', 'Colonia Nueva', 'Avenida Principal', '456', 2),
('67890', 'Otra Colonia', 'Calle Secundaria', '789', 3),
('13579', 'Colonia Antigua', 'Avenida Antigua', '246', 4),
('97531', 'Barrio Viejo', 'Calle de Piedra', '135', 5),
('24680', 'Barrio Nuevo', 'Calle de Madera', '246', 6),
('86420', 'Rinconada', 'Calle del Rio', '753', 7),
('45678', 'Colonia Moderna', 'Avenida de las Flores', '476',  8),
('98765', 'Nueva Zona', 'Calle del Sol', '369', 9),
('56789', 'Barrio Residencial', 'Avenida de la Luna', '147', 10);

-- Inserts para la tabla Cuentas
INSERT INTO Cuentas (saldo, idCliente) VALUES
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
INSERT INTO Operaciones (monto, tipo, numCuentaEmisora) VALUES
(500.00, 'Transferencia', 1),
(200.00, 'Retiro', 3),
(100.00, 'Retiro sin cuenta', 2),
(300.00, 'Depósito', 4),
(150.00, 'Transferencia', 3),
(200.00, 'Retiro', 8),
(400.00, 'Retiro sin cuenta', 4),
(250.00, 'Depósito', 12),
(100.00, 'Transferencia', 14),
(150.00, 'Retiro', 16),
(200.00, 'Retiro sin cuenta', 16),
(350.00, 'Depósito', 4),
(400.00, 'Transferencia', 14),
(200.00, 'Retiro', 2),
(100.00, 'Retiro sin cuenta', 2),
(300.00, 'Depósito', 2),
(200.00, 'Transferencia', 11),
(150.00, 'Retiro', 5),
(250.00, 'Retiro sin cuenta', 7),
(150.00, 'Depósito', 9),
(200.00, 'Transferencia', 11),
(300.00, 'Retiro', 13),
(100.00, 'Retiro sin cuenta', 15),
(200.00, 'Depósito', 10),
(150.00, 'Transferencia', 12),
(250.00, 'Retiro', 9),
(200.00, 'Retiro sin cuenta', 8),
(300.00, 'Depósito', 7),
(100.00, 'Transferencia', 6),
(150.00, 'Retiro', 5);

-- Inserts para la tabla Transferencias
INSERT INTO Transferencias VALUES
(1, 2),
(5, 6),
(9, 10),
(13, 14),
(17, 18),
(21, 22),
(25, 26),
(29, 30);

-- Inserts para la tabla RetirosSinCuenta
INSERT INTO Retiros  VALUES
(2),
(6),
(10),
(14),
(18),
(22),
(26),
(30);

-- Inserts para la tabla RetirosSinCuenta
INSERT INTO RetirosSinCuenta VALUES
(3, 84072651, 'Pendiente', null),
(7, 30518729, 'No cobrado', null),
(11, 61930472, 'Cobrado', now()),
(15, 92810634, 'No cobrado', null),
(19, 20387456, 'Pendiente', null),
(23, 74501928, 'Cobrado', now()),
(27, 51263789, 'Pendiente', null);

INSERT INTO Depositos  VALUES
(4),
(8),
(12),
(16),
(20),
(24),
(28);