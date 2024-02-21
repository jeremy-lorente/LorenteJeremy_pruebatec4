-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-02-2024 a las 03:53:38
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `booking`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `flight`
--

CREATE TABLE `flight` (
  `flight_code` varchar(255) NOT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `flight_date` date DEFAULT NULL,
  `max_passengers` int(11) NOT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `seat_price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `flight`
--

INSERT INTO `flight` (`flight_code`, `destination`, `flight_date`, `max_passengers`, `origin`, `seat_price`) VALUES
('BAMI-1235', 'Madrid', '2024-02-20', 23, 'Teruel', 200),
('FLY11122', 'CityB', '2024-02-20', 100, 'CityA', 200),
('MIMA-1420', 'Madrid', '2024-02-10', 100, 'Miami', 649.22);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `flight_booking`
--

CREATE TABLE `flight_booking` (
  `id` bigint(20) NOT NULL,
  `flight_date` date DEFAULT NULL,
  `num_persons` int(11) NOT NULL,
  `seat_type` varchar(255) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `flight_flight_code` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `flight_booking`
--

INSERT INTO `flight_booking` (`id`, `flight_date`, `num_persons`, `seat_type`, `total_price`, `flight_flight_code`) VALUES
(1, '2024-03-05', 2, 'FirstClass', 250, 'BAMI-1235'),
(2, '2024-03-05', 1, 'Economy', 200, 'FLY11122'),
(4, '2024-03-05', 2, 'Economy', 400, 'BAMI-1235');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hotel`
--

CREATE TABLE `hotel` (
  `hotel_code` varchar(255) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `hotel_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `hotel`
--

INSERT INTO `hotel` (`hotel_code`, `city`, `hotel_name`) VALUES
('H001', 'Ciudad Ejemplo', 'Hotel Ejemplo'),
('H005', 'Teruel', 'Hotel Teruel'),
('H123', 'Ciudad Ejemplo', 'Ejemplo Hotel');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `person`
--

CREATE TABLE `person` (
  `dni` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `person`
--

INSERT INTO `person` (`dni`, `email`, `last_name`, `name`) VALUES
('12345678A', 'nuevoemail@example.com', 'NuevoApellido', 'NuevoNombre');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `person_flight_booking`
--

CREATE TABLE `person_flight_booking` (
  `flight_booking_id` bigint(20) NOT NULL,
  `person_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `person_flight_booking`
--

INSERT INTO `person_flight_booking` (`flight_booking_id`, `person_id`) VALUES
(1, '12345678A'),
(2, '12345678A'),
(4, '12345678A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `person_room_booking`
--

CREATE TABLE `person_room_booking` (
  `person_id` bigint(20) NOT NULL,
  `room_booking_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `person_room_booking`
--

INSERT INTO `person_room_booking` (`person_id`, `room_booking_id`) VALUES
(1, '12345678A'),
(2, '12345678A'),
(4, '12345678A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `room`
--

CREATE TABLE `room` (
  `room_code` varchar(255) NOT NULL,
  `available_from` date DEFAULT NULL,
  `available_room` bit(1) DEFAULT NULL,
  `available_until` date DEFAULT NULL,
  `num_bed` int(11) NOT NULL,
  `price_per_night` double DEFAULT NULL,
  `hotel_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `room`
--

INSERT INTO `room` (`room_code`, `available_from`, `available_room`, `available_until`, `num_bed`, `price_per_night`, `hotel_id`) VALUES
('R001', '2024-02-15', b'1', '2024-03-15', 2, 100, 'H001'),
('R002', '2024-02-20', b'1', '2024-03-20', 1, 80, 'H001'),
('R0033', '2024-02-15', b'1', '2024-03-15', 2, 100, 'H005'),
('R0034', '2024-02-20', b'0', '2024-03-20', 1, 80, 'H005');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `room_booking`
--

CREATE TABLE `room_booking` (
  `id` bigint(20) NOT NULL,
  `num_persons` int(11) NOT NULL,
  `room_price` double DEFAULT NULL,
  `stay_from` date DEFAULT NULL,
  `stay_until` date DEFAULT NULL,
  `room_room_code` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `room_booking`
--

INSERT INTO `room_booking` (`id`, `num_persons`, `room_price`, `stay_from`, `stay_until`, `room_room_code`) VALUES
(2, 1, 320, '2024-03-16', '2024-03-20', 'R002'),
(4, 2, 300, '2024-03-12', '2024-03-15', 'R001');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `flight`
--
ALTER TABLE `flight`
  ADD PRIMARY KEY (`flight_code`);

--
-- Indices de la tabla `flight_booking`
--
ALTER TABLE `flight_booking`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn9d0vm27kabrrbhw3wu91grux` (`flight_flight_code`);

--
-- Indices de la tabla `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`hotel_code`);

--
-- Indices de la tabla `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`dni`);

--
-- Indices de la tabla `person_flight_booking`
--
ALTER TABLE `person_flight_booking`
  ADD KEY `FKntpnt1whj99aaahksije5cfa5` (`person_id`),
  ADD KEY `FK3ft08hg19j4x7nndthr6q14ip` (`flight_booking_id`);

--
-- Indices de la tabla `person_room_booking`
--
ALTER TABLE `person_room_booking`
  ADD KEY `FKe56q9mdyyoqdnouype3nb41lg` (`room_booking_id`),
  ADD KEY `FKr20s0y8qq0uvh4wa3mnrwciw6` (`person_id`);

--
-- Indices de la tabla `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`room_code`),
  ADD KEY `FKdosq3ww4h9m2osim6o0lugng8` (`hotel_id`);

--
-- Indices de la tabla `room_booking`
--
ALTER TABLE `room_booking`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKld3f0t3rdtux5xyo7xat7jr7o` (`room_room_code`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `flight_booking`
--
ALTER TABLE `flight_booking`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `room_booking`
--
ALTER TABLE `room_booking`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `flight_booking`
--
ALTER TABLE `flight_booking`
  ADD CONSTRAINT `FKn9d0vm27kabrrbhw3wu91grux` FOREIGN KEY (`flight_flight_code`) REFERENCES `flight` (`flight_code`);

--
-- Filtros para la tabla `person_flight_booking`
--
ALTER TABLE `person_flight_booking`
  ADD CONSTRAINT `FK3ft08hg19j4x7nndthr6q14ip` FOREIGN KEY (`flight_booking_id`) REFERENCES `flight_booking` (`id`),
  ADD CONSTRAINT `FKntpnt1whj99aaahksije5cfa5` FOREIGN KEY (`person_id`) REFERENCES `person` (`dni`);

--
-- Filtros para la tabla `person_room_booking`
--
ALTER TABLE `person_room_booking`
  ADD CONSTRAINT `FKe56q9mdyyoqdnouype3nb41lg` FOREIGN KEY (`room_booking_id`) REFERENCES `person` (`dni`),
  ADD CONSTRAINT `FKr20s0y8qq0uvh4wa3mnrwciw6` FOREIGN KEY (`person_id`) REFERENCES `room_booking` (`id`);

--
-- Filtros para la tabla `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `FKdosq3ww4h9m2osim6o0lugng8` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_code`);

--
-- Filtros para la tabla `room_booking`
--
ALTER TABLE `room_booking`
  ADD CONSTRAINT `FKld3f0t3rdtux5xyo7xat7jr7o` FOREIGN KEY (`room_room_code`) REFERENCES `room` (`room_code`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
