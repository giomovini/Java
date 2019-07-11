-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 11-Jul-2019 às 00:26
-- Versão do servidor: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sistema_bibliotecario_aplicacao`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `autor`
--

CREATE TABLE `autor` (
  `ID_AUTOR` int(11) NOT NULL,
  `AUTOR` varchar(300) NOT NULL,
  `WEBSITE` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `autor`
--

INSERT INTO `autor` (`ID_AUTOR`, `AUTOR`, `WEBSITE`) VALUES
(3, 'J. K. Rowling', 'https://www.jkrowling.com/'),
(4, 'J. K. Rowling', 'https://www.jkrowling.com/'),
(5, 'J.K Rowling', 'https://www.jkrowling.com/'),
(6, 'J. K. Rowling', 'https://www.jkrowling.com/'),
(7, 'J. K. Rowling', 'https://www.jkrowling.com/');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cartao_acesso`
--

CREATE TABLE `cartao_acesso` (
  `IDENTIFICADOR` int(11) NOT NULL,
  `COD_BARRAS` varchar(200) NOT NULL,
  `VALIDADE` date NOT NULL,
  `ID_LOCATARIO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cartao_acesso`
--

INSERT INTO `cartao_acesso` (`IDENTIFICADOR`, `COD_BARRAS`, `VALIDADE`, `ID_LOCATARIO`) VALUES
(1, '3131231121212312', '2020-02-06', 10);

-- --------------------------------------------------------

--
-- Estrutura da tabela `exemplar`
--

CREATE TABLE `exemplar` (
  `ID_EXEMPLAR` int(11) NOT NULL,
  `ID_AUTOR` int(11) NOT NULL,
  `TITULO` varchar(200) NOT NULL,
  `IDIOMA` varchar(100) NOT NULL,
  `EDICAO` varchar(5) NOT NULL,
  `ANO_LANC` varchar(4) NOT NULL,
  `QTDE_DISP` int(11) NOT NULL,
  `CATEGORIA` varchar(100) NOT NULL,
  `CLASSIFICACAO` varchar(10) NOT NULL,
  `LOCALIZACAO` varchar(100) NOT NULL,
  `COD_BARRAS` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `exemplar`
--

INSERT INTO `exemplar` (`ID_EXEMPLAR`, `ID_AUTOR`, `TITULO`, `IDIOMA`, `EDICAO`, `ANO_LANC`, `QTDE_DISP`, `CATEGORIA`, `CLASSIFICACAO`, `LOCALIZACAO`, `COD_BARRAS`) VALUES
(3, 3, 'Harry Potter e a câmara secreta', 'Português', '1', '2000', 4, 'Livro', '1', 'A1', '73225509'),
(4, 4, 'A ciência de Harry Potter: como a mágica realmente funciona', 'Português', '2', '2002', 7, 'Livro', '1', 'A2', '73312118'),
(5, 5, 'Harry Potter e a pedra filosofal', 'Português', '1', '2000', 5, 'Livro', '1', 'A3', '73347826'),
(6, 6, 'Harry Potter e as relíquias da morte', 'Português', '1', '2007', 6, 'Livro', '1', 'A4', '76602223'),
(7, 7, 'Harry Potter e a criança amaldiçoada', 'Português', '1', '2016', 7, 'Livro', '1', 'A6', '72056627');

-- --------------------------------------------------------

--
-- Estrutura da tabela `locacao`
--

CREATE TABLE `locacao` (
  `ID_LOCACAO` int(11) NOT NULL,
  `ID_EXEMPLAR` int(11) NOT NULL,
  `ID_LOCATARIO` int(11) NOT NULL,
  `DATALOCACAO` date NOT NULL,
  `PRAZO_DEVOLUCAO` date NOT NULL,
  `DATA_DEVOLUCAO` date DEFAULT NULL,
  `STATUS` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `locacao`
--

INSERT INTO `locacao` (`ID_LOCACAO`, `ID_EXEMPLAR`, `ID_LOCATARIO`, `DATALOCACAO`, `PRAZO_DEVOLUCAO`, `DATA_DEVOLUCAO`, `STATUS`) VALUES
(2, 6, 10, '2019-05-19', '2019-05-20', '2019-06-09', 'DEVOLVIDO'),
(3, 7, 14, '2019-05-19', '2019-06-10', NULL, 'LOCADO'),
(4, 4, 10, '2019-05-19', '2019-05-21', '2019-05-19', 'DEVOLVIDO');

-- --------------------------------------------------------

--
-- Estrutura da tabela `locatario`
--

CREATE TABLE `locatario` (
  `ID_LOCATARIO` int(11) NOT NULL,
  `LOCATARIO` varchar(200) NOT NULL,
  `TELEFONE` varchar(20) NOT NULL,
  `CPF` varchar(20) NOT NULL,
  `NASCIMENTO` date NOT NULL,
  `SEXO` varchar(1) NOT NULL,
  `EMAIL` varchar(200) NOT NULL,
  `CIDADE` varchar(200) NOT NULL,
  `ESTADO` varchar(200) NOT NULL,
  `ENDERECO` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `locatario`
--

INSERT INTO `locatario` (`ID_LOCATARIO`, `LOCATARIO`, `TELEFONE`, `CPF`, `NASCIMENTO`, `SEXO`, `EMAIL`, `CIDADE`, `ESTADO`, `ENDERECO`) VALUES
(6, 'Maria da Silva', '(44)7894-5147', '078.048.055-89', '1977-04-01', '', 'dasilvamaria@gmail.com', 'Ponta Grossa', 'PR', 'Rua Princesa Isabel 123, Centro'),
(7, 'Jaqueline França', '(47)9748-2548', '018.472.547-22', '1999-01-15', 'F', 'jaquelinef.ranca@gmail.com', 'Curitiba', 'PR', 'Avenia Rio Claro 123, Monte Claro'),
(8, 'Josias de Carvalho', '(41)8798-1578', '142.327.597-88', '2001-02-27', 'M', 'carvalho.josias1@gmail.com', 'São Paulo', 'SP', 'Rua Mato Grosso 333, Cristo Rei'),
(9, 'Rafael Kutchma', '(42)8847-5791', '005.754.058-24', '1984-08-26', 'M', 'rafaelkutchma@gmail.com', 'Rio de Janeiro', 'RJ', 'Av. Flores 123, Penha'),
(10, 'Caio Góis', '(51)3374-0971', '005.754.058-24', '1973-05-13', 'M', 'goiscaio@gmail.com', 'Ponta Grossa', 'PR', 'Rua São João 333, Santa Rita'),
(11, 'Giovanna Valentin', '(21)9428-1403', '678.506.830-48', '1996-01-31', 'F', 'g.valentin@gmail.com', 'Joinville', 'SC', 'Rua Henrique 123, Centro'),
(12, 'Fernando Kurt', '(11)8450-3674', '039.048.320-69', '1979-06-22', 'M', 'fernandokurt@gmail.com', 'Carambei', 'PR', 'Avenida Matinhos 123, Santa Monica'),
(13, 'Shayame Silveira', '(27)9874-2160', '732.003.250-38', '1995-12-04', 'F', 'shayame.s@gmail.com', 'Campo Largo', 'PR', 'Avenida Monteiro Lobato 222, Santa Monica'),
(14, 'Natanael Hilden', '(69)8848-5030', '174.007.960-46', '1955-11-09', 'M', 'hildennatanael@gmail.com', 'Curitiba', 'PR', 'Rua dos ingleses 122, Jardim Carvalho'),
(15, 'Tainá da Cruz', '(69)8848-5030', '582.438.280-87', '1967-07-19', 'F', 'cruz.taina@gmail.com', 'Curitiba', 'PR', 'Rua Mato Grosso 12, Uvaranas');

-- --------------------------------------------------------

--
-- Estrutura da tabela `multa`
--

CREATE TABLE `multa` (
  `ID_MULTA` int(11) NOT NULL,
  `ID_LOCACAO` int(11) NOT NULL,
  `VALOR` double NOT NULL,
  `PAGAMENTO` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `multa`
--

INSERT INTO `multa` (`ID_MULTA`, `ID_LOCACAO`, `VALOR`, `PAGAMENTO`) VALUES
(1, 4, 7.55, 'QUITADO'),
(3, 3, 12, 'PENDENTE'),
(4, 4, 0, 'QUITADO'),
(5, 4, 12, 'PENDENTE');

-- --------------------------------------------------------

--
-- Estrutura da tabela `reserva`
--

CREATE TABLE `reserva` (
  `IDENTIFICADOR` int(11) NOT NULL,
  `ID_EXEMPLAR` int(11) NOT NULL,
  `ID_LOCATARIO` int(11) NOT NULL,
  `DATA_RESERVA` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `reserva`
--

INSERT INTO `reserva` (`IDENTIFICADOR`, `ID_EXEMPLAR`, `ID_LOCATARIO`, `DATA_RESERVA`) VALUES
(2, 5, 11, '2019-07-06'),
(3, 4, 11, '2019-07-06'),
(4, 4, 8, '2019-07-06'),
(5, 6, 13, '2019-07-06');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`ID_AUTOR`);

--
-- Indexes for table `cartao_acesso`
--
ALTER TABLE `cartao_acesso`
  ADD PRIMARY KEY (`IDENTIFICADOR`);

--
-- Indexes for table `exemplar`
--
ALTER TABLE `exemplar`
  ADD PRIMARY KEY (`ID_EXEMPLAR`);

--
-- Indexes for table `locacao`
--
ALTER TABLE `locacao`
  ADD PRIMARY KEY (`ID_LOCACAO`);

--
-- Indexes for table `locatario`
--
ALTER TABLE `locatario`
  ADD PRIMARY KEY (`ID_LOCATARIO`);

--
-- Indexes for table `multa`
--
ALTER TABLE `multa`
  ADD PRIMARY KEY (`ID_MULTA`);

--
-- Indexes for table `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`IDENTIFICADOR`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `autor`
--
ALTER TABLE `autor`
  MODIFY `ID_AUTOR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `cartao_acesso`
--
ALTER TABLE `cartao_acesso`
  MODIFY `IDENTIFICADOR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `exemplar`
--
ALTER TABLE `exemplar`
  MODIFY `ID_EXEMPLAR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `locacao`
--
ALTER TABLE `locacao`
  MODIFY `ID_LOCACAO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `locatario`
--
ALTER TABLE `locatario`
  MODIFY `ID_LOCATARIO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `multa`
--
ALTER TABLE `multa`
  MODIFY `ID_MULTA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `reserva`
--
ALTER TABLE `reserva`
  MODIFY `IDENTIFICADOR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
