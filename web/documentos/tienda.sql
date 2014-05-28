-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-05-2014 a las 10:14:52
-- Versión del servidor: 5.5.27
-- Versión de PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `tienda`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE IF NOT EXISTS `categoria` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `imagen` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`, `imagen`) VALUES
(1, 'Europa', 'europa.jpg'),
(2, 'America', 'america.jpg'),
(3, 'Africa', 'africa.png'),
(4, 'Asia', 'asia.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `telefono` varchar(25) NOT NULL,
  `direccion` varchar(90) NOT NULL,
  `tarjeta` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orden_cliente`
--

CREATE TABLE IF NOT EXISTS `orden_cliente` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `total` decimal(8,2) unsigned NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `numero_confirmacion` varchar(45) NOT NULL,
  `cliente_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`,`cliente_id`),
  UNIQUE KEY `numero_confirmacion_UNIQUE` (`numero_confirmacion`),
  KEY `fk_orden_cliente_cliente1_idx` (`cliente_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orden_cliente_has_producto`
--

CREATE TABLE IF NOT EXISTS `orden_cliente_has_producto` (
  `orden_cliente_id` int(10) unsigned NOT NULL,
  `producto_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`orden_cliente_id`,`producto_id`),
  KEY `fk_orden_cliente_has_producto_producto1_idx` (`producto_id`),
  KEY `fk_orden_cliente_has_producto_orden_cliente1_idx` (`orden_cliente_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE IF NOT EXISTS `producto` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `precio` decimal(8,2) unsigned NOT NULL,
  `descripcion` mediumtext NOT NULL,
  `imagen` varchar(45) NOT NULL,
  `ultima_actualizacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `categoria_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`,`categoria_id`),
  KEY `fk_producto_categoria_idx` (`categoria_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17 ;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `nombre`, `precio`, `descripcion`, `imagen`, `ultima_actualizacion`, `categoria_id`) VALUES
(1, 'Barcelona Modernista', 675.00, 'Descubre a Gaudi y las rutas modernistas ...', 'barcelona.jpg', '2014-05-28 07:44:23', 1),
(2, 'Amsterdam en bicicleta', 399.00, 'Callejea por esta fantastica ciudad ...', 'amsterdam.jpg', '2014-05-28 08:13:31', 1),
(3, 'London bites', 850.00, 'Navega por el Tamesis y disfruta de su amplia oferta cultural', 'london.jpg', '2014-05-28 08:13:31', 1),
(4, 'Paris la nuit', 650.00, 'Disfruta de la noche parisina ...', 'paris.jpg', '2014-05-28 08:13:31', 1),
(5, 'Acapulco playas', 1250.00, 'Tequila y playa para un buen torrao', 'acapulco.jpg', '2014-05-28 08:13:31', 2),
(6, 'Escenarios de New York ', 1110.00, 'Muerde la manzana !!', 'newyork.jpg', '2014-05-28 08:13:31', 2),
(7, 'Machu Picchu Ancestral', 2200.00, 'Asciende y escucha las piedras ancestrales.', 'machupichu.jpg', '2014-05-28 08:13:31', 2),
(8, 'Colorado: Gran Canyon', 3500.00, 'Sobrevuela en helicóptero esta maravilla de la naturaleza.', 'colorado.jpg', '2014-05-28 08:13:31', 2),
(9, 'Egipto: El Secreto de Nefert Eru', 850.00, 'Descubre los misterios y asesinatos.....y regresa.', 'egipto.png', '2014-05-28 08:13:31', 3),
(10, 'Marruecos: Colores y Especias', 420.00, 'Té verde con churros, especias y colores.', 'marruecos.jpg', '2014-05-28 08:13:31', 3),
(11, 'Túnez de Cine', 760.00, 'Visita las localizaciones de la saga Star Wars.', 'tunez.png', '2014-05-28 08:13:31', 3),
(12, 'Zimbawe: Tam-tams.', 1545.00, 'Sigue las huellas de Livingstone...¿supongo?', 'zimbawe.png', '2014-05-28 08:13:31', 3),
(13, 'A Pekin en tren.', 2500.00, '¡Viajeros al tren!  Tu viaje empieza aquí', 'pekin.png', '2014-05-28 08:13:31', 4),
(14, 'Templos de Bangkok', 1250.00, 'Descubre la cultura budista.', 'bangkok.png', '2014-05-28 08:13:32', 4),
(15, 'Vietnam al Natural', 1460.00, 'Incienso y arrozales. Reserva YA y te regalamos un Nón Lá.', 'hochiminh.png', '2014-05-28 08:13:32', 4),
(16, 'Tokyo Geek', 3610.00, 'Descubre el país de la tecnología puntera.', 'tokyo.png', '2014-05-28 08:13:32', 4);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `orden_cliente`
--
ALTER TABLE `orden_cliente`
  ADD CONSTRAINT `fk_orden_cliente_cliente1` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `orden_cliente_has_producto`
--
ALTER TABLE `orden_cliente_has_producto`
  ADD CONSTRAINT `fk_orden_cliente_has_producto_orden_cliente1` FOREIGN KEY (`orden_cliente_id`) REFERENCES `orden_cliente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_orden_cliente_has_producto_producto1` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `fk_producto_categoria` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
