-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-06-2026 a las 23:02:52
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_plataforma`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mapa_clientes`
--

CREATE TABLE `mapa_clientes` (
  `id_cliente` varchar(20) NOT NULL,
  `banco` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mapa_clientes`
--

INSERT INTO `mapa_clientes` (`id_cliente`, `banco`) VALUES
('12345', 'BNB'),
('12455', 'BNB'),
('32212', 'Union'),
('32215', 'Union');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tokens_atc`
--

CREATE TABLE `tokens_atc` (
  `token` varchar(100) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `activo` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tokens_atc`
--

INSERT INTO `tokens_atc` (`token`, `usuario`, `activo`) VALUES
('f19631de00280ee49a259888e7641d93', 'OrquestadorAdmin', 1),
('token-seguro-xyz123', 'Usuario de Prueba', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `mapa_clientes`
--
ALTER TABLE `mapa_clientes`
  ADD PRIMARY KEY (`id_cliente`);

--
-- Indices de la tabla `tokens_atc`
--
ALTER TABLE `tokens_atc`
  ADD PRIMARY KEY (`token`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
