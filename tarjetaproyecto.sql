-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-10-2022 a las 18:52:48
-- Versión del servidor: 10.4.25-MariaDB
-- Versión de PHP: 8.0.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tarjetaproyecto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarjeta`
--

CREATE TABLE `tarjeta` (
  `pan` bigint(22) NOT NULL,
  `titular` varchar(100) NOT NULL,
  `cedula` bigint(50) NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `telefono` bigint(50) NOT NULL,
  `estados` varchar(100) NOT NULL,
  `fecha_creacion` date NOT NULL,
  `fecha_modificacion` date NOT NULL,
  `numero_validacion` bigint(20) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `identificador` varchar(100) NOT NULL,
  `pan_enmascarado` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tarjeta`
--

INSERT INTO `tarjeta` (`pan`, `titular`, `cedula`, `tipo`, `telefono`, `estados`, `fecha_creacion`, `fecha_modificacion`, `numero_validacion`, `id`, `identificador`, `pan_enmascarado`) VALUES
(1234567891452369, 'Bryan', 1234567890, 'CREDITO', 3204677370, 'ENROLADA', '2022-10-29', '2022-10-29', 89, 55, '1234562369-29-10-2022-09:16:20', '123456******2369'),
(1234567891452369, 'Andres', 1001276935, 'DEBITO', 3204677370, 'INACTIVA', '2022-10-29', '2022-10-29', 20, 56, '1234562369-29-10-2022-09:38:34', '123456******2369'),
(1234567891452369, 'Bryan', 1001276935, 'DEBITO', 3204677375, 'CREADA', '2022-10-29', '2022-10-29', 55, 57, '1234562369-29-10-2022-10:53:03', '123456******2369');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transacciones`
--

CREATE TABLE `transacciones` (
  `id` int(100) NOT NULL,
  `fecha_creacion` datetime NOT NULL,
  `fecha_modificacion` date NOT NULL,
  `estado` varchar(50) NOT NULL,
  `identificador` varchar(255) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `direccion_compra` varchar(255) DEFAULT NULL,
  `referencia` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `transacciones`
--
ALTER TABLE `transacciones`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT de la tabla `transacciones`
--
ALTER TABLE `transacciones`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
