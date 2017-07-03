-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 03-Jul-2017 às 05:00
-- Versão do servidor: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bdsgc`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `bancos`
--

CREATE TABLE `bancos` (
  `codBanco` bigint(20) UNSIGNED NOT NULL,
  `nroBanco` varchar(20) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `webPage` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `bancos`
--

INSERT INTO `bancos` (`codBanco`, `nroBanco`, `nome`, `webPage`) VALUES
(1, 'fd', '', '');

-- --------------------------------------------------------

--
-- Estrutura da tabela `pessoa`
--

CREATE TABLE `pessoa` (
  `codPessoa` int(11) UNSIGNED NOT NULL,
  `nome` varchar(100) NOT NULL,
  `codCidade` int(11) DEFAULT NULL,
  `codTipoPessoa` int(11) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pessoa`
--

INSERT INTO `pessoa` (`codPessoa`, `nome`, `codCidade`, `codTipoPessoa`, `telefone`, `email`) VALUES
(9, 'ffffffffff', 2, 3, '15562125', '6neimarfds'),
(10, 'ffffffffffff', 12, 32, '5161156', 'sfsdbbb@sfsd'),
(11, 'fsd', 4, 1, '1fdf', 'dfd'),
(12, 'fsdfds', 2, 3, '11dfds21321', 'dfsdfsdfs1d2f2sd'),
(13, 'sdfsdf', 5, 5, 'dfs', '1');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipoutilitario`
--

CREATE TABLE `tipoutilitario` (
  `codTipoUtilitario` int(20) UNSIGNED NOT NULL,
  `descTipoUtilitario` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tipoutilitario`
--

INSERT INTO `tipoutilitario` (`codTipoUtilitario`, `descTipoUtilitario`) VALUES
(1, 'Assunto'),
(2, 'Genero'),
(3, 'Autor'),
(4, 'Editora'),
(7, 'Tipo pessoa');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `nome`, `email`, `username`) VALUES
(4, 'neimar moises gobbi', 'neimarmg@hotmail.com', 'neimar'),
(5, 'Lorenzo ', 'lori@gmail.com', 'lorinho'),
(6, 'Aurelio', 'au@gmail.com	', 'aureliosanche');

-- --------------------------------------------------------

--
-- Estrutura da tabela `utilitarios`
--

CREATE TABLE `utilitarios` (
  `codUtilitario` int(11) NOT NULL,
  `utilitario` varchar(255) NOT NULL,
  `codTipoUtilirario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `utilitarios`
--

INSERT INTO `utilitarios` (`codUtilitario`, `utilitario`, `codTipoUtilirario`) VALUES
(0, 'das', 1),
(1, 'Direito Civil', 1),
(2, 'Direito Trabalho', 1),
(3, 'Filozofia', 1),
(4, 'Administração', 1),
(5, 'Direito penal', 1),
(6, 'Literatura', 2),
(7, 'Martim claret', 4),
(8, 'Abril', 4),
(9, 'Leya', 4),
(10, 'Ficção', 2),
(11, 'Terror', 2),
(12, 'Direito', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bancos`
--
ALTER TABLE `bancos`
  ADD PRIMARY KEY (`codBanco`),
  ADD UNIQUE KEY `codBanco` (`codBanco`);

--
-- Indexes for table `pessoa`
--
ALTER TABLE `pessoa`
  ADD PRIMARY KEY (`codPessoa`),
  ADD UNIQUE KEY `codPessoa` (`codPessoa`);

--
-- Indexes for table `tipoutilitario`
--
ALTER TABLE `tipoutilitario`
  ADD PRIMARY KEY (`codTipoUtilitario`),
  ADD UNIQUE KEY `codTipoUtilitario` (`codTipoUtilitario`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `utilitarios`
--
ALTER TABLE `utilitarios`
  ADD PRIMARY KEY (`codUtilitario`),
  ADD UNIQUE KEY `codUtilitario` (`codUtilitario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bancos`
--
ALTER TABLE `bancos`
  MODIFY `codBanco` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `pessoa`
--
ALTER TABLE `pessoa`
  MODIFY `codPessoa` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
