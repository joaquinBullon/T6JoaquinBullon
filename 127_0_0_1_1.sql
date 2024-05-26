-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 26, 2024 at 06:43 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `incidencias`
--
CREATE DATABASE IF NOT EXISTS `incidencias` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `incidencias`;
CREATE USER 'joaquin'@'%' IDENTIFIED BY '12345';
GRANT ALL PRIVILEGES ON incidencias.* TO 'joaquin'@'%';
FLUSH PRIVILEGES;
-- --------------------------------------------------------

--
-- Table structure for table `eliminadas`
--

CREATE TABLE `eliminadas` (
  `Codigo` varchar(20) NOT NULL,
  `Estado` text NOT NULL,
  `Puesto` int(200) NOT NULL,
  `Problema` text NOT NULL,
  `Fechadeeliminacion` text NOT NULL,
  `Causadeeliminacion` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pendientes`
--

CREATE TABLE `pendientes` (
  `Codigo` varchar(20) NOT NULL,
  `Estado` text NOT NULL,
  `Puesto` int(200) NOT NULL,
  `Problema` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `resueltas`
--

CREATE TABLE `resueltas` (
  `Codigo` varchar(20) NOT NULL,
  `Estado` text NOT NULL,
  `Puesto` int(200) NOT NULL,
  `Problema` varchar(200) NOT NULL,
  `Fecharesolucion` date NOT NULL,
  `Resolucion` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
