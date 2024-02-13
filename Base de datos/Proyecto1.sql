CREATE DATABASE proyecto1;
USE proyecto1;

-- Creación de tablas
CREATE TABLE Clientes (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(50) NOT NULL,
    apellidoPaterno VARCHAR(50) NOT NULL,
    apellidoMaterno VARCHAR(50) NOT NULL,
    fechaNacimiento DATE NOT NULL,
    correo VARCHAR(255) UNIQUE NOT NULL,
    contrasenia VARBINARY(255) NOT NULL
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
    contrasenia VARBINARY(255),
    estado ENUM('Pendiente', 'Cobrado', 'No cobrado'),
    fechaHoraCobro DATETIME,
    FOREIGN KEY (folio) REFERENCES Operaciones (folio)
);

CREATE TABLE Depositos (
    folio INT PRIMARY KEY,
    FOREIGN KEY (folio) REFERENCES Operaciones (folio)
);


-- TRIGGER PARA GUARDAR LA CONTRASEÑA DEL RETIRO SIN CUENTA
DELIMITER //
CREATE TRIGGER insertarRetiroSinCuenta
BEFORE INSERT ON RetirosSinCuenta
FOR EACH ROW
BEGIN
    DECLARE contra VARCHAR(8);
    CALL spGenerarContraseniaRSC(contra);
    SET NEW.contrasenia = AES_ENCRYPT(contra, 'pipucatepipucate');
END; //

DELIMITER ;


-- STORED PROCEDURE PARA GENERAR UNA CONTRASEÑA DE 8 DÍGITOS.
DELIMITER //
CREATE PROCEDURE spGenerarContraseniaRSC(OUT contra VARCHAR(8))
BEGIN
	SET contra = CONCAT(FLOOR(RAND() * 10),
						 FLOOR(RAND() * 10),
                         FLOOR(RAND() * 10),
                         FLOOR(RAND() * 10),
                         FLOOR(RAND() * 10),
                         FLOOR(RAND() * 10),
                         FLOOR(RAND() * 10),
                         FLOOR(RAND() * 10));
END; //
DELIMITER ;


-- STORED PROCEDURE y TRANSACCIÓN PARA LLEVAR A CABO LAS TRANSFERENCIAS
DELIMITER //
CREATE PROCEDURE spTransferencia (IN numCuentaOrigen INT, IN numCuentaDestino INT, IN monto DECIMAL(15, 2))
BEGIN
    DECLARE saldoActual DECIMAL(15, 2);
    DECLARE mensaje VARCHAR(255);
	
    START TRANSACTION;
    
    SET autocommit = 0;
        
	-- Se verifica que existan los números de cuenta.
	IF (numCuentaOrigen NOT IN(SELECT numCuenta FROM Cuentas WHERE estaEliminado = 0)
		AND
        numCuentaDestino NOT IN(SELECT numCuenta FROM Cuentas WHERE estaEliminado = 0)
        ) THEN
		SET mensaje = 'Ninguna de las cuentas existe.';
	ELSEIF (numCuentaOrigen NOT IN(SELECT numCuenta FROM Cuentas WHERE estaEliminado = 0)) THEN
		SET mensaje = 'La cuenta origen no existe.';
	ELSEIF (numCuentaDestino NOT IN(SELECT numCuenta FROM Cuentas WHERE estaEliminado = 0)) THEN
		SET mensaje = 'La cuenta destino no existe.';
	END IF;
    
     -- Se obtiene el saldo actual de la cuenta y se guarda en la variable saldoActual
    SELECT saldo INTO saldoActual FROM Cuentas WHERE numCuenta = numCuentaOrigen;
    
    IF monto > saldoActual THEN
		SET mensaje = 'No cuentas con los fondos suficientes.';
    END IF;
    
	UPDATE Cuentas SET saldo = saldo - monto WHERE numCuenta = numCuentaOrigen;
    UPDATE Cuentas SET saldo = saldo + monto WHERE numCuenta = numCuentaDestino;
    
    -- Mostrar mensaje después del COMMIT
    IF mensaje IS NOT NULL THEN
        SELECT mensaje AS Mensaje;
        ROLLBACK;
	ELSE
		COMMIT;
    END IF;
END; //
DELIMITER ;


-- FAVOR DE EJECUTAR ESTA INSTRUCCIÓN PARA QUE LOS RETIROS SIN CUENTA ACTUALICEN SU ESTADO
-- AUTOMÁTICAMENTE
SET GLOBAL event_scheduler = ON;


-- EVENTO QUE CADA 10 SEGUNDOS CHECA SI DEBE ACTUALIZAR EL ESTADO DE LOS RETIROS SIN CUENTA
DELIMITER //
CREATE EVENT actualizarEstadoRetiros
ON SCHEDULE EVERY 10 SECOND -- Cambiar a la frecuencia deseada, por ejemplo, cada minuto
DO
BEGIN
    -- Actualizar el estado de las operaciones pendientes que han pasado más de 10 minutos
    UPDATE RetirosSinCuenta r
    INNER JOIN Operaciones o ON r.folio = o.folio
    SET r.estado = 'No cobrado'
    WHERE r.estado = 'Pendiente' 
    AND TIMESTAMPDIFF(MINUTE, o.fechaHoraEjec, NOW()) >= 10;
END; //
DELIMITER ;

-- COMIENZAN LOS INSERTS DE MUESTRA
-- Inserts para la tabla Clientes
INSERT INTO Clientes (nombres, apellidoPaterno, apellidoMaterno, fechaNacimiento, correo, contrasenia) VALUES
('Juan', 'Perez', 'Gonzalez', '1990-05-15', 'a', AES_ENCRYPT('a', 'pipucatepipucate')),
('Maria', 'Lopez', 'Martinez', '1988-10-25', 'b@b.com', AES_ENCRYPT('b', 'pipucatepipucate')),
('Pedro', 'Garcia', 'Sanchez', '1975-03-08', 'c@c.com', AES_ENCRYPT('c', 'pipucatepipucate')),
('Ana', 'Martinez', 'Gomez', '1982-07-12', 'd@d.com', AES_ENCRYPT('d', 'pipucatepipucate')),
('Sofia', 'Hernandez', 'Rodriguez', '1995-11-30', 'e@e.com', AES_ENCRYPT('e', 'pipucatepipucate')),
('Luis', 'Torres', 'Diaz', '1978-09-18', 'f@f.com', AES_ENCRYPT('f', 'pipucatepipucate')),
('Fernanda', 'Diaz', 'Ruiz', '1989-04-03', 'g@g.com', AES_ENCRYPT('g', 'pipucatepipucate')),
('Carlos', 'Sanchez', 'Perez', '1992-12-20', 'h@h.com', AES_ENCRYPT('h', 'pipucatepipucate')),
('Alejandra', 'Gomez', 'Luna', '1980-08-15', 'i@i.com', AES_ENCRYPT('i', 'pipucatepipucate')),
('Javier', 'Gutierrez', 'Fernandez', '1973-06-28', 'j@j.com', AES_ENCRYPT('j', 'pipucatepipucate'));

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
INSERT INTO RetirosSinCuenta (folio, estado, fechaHoraCobro) VALUES
(3, 'Pendiente', null),
(7, 'No cobrado', null),
(11, 'Cobrado', now()),
(15, 'No cobrado', null),
(19, 'Pendiente', null),
(23, 'Cobrado', now()),
(27, 'Pendiente', null);

INSERT INTO Depositos  VALUES
(4),
(8),
(12),
(16),
(20),
(24),
(28);