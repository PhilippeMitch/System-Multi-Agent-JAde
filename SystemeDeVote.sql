-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 07, 2019 at 01:16 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.1.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `JedeonSystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `Candidat_Election`
--

CREATE TABLE `Candidat_Election` (
  `NumeroSocialCandidat` bigint(15) NOT NULL,
  `dateElection` varchar(15) NOT NULL,
  `nombreVoix` int(15) NOT NULL,
  `PartiCandidat` varchar(30) NOT NULL,
  `PosteCandidat` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Candidat_Election`
--

INSERT INTO `Candidat_Election` (`NumeroSocialCandidat`, `dateElection`, `nombreVoix`, `PartiCandidat`, `PosteCandidat`) VALUES
(1874227643, '2019-02-18', 0, 'Republicain', 'Senateur'),
(1874927643, '2019-02-20', 0, 'Democrate', 'President'),
(1874927645, '2019-02-18', 0, 'Democrate', 'Senateur'),
(9076302734, '2019-02-20', 0, 'Republicain', 'President');

-- --------------------------------------------------------

--
-- Table structure for table `Election`
--

CREATE TABLE `Election` (
  `id` int(15) NOT NULL,
  `date` varchar(15) NOT NULL,
  `type` varchar(15) NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Election`
--

INSERT INTO `Election` (`id`, `date`, `type`, `status`) VALUES
(13738105, '2019-02-20', 'En cours', 'Sénatoriales'),
(83367382, '2019-02-18', 'En cours', 'Présidentielle');

-- --------------------------------------------------------

--
-- Table structure for table `Personne`
--

CREATE TABLE `Personne` (
  `NumeroSocial` bigint(15) NOT NULL,
  `Nom` varchar(30) NOT NULL,
  `Prenom` varchar(30) NOT NULL,
  `Sexe` varchar(20) NOT NULL,
  `Date` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Personne`
--

INSERT INTO `Personne` (`NumeroSocial`, `Nom`, `Prenom`, `Sexe`, `Date`) VALUES
(1874927643, 'Brown', 'Janette', 'Feminin', '1960-03-10'),
(3880269643, 'Pierre', 'Joseph', 'Masculin', '1965-01-21'),
(9076302734, 'Philippe', 'Mc Clain', 'Masculin', '1970-01-01'),
(9370973531, 'Jhon', 'Marckey', 'Masculin', '1970-03-20');

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `IdUser` varchar(15) NOT NULL,
  `PassUser` varchar(30) NOT NULL,
  `IsActive` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`IdUser`, `PassUser`, `IsActive`) VALUES
('admin', 'admin', 1),
('H3J-1234', 'jesus', 1),
('jesus', 'jesus', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Candidat_Election`
--
ALTER TABLE `Candidat_Election`
  ADD PRIMARY KEY (`NumeroSocialCandidat`);

--
-- Indexes for table `Election`
--
ALTER TABLE `Election`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Personne`
--
ALTER TABLE `Personne`
  ADD PRIMARY KEY (`NumeroSocial`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`IdUser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
