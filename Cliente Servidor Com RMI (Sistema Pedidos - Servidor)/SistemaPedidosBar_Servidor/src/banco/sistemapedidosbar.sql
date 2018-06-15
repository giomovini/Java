-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 15-Jun-2018 às 04:59
-- Versão do servidor: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sistemapedidosbar`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `ID_CLIENTE` int(11) NOT NULL,
  `NOME` varchar(100) NOT NULL,
  `RG` varchar(20) NOT NULL,
  `CPF` varchar(11) NOT NULL,
  `TELEFONE` varchar(11) NOT NULL,
  `SENHA` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`ID_CLIENTE`, `NOME`, `RG`, `CPF`, `TELEFONE`, `SENHA`) VALUES
(1, 'Henry', '12345', '332', '847524', '332'),
(3, 'Joao', '123123', '22558', '123123', '1231237'),
(4, 'Lucas', '3324', '3321', '12323', '3321'),
(5, 'Leonardo', '1111', '11111111111', '1111', '1111'),
(6, 'Teste', '55555555555', '55555555555', '555', '12345'),
(7, 'Enzo', '12312312312', '12312312312', '12312312312', '123'),
(8, 'Leonardo Silva', '012345678912', '01234567891', '1231313213', '01234567891'),
(9, 'Teste Teste', '23423423423', '23423423423', '12313131', '23423423423');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `ID_FUNCIONARIO` int(11) NOT NULL,
  `NOME` varchar(100) NOT NULL,
  `RG` varchar(20) NOT NULL,
  `CPF` varchar(11) NOT NULL,
  `SENHA` varchar(16) NOT NULL,
  `SETOR` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `funcionario`
--

INSERT INTO `funcionario` (`ID_FUNCIONARIO`, `NOME`, `RG`, `CPF`, `SENHA`, `SETOR`) VALUES
(1, 'Henry', '1012443', '01515396266', 'henry', 'Atendente'),
(2, 'Leonardo', '223223', '01515396265', '22234', 'Atendente');

-- --------------------------------------------------------

--
-- Estrutura da tabela `item_pedido`
--

CREATE TABLE `item_pedido` (
  `ID_ITEM_PEDIDO` int(11) NOT NULL,
  `ID_PRODUTO` int(11) NOT NULL,
  `ID_PEDIDO` int(11) NOT NULL,
  `QUANTIDADE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `item_pedido`
--

INSERT INTO `item_pedido` (`ID_ITEM_PEDIDO`, `ID_PRODUTO`, `ID_PEDIDO`, `QUANTIDADE`) VALUES
(3, 10, 3, 1),
(4, 18, 3, 2),
(5, 20, 3, 5),
(6, 23, 3, 1),
(7, 8, 6, 1),
(8, 36, 6, 2),
(9, 14, 6, 1),
(10, 8, 7, 2),
(11, 12, 7, 1),
(12, 23, 7, 3),
(13, 33, 7, 1),
(14, 20, 3, 1),
(15, 13, 8, 2),
(16, 21, 8, 3),
(17, 31, 8, 1),
(18, 7, 9, 2),
(19, 12, 9, 2),
(20, 10, 10, 1),
(21, 19, 10, 3),
(22, 33, 10, 2),
(23, 14, 11, 5),
(24, 12, 11, 5),
(25, 7, 11, 2),
(26, 11, 12, 5),
(27, 19, 12, 2),
(28, 23, 12, 5),
(29, 6, 13, 3),
(30, 11, 13, 2),
(31, 30, 13, 2),
(32, 32, 13, 2),
(33, 9, 13, 1),
(34, 18, 14, 4),
(35, 23, 14, 4),
(36, 6, 14, 1),
(37, 7, 14, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedido`
--

CREATE TABLE `pedido` (
  `ID_PEDIDO` int(11) NOT NULL,
  `ID_CLIENTE` int(11) NOT NULL,
  `DATA` date NOT NULL,
  `MESA` varchar(100) NOT NULL,
  `FINALIZADO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pedido`
--

INSERT INTO `pedido` (`ID_PEDIDO`, `ID_CLIENTE`, `DATA`, `MESA`, `FINALIZADO`) VALUES
(3, 3, '2018-05-22', '2', 1),
(6, 1, '2018-05-25', '23', 1),
(7, 5, '2018-05-29', '13', 1),
(8, 3, '2018-05-29', '44', 1),
(9, 6, '2018-06-05', '13', 0),
(10, 7, '2018-06-06', '34', 0),
(11, 3, '2018-06-07', '55', 1),
(12, 8, '2018-06-11', '44', 1),
(13, 9, '2018-06-12', '34', 1),
(14, 9, '2018-06-12', '55', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `ID_PRODUTO` int(11) NOT NULL,
  `NOME` varchar(45) NOT NULL,
  `DESCRICAO` varchar(100) NOT NULL,
  `PRECO` double NOT NULL,
  `VALIDADE` date DEFAULT NULL,
  `QUANTIDADE` int(11) NOT NULL,
  `TIPO` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`ID_PRODUTO`, `NOME`, `DESCRICAO`, `PRECO`, `VALIDADE`, `QUANTIDADE`, `TIPO`) VALUES
(6, 'Sweet Blue', 'Coquetel Alcoólico Sweet Blue (pinga Azul) 920ml', 50, '2025-05-22', 7, 'Bebida'),
(7, 'Baileys', 'Licor Irlandês Baileys Garrafa 750ml', 130, '2022-05-22', 9, 'Bebida'),
(8, 'Jack Daniels', 'Whiskys Importado Jack Daniels 375ml', 45, '2030-10-22', 77, 'Bebida'),
(9, 'Red Label', 'Whisky Escocês Red Label 8 Anos Garrafa 500ml - Johnnie Walker', 800, '2028-10-12', 9, 'Bebida'),
(10, 'Dalmore', 'Whisky Dalmore 18 anos 700ml', 1000, '2028-10-12', 13, 'Bebida'),
(11, 'Velho Barreiro ', 'Aguardente Velho Barreiro 910ml', 25, '2023-10-12', 181, 'Bebida'),
(12, 'Frango a Passarinho', 'Frango em pedaços empanados com pedaços de calabresa. (500g) ', 18, '2018-06-21', 93, 'Porção'),
(13, 'Dois Queijos à Milanesa', 'Queijos empanados: Provolone e meia cura. 300g', 22, '2018-06-21', 33, 'Porção'),
(14, 'Bolinho de Bacalhau', 'Bacalhau cortado em pedaços empanado. 500g', 30, '2018-07-21', 29, 'Porção'),
(15, 'Batata Frita', 'Batatas fritas 300g', 14, '2018-04-21', 100, 'Porção'),
(16, 'Batata Frita com Bacon', 'Batatas fritas com pedaços de bacon 400g', 18, '2017-03-28', 100, 'Porção'),
(17, 'Calabresa acebolada', 'Fatitas de calabresa fritas com cebola 400g', 25, '2018-07-28', 100, 'Porção'),
(18, 'Torresmo', 'Torresmo frito 200g', 15, '2018-07-11', 44, 'Porção'),
(19, 'Polenta Frita', 'Polenta cortadas em cubos frita 400g', 22, '2018-07-11', 95, 'Porção'),
(20, 'Picolé', 'Picolés de diversos sabores. Unidade', 3.5, '2019-07-18', 194, 'Doce'),
(21, 'Bolo de chocolate', 'Bolo de chocolate com cobertura 50g', 4, '2018-07-18', 17, 'Doce'),
(22, 'Pudim', 'Pudim 50g', 3, '2018-07-18', 30, 'Doce'),
(23, 'Brownie de chocolate', 'Brownie de chocolate 30g', 2.5, '2018-07-18', 37, 'Doce'),
(24, 'Crepe', 'Crepe de chocolate', 5, '2018-07-17', 20, 'Doce'),
(25, 'Crepe', 'Crepe de presunto e queijo', 5, '2018-07-17', 100, 'Lanche'),
(26, 'Crepe', 'Crepe de doce de leite', 5, '2018-07-18', 20, 'Doce'),
(27, 'Crepe', 'Crepe de chocolate com morango', 5, '2018-07-18', 20, 'Doce'),
(28, 'Crepe', 'Crepe de nutella', 5, '2018-07-18', 20, 'Doce'),
(29, 'Crepe', 'Crepe de queijo', 5, '2018-07-18', 20, 'Lanche'),
(30, 'Crepe', 'Crepe de frango', 5, '2018-07-18', 18, 'Lanche'),
(31, 'Crepe', 'Crepe de carne', 5, '2018-07-18', 19, 'Lanche'),
(32, 'Pastel', 'Carne', 5, '2018-07-25', 18, 'Lanche'),
(33, 'Pastel', 'Frango com catupiri', 5, '2018-07-25', 17, 'Lanche'),
(35, 'X-Bacon', 'Hambúrguer, queijo, bacon, tomate, alface', 10, '2018-07-25', 20, 'Lanche'),
(36, 'X-Bacon', 'hambúrguer, queijo, milho,cebola, tomate, alface', 10, '2018-07-25', 18, 'Lanche');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`ID_CLIENTE`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`ID_FUNCIONARIO`);

--
-- Indexes for table `item_pedido`
--
ALTER TABLE `item_pedido`
  ADD PRIMARY KEY (`ID_ITEM_PEDIDO`);

--
-- Indexes for table `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`ID_PEDIDO`);

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`ID_PRODUTO`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `ID_CLIENTE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `ID_FUNCIONARIO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `item_pedido`
--
ALTER TABLE `item_pedido`
  MODIFY `ID_ITEM_PEDIDO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT for table `pedido`
--
ALTER TABLE `pedido`
  MODIFY `ID_PEDIDO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `ID_PRODUTO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
