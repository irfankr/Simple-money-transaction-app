-- phpMyAdmin SQL Dump
-- version 4.5.0.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 13, 2016 at 04:37 AM
-- Server version: 10.0.17-MariaDB
-- PHP Version: 5.5.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ssmkdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE `clients` (
  `Id` int(11) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Money` float DEFAULT NULL,
  `Mobile` int(11) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` VALUES(1, 'irfank', 'admin', 5825, 61506652, 'irfankr91@gmail.com');
INSERT INTO `clients` VALUES(213151, 'armin', 'sigra213', 325, 52351561, 'korisnikovmail@mail.com');
INSERT INTO `clients` VALUES(2315156, 'faris', 'faretovasifra', 555, 3151456, 'faremail@gmail.com');
INSERT INTO `clients` VALUES(511455454, 'galibb', 'galib91', 250, 25665165, 'ghadziemri1@etf.unsa.ba');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `Id` int(11) NOT NULL,
  `User_id` int(11) NOT NULL,
  `Status` text NOT NULL,
  `Description` text NOT NULL,
  `Code` int(11) DEFAULT NULL,
  `Reciever_User_Id` int(11) DEFAULT NULL,
  `Money` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` VALUES(16, 1, 'Uspjesno', 'Uplata', 10457040, 0, 200);
INSERT INTO `transaction` VALUES(17, 1, 'Uspjesno', 'Isplata', 6725891, 0, 350);
INSERT INTO `transaction` VALUES(18, 1, 'Neuspjesno', 'Isplata', 7820437, 0, 35000);
INSERT INTO `transaction` VALUES(19, 1, 'Uspjesno', 'Transfer', 3982863, 0, 350);
INSERT INTO `transaction` VALUES(20, 1, 'Uspjesno', 'Isplata', 3006157, 0, 200);
INSERT INTO `transaction` VALUES(21, 1, 'Uspjesno', 'Uplata', 9286033, 0, 77);
INSERT INTO `transaction` VALUES(22, 1, 'Uspjesno', 'Uplata', 8988018, 0, 77);
INSERT INTO `transaction` VALUES(23, 1, 'Uspjesno', 'Uplata', 1150015, 0, 77);
INSERT INTO `transaction` VALUES(24, 1, 'Uspjesno', 'Uplata', 10431642, 0, 9);
INSERT INTO `transaction` VALUES(25, 1, 'Uspjesno', 'Uplata', 10896029, 0, 30);
INSERT INTO `transaction` VALUES(26, 1, 'Uspjesno', 'Uplata', 3032044, 0, 30);
INSERT INTO `transaction` VALUES(27, 1, 'Uspjesno', 'Isplata', 6839214, 0, 100);
INSERT INTO `transaction` VALUES(29, 1, 'Neuspjesno', 'Isplata', 2687072, 0, 5000);
INSERT INTO `transaction` VALUES(30, 1, 'Uspjesno', 'Uplata', 4732409, 0, 20);
INSERT INTO `transaction` VALUES(31, 1, 'Uspjesno', 'Transfer', 4254780, 0, 10);
INSERT INTO `transaction` VALUES(34, 1, 'Uspjesno', 'Uplata', 3550849, 0, 70);
INSERT INTO `transaction` VALUES(35, 1, 'Uspjesno', 'Uplata', 1572989, 0, 60);
INSERT INTO `transaction` VALUES(36, 1, 'Uspjesno', 'Uplata', 3977698, 0, 66);
INSERT INTO `transaction` VALUES(37, 1, 'Uspjesno', 'Uplata', 5621361, 0, 33);
INSERT INTO `transaction` VALUES(38, 1, 'Uspjesno', 'Uplata', 7837023, 0, 40);
INSERT INTO `transaction` VALUES(39, 1, 'Uspjesno', 'Uplata', 6007177, 0, 40);
INSERT INTO `transaction` VALUES(40, 1, 'Uspjesno', 'Uplata', 2970457, 0, 40);
INSERT INTO `transaction` VALUES(41, 1, 'Uspjesno', 'Uplata', 10009033, 0, 40);
INSERT INTO `transaction` VALUES(42, 1, 'Uspjesno', 'Uplata', 10542853, 0, 50);
INSERT INTO `transaction` VALUES(43, 1, 'Uspjesno', 'Isplata', 1010960, 0, 30);
INSERT INTO `transaction` VALUES(44, 1, 'Uspjesno', 'Uplata', 10856363, 0, 40);
INSERT INTO `transaction` VALUES(45, 1, 'Uspjesno', 'Uplata', 2585333, 0, 25);
INSERT INTO `transaction` VALUES(46, 1, 'Uspjesno', 'Uplata', 4121106, 0, 25);
INSERT INTO `transaction` VALUES(47, 1, 'Uspjesno', 'Uplata', 8163076, 0, 70);
INSERT INTO `transaction` VALUES(48, 1, 'Uspjesno', 'Transfer', 4916892, 4, 33);
INSERT INTO `transaction` VALUES(49, 1, 'Uspjesno', 'Uplata', 4715757, 0, 44);
INSERT INTO `transaction` VALUES(50, 1, 'Uspjesno', 'Uplata', 1810772, 0, 44);
INSERT INTO `transaction` VALUES(51, 1, 'Uspjesno', 'Uplata', 8591754, 0, 20);
INSERT INTO `transaction` VALUES(52, 1, 'Uspjesno', 'Uplata', 2608103, 0, 33);
INSERT INTO `transaction` VALUES(53, 1, 'Uspjesno', 'Uplata', 4712496, 0, 27);
INSERT INTO `transaction` VALUES(54, 1, 'Uspjesno', 'Uplata', 1085129, 0, 11);
INSERT INTO `transaction` VALUES(55, 1, 'Uspjesno', 'Uplata', 10021149, 0, 11);
INSERT INTO `transaction` VALUES(56, 1, 'Uspjesno', 'Uplata', 10150225, 0, 33);
INSERT INTO `transaction` VALUES(57, 1, 'Uspjesno', 'Uplata', 1837379, 0, 11);
INSERT INTO `transaction` VALUES(58, 1, 'Uspjesno', 'Uplata', 1291404, 0, 12);
INSERT INTO `transaction` VALUES(59, 213151, 'Uspjesno', 'Uplata', 6205827, 0, 125);
INSERT INTO `transaction` VALUES(60, 1, 'Uspjesno', 'Uplata', 6858802, 0, 33);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clients`
--
ALTER TABLE `clients`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=623566232;
--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
