-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-08-2025 a las 11:21:12
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `utmedic`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cita`
--

CREATE TABLE `cita` (
  `idCita` int(11) NOT NULL,
  `idPerfil` int(11) DEFAULT NULL,
  `idPersonal` int(11) DEFAULT NULL,
  `idMotivo` int(11) DEFAULT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `observaciones` text DEFAULT NULL,
  `estado` varchar(20) NOT NULL DEFAULT 'Programada'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cita`
--

INSERT INTO `cita` (`idCita`, `idPerfil`, `idPersonal`, `idMotivo`, `fecha`, `hora`, `observaciones`, `estado`) VALUES
(16, 41, 1, 3, '2025-08-30', '06:00:00', '', 'Programada'),
(25, 41, 1, 4, '2025-08-30', '06:30:00', '', 'Atendida'),
(26, 41, 1, 5, '2025-08-23', '06:13:00', '', 'Programada'),
(27, 41, 1, 3, '2025-08-22', '06:24:00', '', 'Programada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_citas`
--

CREATE TABLE `historial_citas` (
  `IdCita` int(11) NOT NULL,
  `idPaciente` int(11) DEFAULT NULL,
  `idPersonal` int(11) DEFAULT NULL,
  `Profesion` varchar(40) DEFAULT NULL,
  `Fecha` date DEFAULT NULL,
  `Hora` time DEFAULT NULL,
  `MotivoConsulta` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `historial_citas`
--

INSERT INTO `historial_citas` (`IdCita`, `idPaciente`, `idPersonal`, `Profesion`, `Fecha`, `Hora`, `MotivoConsulta`) VALUES
(25, 2, 1, 'Médico', '2025-08-30', '06:30:00', '4 - Lesión leve o accidente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `motivo`
--

CREATE TABLE `motivo` (
  `idMotivo` int(11) NOT NULL,
  `descripcion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `motivo`
--

INSERT INTO `motivo` (`idMotivo`, `descripcion`) VALUES
(1, 'Consulta general'),
(2, 'Síntomas gripales o fiebre'),
(3, 'Dolor muscular o de cabeza'),
(4, 'Lesión leve o accidente'),
(5, 'Control de signos vitales'),
(6, 'Malestar estomacal'),
(7, 'Primera consulta psicológica'),
(8, 'Ansiedad o estrés académico'),
(9, 'Depresión'),
(10, 'Problemas personales'),
(11, 'Crisis emocional'),
(12, 'Dificultad para concentrarse'),
(13, 'Terapia breve individual'),
(14, 'Primera consulta nutricional'),
(15, 'Plan de alimentación'),
(16, 'Control de peso'),
(17, 'Control de enfermedades crónicas'),
(18, 'Trastornos alimenticios'),
(19, 'Seguimiento nutricional'),
(20, 'Situacion personal'),
(21, 'Horario ocupado'),
(22, 'Otro');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `perfil`
--

CREATE TABLE `perfil` (
  `idPerfil` int(11) NOT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `nombre` varchar(40) DEFAULT NULL,
  `apellido_paterno` varchar(40) DEFAULT NULL,
  `apellido_materno` varchar(40) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `contactoEmergencia` varchar(40) DEFAULT NULL,
  `alergias` text DEFAULT NULL,
  `peso` decimal(5,2) DEFAULT NULL,
  `padecimientos` text DEFAULT NULL,
  `foto` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `perfil`
--

INSERT INTO `perfil` (`idPerfil`, `idUsuario`, `nombre`, `apellido_paterno`, `apellido_materno`, `telefono`, `contactoEmergencia`, `alergias`, `peso`, `padecimientos`, `foto`) VALUES
(4, 4, 'Santiago', 'Guzman', 'Hernandez', '1234567890', '9876543210', 'si', NULL, 'Ninguno', 'C:\\Users\\santi\\Downloads\\desktop-wallpaper-cool-halo-reach-nes-halo-halo-disenos-de-unas-halo-emile.jpg'),
(41, 2, 'ian', 'Medina', 'Galvan', '4434', '2123', 'chambear', NULL, 'ninguno', 'C:\\Users\\santi\\OneDrive\\Imágenes\\wallpaperflare.com_wallpaper (2).jpg'),
(46, 1, 'ana', 'torres', 'Vazques', '123456', '123456', '123456', NULL, '123456', 'C:\\Users\\santi\\Downloads\\enojado66.PNG'),
(47, 13, 'laura', 'Perez', 'M', '12', '34', '56', NULL, '78', 'C:\\Users\\santi\\OneDrive\\Documentos\\descarga (1).jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personal_salud`
--

CREATE TABLE `personal_salud` (
  `idPersonal` int(11) NOT NULL,
  `nombre` varchar(40) DEFAULT NULL,
  `profesion` varchar(40) DEFAULT NULL,
  `correo` varchar(40) DEFAULT NULL,
  `telefono` varchar(10) NOT NULL,
  `idUsuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `personal_salud`
--

INSERT INTO `personal_salud` (`idPersonal`, `nombre`, `profesion`, `correo`, `telefono`, `idUsuario`) VALUES
(1, 'Dra. Ana Torres', 'Medico', 'ana.torres@utmedic.com', '4431234567', 1),
(4, 'Dra. Laura Pérez', 'Psicologo', 'laura.perez@utmedic.com', '4431234567', 13);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL,
  `matricula` varchar(50) NOT NULL,
  `password` varchar(40) NOT NULL,
  `rol` enum('paciente','medico','ADMIN') NOT NULL,
  `usuario` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `matricula`, `password`, `rol`, `usuario`) VALUES
(1, 'ANT001', '12345', 'medico', 'ana.torres'),
(2, 'IAN001', '123456', 'paciente', 'ian'),
(4, 'SGH001', '123456', 'ADMIN', 'sgh'),
(13, 'LPD001', '1224', 'medico', 'laura.perez');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cita`
--
ALTER TABLE `cita`
  ADD PRIMARY KEY (`idCita`),
  ADD KEY `idPerfil` (`idPerfil`),
  ADD KEY `idMedico` (`idPersonal`),
  ADD KEY `idMotivo` (`idMotivo`);

--
-- Indices de la tabla `historial_citas`
--
ALTER TABLE `historial_citas`
  ADD PRIMARY KEY (`IdCita`),
  ADD KEY `IdMedico` (`idPersonal`),
  ADD KEY `IdPaciente` (`idPaciente`);

--
-- Indices de la tabla `motivo`
--
ALTER TABLE `motivo`
  ADD PRIMARY KEY (`idMotivo`);

--
-- Indices de la tabla `perfil`
--
ALTER TABLE `perfil`
  ADD PRIMARY KEY (`idPerfil`),
  ADD KEY `idUsuario` (`idUsuario`);

--
-- Indices de la tabla `personal_salud`
--
ALTER TABLE `personal_salud`
  ADD PRIMARY KEY (`idPersonal`),
  ADD KEY `idUsuario` (`idUsuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`),
  ADD UNIQUE KEY `matricula` (`matricula`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cita`
--
ALTER TABLE `cita`
  MODIFY `idCita` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de la tabla `motivo`
--
ALTER TABLE `motivo`
  MODIFY `idMotivo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `perfil`
--
ALTER TABLE `perfil`
  MODIFY `idPerfil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT de la tabla `personal_salud`
--
ALTER TABLE `personal_salud`
  MODIFY `idPersonal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cita`
--
ALTER TABLE `cita`
  ADD CONSTRAINT `cita_ibfk_1` FOREIGN KEY (`idPerfil`) REFERENCES `perfil` (`idPerfil`),
  ADD CONSTRAINT `cita_ibfk_2` FOREIGN KEY (`idPersonal`) REFERENCES `personal_salud` (`idPersonal`),
  ADD CONSTRAINT `cita_ibfk_3` FOREIGN KEY (`idMotivo`) REFERENCES `motivo` (`idMotivo`);

--
-- Filtros para la tabla `historial_citas`
--
ALTER TABLE `historial_citas`
  ADD CONSTRAINT `historial_citas_ibfk_1` FOREIGN KEY (`IdCita`) REFERENCES `cita` (`idCita`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `historial_citas_ibfk_2` FOREIGN KEY (`idPersonal`) REFERENCES `personal_salud` (`idPersonal`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `historial_citas_ibfk_3` FOREIGN KEY (`idPaciente`) REFERENCES `usuario` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `perfil`
--
ALTER TABLE `perfil`
  ADD CONSTRAINT `perfil_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `personal_salud`
--
ALTER TABLE `personal_salud`
  ADD CONSTRAINT `personal_salud_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
