CREATE DATABASE proyecto1;
USE proyecto1;

CREATE TABLE Clientes (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(50) NOT NULL,
    apellidoPaterno VARCHAR(50) NOT NULL,
    apellidoMaterno VARCHAR(50) NOT NULL,
    fechaNacimiento DATE NOT NULL
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
    saldo DECIMAL(15 , 2 ) NOT NULL,
    fechaApertura DATE NOT NULL DEFAULT (CURRENT_DATE()),
    estaEliminado BIT DEFAULT 0 NOT NULL,
    idCliente INT NOT NULL,
    FOREIGN KEY (idCliente)
    REFERENCES Clientes (idCliente)
);

CREATE TABLE Operaciones (
    folio INT AUTO_INCREMENT PRIMARY KEY,
    monto DECIMAL(15 , 2 ) NOT NULL,
    tipo ENUM('Transferencia', 'Retiro sin cuenta') NOT NULL,
    fechaHoraEjec DATETIME DEFAULT NOW() NOT NULL,
    numCuenta INT NOT NULL,
    FOREIGN KEY (numCuenta)
    REFERENCES Cuentas (numCuenta)
);

CREATE TABLE Transferencias (
    folio INT PRIMARY KEY,
    FOREIGN KEY (folio)
    REFERENCES Operaciones (folio)
);

CREATE TABLE RetirosSinCuenta (
    folio INT PRIMARY KEY,
    contrasenia INT NOT NULL,
    estado ENUM('Pendiente', 'Cobrado', 'No cobrado'),
    FOREIGN KEY (folio)
    REFERENCES Operaciones (folio)
);

-- Inserts para la tabla Clientes
INSERT INTO Clientes (nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento) VALUES
('Juan', 'Perez', 'Gonzalez', '1990-05-15'),
('Maria', 'Lopez', 'Martinez', '1988-10-25'),
('Pedro', 'Garcia', 'Sanchez', '1975-03-08'),
('Ana', 'Martinez', 'Gomez', '1982-07-12'),
('Sofia', 'Hernandez', 'Rodriguez', '1995-11-30'),
('Luis', 'Torres', 'Diaz', '1978-09-18'),
('Fernanda', 'Diaz', 'Ruiz', '1989-04-03'),
('Carlos', 'Sanchez', 'Perez', '1992-12-20'),
('Alejandra', 'Gomez', 'Luna', '1980-08-15'),
('Javier', 'Gutierrez', 'Fernandez', '1973-06-28');

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
INSERT INTO Operaciones (monto, tipo, numCuenta) VALUES
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
INSERT INTO Transferencias (folio) VALUES
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
INSERT INTO RetirosSinCuenta (folio, contrasenia, estado) VALUES
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
