DROP DATABASE IF EXISTS COVID19;
-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS COVID19;
USE COVID19;

-- Creación de la tabla Paises
CREATE TABLE IF NOT EXISTS Paises (
    idPais INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

-- Creación de la tabla Ciudades
CREATE TABLE IF NOT EXISTS Ciudades (
    idCiudad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    idPais INT,
    FOREIGN KEY (idPais) REFERENCES Paises(idPais)
);

-- Creación de la tabla Pacientes
CREATE TABLE IF NOT EXISTS Pacientes (
    idPaciente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    edad INT,
    sexo ENUM('Masculino', 'Femenino', 'Otro'),
    idCiudad INT,
    FOREIGN KEY (idCiudad) REFERENCES Ciudades(idCiudad)
);

-- Creación de la tabla Casos
CREATE TABLE IF NOT EXISTS Casos (
    idCaso INT AUTO_INCREMENT PRIMARY KEY,
    idPaciente INT,
    fecha DATE,
    FOREIGN KEY (idPaciente) REFERENCES Pacientes(idPaciente)
);

-- Creación de la tabla Fallecimientos
CREATE TABLE IF NOT EXISTS Fallecimientos (
    idFallecimiento INT AUTO_INCREMENT PRIMARY KEY,
    idPaciente INT,
    fecha DATE,
    FOREIGN KEY (idPaciente) REFERENCES Pacientes(idPaciente)
);

-- Creación de la tabla Recuperaciones
CREATE TABLE IF NOT EXISTS Recuperaciones (
    idRecuperacion INT AUTO_INCREMENT PRIMARY KEY,
    idPaciente INT,
    fecha DATE,
    FOREIGN KEY (idPaciente) REFERENCES Pacientes(idPaciente)
);


Create table if not exists remedio (
    idRecuperacion INT AUTO_INCREMENT PRIMARY KEY,
    idPaciente INT,
    fecha DATE,
    FOREIGN KEY (idPaciente) REFERENCES Pacientes(idPaciente)
);

-- Inserción de datos de ejemplo en la tabla Paises
INSERT INTO Paises (nombre) VALUES
('Estados Unidos'),
('Brasil'),
('India'),
('Rusia'),
('Francia');

-- Inserción de datos de ejemplo en la tabla Ciudades
INSERT INTO Ciudades (nombre, idPais) VALUES
('Nueva York', 1),
('Río de Janeiro', 2),
('Bombay', 3),
('Moscú', 4),
('París', 5);

-- Inserción de datos de ejemplo en la tabla Pacientes
INSERT INTO Pacientes (nombre, edad, sexo, idCiudad) VALUES
('Juan', 35, 'Masculino', 1),
('María', 45, 'Femenino', 2),
('Pedro', 30, 'Masculino', 3),
('Ana', 55, 'Femenino', 4),
('Carlos', 40, 'Masculino', 5);

-- Inserción de datos de ejemplo en la tabla Casos
INSERT INTO Casos (idPaciente, fecha) VALUES
(1, '2022-01-01'),
(2, '2022-01-02'),
(3, '2022-01-03'),
(4, '2022-01-04'),
(5, '2022-01-05');

-- Inserción de datos de ejemplo en la tabla Fallecimientos
INSERT INTO Fallecimientos (idPaciente, fecha) VALUES
(1, '2022-01-02'),
(3, '2022-01-03'),
(5, '2022-01-04');

-- Inserción de datos de ejemplo en la tabla Recuperaciones
INSERT INTO Recuperaciones (idPaciente, fecha) VALUES
(2, '2022-01-03'),
(4, '2022-01-04');

DELIMITER //

CREATE PROCEDURE InsertarPacienteYCaso (
    IN pNombre VARCHAR(255),
    IN pEdad INT,
    IN pSexo ENUM('Masculino', 'Femenino', 'Otro'),
    IN pIdCiudad INT,
    IN pFechaCaso DATE
)
BEGIN
    DECLARE vIdPaciente INT;

    -- Insertar un nuevo paciente
    INSERT INTO Pacientes (nombre, edad, sexo, idCiudad)
    VALUES (pNombre, pEdad, pSexo, pIdCiudad);

    -- Obtener el id del paciente insertado
    SET vIdPaciente = LAST_INSERT_ID();

    -- Insertar un nuevo caso para el paciente
    INSERT INTO Casos (idPaciente, fecha)
    VALUES (vIdPaciente, pFechaCaso);
END //

DELIMITER ;
