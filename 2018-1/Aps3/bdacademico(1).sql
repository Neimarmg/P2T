-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 02-Maio-2018 às 04:36
-- Versão do servidor: 10.1.26-MariaDB
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bdacademico`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `cMenus` (IN `idTipoMenu` INT(11) UNSIGNED ZEROFILL)  NO SQL
    DETERMINISTIC
SELECT
	 gMenus.idMenu
	,gMenus.nomeMenu
    ,gMenus.idTipoMenu
	,gTipomenu.descTipoMenu
    
FROM gMenus
	LEFT JOIN gTipomenu ON		
    	gMenus.idTipoMenu = gTipomenu.idTipoMenu
        
 where gMenus.idTipoMenu = filtro(gMenus.idTipoMenu, idTipoMenu)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `cPerfis` (IN `idPerfil` INT(11) UNSIGNED ZEROFILL, IN `idAplicacao` INT(11) UNSIGNED ZEROFILL)  NO SQL
SELECT 
	yPerfil.idPerfil
    ,yPerfil.descricaoPerfil
    ,yPerfil.idAplicacao
    ,yPemissoes.idObjeto
    ,yPemissoes.nomeObjeto
    ,yPemissoes.ler
    ,yPemissoes.criar
    ,yPemissoes.atualizar
    ,yPemissoes.deletar
    ,yPemissoes.statusObjeto
    ,gAplicacao.descricao
    ,gAplicacao.simbolo

FROM yPerfil 
 	
    LEFT JOIN yPemissoes ON
      	yPerfil.idPerfil = yPemissoes.idPerfil
          
	LEFT JOIN gAplicacao ON
      	yPerfil.idAplicacao = gAplicacao.idAplicacao

WHERE yPerfil.idPerfil = filtro(yPerfil.idPerfil,idPerfil)
	AND yPerfil.idAplicacao = filtro(yPerfil.idAplicacao,idAplicacao)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `cPessoa` ()  NO SQL
SELECT
  gpessoa.iPessoa
 ,gpessoa.nome
 ,gpessoa.cpf
 ,gpessoa.cpf
 ,gpessoa.RG
 ,gpessoa.cref
 ,gpessoa.Ativa
 ,gpessoa.idTipoPessoa
 ,tipoPessoa.utilitario As tipoPessoa
 ,gpessoa.idProfissao
 ,profissao.utilitario As profissao

FROM gpessoa
	LEFT JOIN gUtilitarios AS tipoPessoa ON
    	gpessoa.idTipoPessoa = tipoPessoa.idUtilitario
	
    JOIN gUtilitarios AS profissao ON
    	gpessoa.idProfissao = profissao.idUtilitario$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `cUtilitarios` (IN `idTipoUtilitarios` INT(11) UNSIGNED ZEROFILL, IN `idSubGrupoUtilitarios` INT(11) UNSIGNED ZEROFILL)  NO SQL
SELECT 
	DISTINCT
	gUtilitarios.idUtilitario
	,gUtilitarios.utilitario
	,gUtilitarios.idTipoUtilirario
	,gUtilitarios.Obs
	,gUtilitarios.favorita
	,gTipoutilitarios.descTipoUtilitario
	,gUtilitarios.idSubGrupo

FROM gUtilitarios
	LEFT JOIN gTipoutilitarios ON
		gUtilitarios.idTipoUtilirario = gTipoutilitarios.idTipoUtilitario
	
    /*LEFT JOIN gUtilitarios as sub ON
		 gUtilitarios.idUtilitario = sub.idSubGrupo*/
            
WHERE gUtilitarios.idTipoUtilirario = filtro(gUtilitarios.idTipoUtilirario, idTipoUtilitarios)
	AND gUtilitarios.idSubGrupo = filtro(gUtilitarios.idSubGrupo,idSubGrupoUtilitarios)

ORDER BY gUtilitarios.utilitario, gTipoutilitarios.descTipoUtilitario ASC$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ObjetosAcessoAtualiza` (IN `tipoObjeto` VARCHAR(20))  NO SQL
UPDATE yObjetos
	LEFT JOIN gAplicacao ON	
    	yObjetos.aplicacao = gAplicacao.simbolo
        
    SET yObjetos.idAplicacao = gAplicacao.idAplicacao

WHERE yObjetos.descTipoObjeto = tipoObjeto$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ObjetosAcessoCria` (IN `tipoObjeto` VARCHAR(20))  NO SQL
BEGIN
    INSERT INTO yObjetos(aplicacao, nomeObjeto,detalhesObjeto,descTipoObjeto)
    SELECT DISTINCT
        UPPER(TABLE_NAME)
        ,TABLE_NAME
        ,null
        ,tipoObjeto

    FROM information_schema.COLUMNS
    WHERE COLUMNS.TABLE_NAME IN (
        SELECT TABLE_NAME 
        FROM information_schema.TABLES 
        WHERE TABLES.ENGINE = 'innoDB');        
 
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ObjetosAcessoCriaPermissoes` (IN `idPerfil` INT UNSIGNED ZEROFILL)  NO SQL
BEGIN 
    DECLARE i INT DEFAULT 0;
	DECLARE m INT DEFAULT 0;
    DECLARE mi INT DEFAULT 0;
    DECLARE a INT DEFAULT 0;
    DECLARE p INT DEFAULT 0;
    
    SET mi = IFNULL((SELECT IFNULL(MIN(yPerfil.idPerfil),0) FROM yPerfil WHERE yPerfil.idPerfil = filtro(yPerfil.idPerfil, idPerfil)),0);
    
	SET m = IFNULL((SELECT IFNULL(MAX(yPerfil.idPerfil),0) FROM yPerfil WHERE yPerfil.idPerfil = filtro(yPerfil.idPerfil, idPerfil)),0);
    
   	SET i = mi;
 
  	d: LOOP
	    SET p = IFNULL((SELECT IFNULL(yPerfil.idPerfil,0) FROM yPerfil WHERE yPerfil.idPerfil = filtro(i, idPerfil)),0);
		SET a = IFNULL((SELECT IFNULL(yPerfil.idAplicacao,0) FROM yPerfil WHERE yPerfil.idPerfil = filtro(i, idPerfil)),0);
        
		CALL ObjetosAcessoRegistraPermissao(p, a); 
       
       if i >= m THEN
            LEAVE d; 	
        END IF;     
        
     SET i = i+1;  
        
	 END LOOP d;
     
    call cPerfis(idPerfil, 0);
     
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ObjetosAcessoRegistraPermissao` (IN `idPerfil` INT(11) UNSIGNED ZEROFILL, IN `idAplicacao` INT(11) UNSIGNED ZEROFILL)  NO SQL
INSERT INTO `yPemissoes`(`idPerfil`, `idAplicacao`, `idObjeto`, `nomeObjeto`, `ler`, `criar`, `atualizar`, `deletar`, `statusObjeto`) 
SELECT 
	idPerfil
    ,yObjetos.idAplicacao
    ,yObjetos.idObjetos
    ,yObjetos.nomeObjeto
    ,'1'
    ,'1'
    ,'1'
    ,'1'
    ,'1'
 
from yObjetos 
WHERE yObjetos.idAplicacao in(SELECT idAplicacao from yPerfil where yPerfil.idPerfil = idPerfil)$$

--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `filtro` (`valorOrigem` VARCHAR(100) CHARSET ascii, `valorFiltro` VARCHAR(100) CHARSET ascii) RETURNS VARCHAR(200) CHARSET ascii NO SQL
    DETERMINISTIC
BEGIN
 	DECLARE f varchar(200);
    
    IF valorFiltro > '0' THEN
        SET f = valorFiltro;

    ELSE 
        SET f = valorOrigem;
    END IF;
 
 RETURN f;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `acurso`
--

CREATE TABLE `acurso` (
  `IDCURSO` bigint(20) NOT NULL,
  `IDMODALIDADE` bigint(20) DEFAULT NULL,
  `IDPROJTOCURSO` bigint(20) DEFAULT NULL,
  `NOMECURSO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `acurso`
--

INSERT INTO `acurso` (`IDCURSO`, `IDMODALIDADE`, `IDPROJTOCURSO`, `NOMECURSO`) VALUES
(1, 5, 4, 'Direito'),
(2, 6, 9, 'Odonto');

-- --------------------------------------------------------

--
-- Estrutura da tabela `adisciplinas`
--

CREATE TABLE `adisciplinas` (
  `IDDISCIPLINA` bigint(20) NOT NULL,
  `IDCURSO` bigint(20) DEFAULT NULL,
  `NOMEDISCIPLINA` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `amodalidadecurso`
--

CREATE TABLE `amodalidadecurso` (
  `IDMODALIDADE` bigint(20) NOT NULL,
  `DESCRICAO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `amodalidadecurso`
--

INSERT INTO `amodalidadecurso` (`IDMODALIDADE`, `DESCRICAO`) VALUES
(1, 'Mestrado'),
(2, 'Doutorado'),
(3, 'Graduação');

-- --------------------------------------------------------

--
-- Estrutura da tabela `gaplicaca`
--

CREATE TABLE `gaplicaca` (
  `IDAPLICACAO` bigint(20) NOT NULL,
  `DESCRICAO` varchar(255) DEFAULT NULL,
  `SIMBOLO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `gfiliais`
--

CREATE TABLE `gfiliais` (
  `IDFILIAL` bigint(20) NOT NULL,
  `CNPJ` varchar(255) DEFAULT NULL,
  `DESCRICAO` varchar(255) DEFAULT NULL,
  `IDMATRIZ` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `gmatriz`
--

CREATE TABLE `gmatriz` (
  `IDMATRIZ` bigint(20) NOT NULL,
  `CNPJ` varchar(255) DEFAULT NULL,
  `DESCRICAO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `gmenus`
--

CREATE TABLE `gmenus` (
  `IDMENU` bigint(20) NOT NULL,
  `IDTIPOMENU` bigint(20) DEFAULT NULL,
  `NOMEMENU` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `gpessoa`
--

CREATE TABLE `gpessoa` (
  `IPESSOA` bigint(20) NOT NULL,
  `ATIVA` tinyint(1) DEFAULT '0',
  `RG` varchar(255) DEFAULT NULL,
  `CPF` varchar(255) DEFAULT NULL,
  `CREF` varchar(255) DEFAULT NULL,
  `IDPROFISSAO` bigint(20) DEFAULT NULL,
  `IDTIPOPESSOA` bigint(20) DEFAULT NULL,
  `NOME` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `gtipomenu`
--

CREATE TABLE `gtipomenu` (
  `IDTIPOMENU` bigint(20) NOT NULL,
  `NOMEMENU` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `gtipoutilitarios`
--

CREATE TABLE `gtipoutilitarios` (
  `IDTIPOMENU` bigint(20) NOT NULL,
  `NOMEMENU` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `gutilitarios`
--

CREATE TABLE `gutilitarios` (
  `IDUTILITARIO` bigint(20) NOT NULL,
  `OBS` varchar(255) DEFAULT NULL,
  `FAVORITA` tinyint(1) DEFAULT '0',
  `IDAPLICACAO` bigint(20) DEFAULT NULL,
  `IDSUBGRUPO` bigint(20) DEFAULT NULL,
  `IDTIPOUTILIRARIO` bigint(20) DEFAULT NULL,
  `UTILITARIO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `yobjetos`
--

CREATE TABLE `yobjetos` (
  `IDOBJETOS` bigint(20) NOT NULL,
  `APLICACAO` varchar(255) DEFAULT NULL,
  `DESCTIPOOBJETO` varchar(255) DEFAULT NULL,
  `DETALHESOBJETO` varchar(255) DEFAULT NULL,
  `IDAPLICACAO` bigint(20) DEFAULT NULL,
  `IDSUBGRUPO` bigint(20) DEFAULT NULL,
  `IDTIPOUTILIRARIO` bigint(20) DEFAULT NULL,
  `NOMEOBJETO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `ypemissoes`
--

CREATE TABLE `ypemissoes` (
  `IDPERMISSOES` bigint(20) NOT NULL,
  `ATUALIZAR` int(11) DEFAULT NULL,
  `CRIAR` int(11) DEFAULT NULL,
  `DELETAR` int(11) DEFAULT NULL,
  `IDAPLICACAO` bigint(20) DEFAULT NULL,
  `IDOBJETO` bigint(20) DEFAULT NULL,
  `IDPERFIL` bigint(20) DEFAULT NULL,
  `LER` int(11) DEFAULT NULL,
  `NOMEOBJETO` varchar(255) DEFAULT NULL,
  `STATUSOBJETO` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `yperfil`
--

CREATE TABLE `yperfil` (
  `IDPERFIL` bigint(20) NOT NULL,
  `IDTIPOPERFIL` bigint(20) DEFAULT NULL,
  `DESCRICAOPERFIL` varchar(255) DEFAULT NULL,
  `IDAPLICACAO` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `yusuarios`
--

CREATE TABLE `yusuarios` (
  `ID` bigint(20) NOT NULL,
  `IDPESSOA` bigint(20) DEFAULT NULL,
  `IDUSUARIO` varchar(255) DEFAULT NULL,
  `SENHA` int(11) DEFAULT NULL,
  `STATUS` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `acurso`
--
ALTER TABLE `acurso`
  ADD PRIMARY KEY (`IDCURSO`);

--
-- Indexes for table `adisciplinas`
--
ALTER TABLE `adisciplinas`
  ADD PRIMARY KEY (`IDDISCIPLINA`);

--
-- Indexes for table `amodalidadecurso`
--
ALTER TABLE `amodalidadecurso`
  ADD PRIMARY KEY (`IDMODALIDADE`);

--
-- Indexes for table `gaplicaca`
--
ALTER TABLE `gaplicaca`
  ADD PRIMARY KEY (`IDAPLICACAO`);

--
-- Indexes for table `gfiliais`
--
ALTER TABLE `gfiliais`
  ADD PRIMARY KEY (`IDFILIAL`);

--
-- Indexes for table `gmatriz`
--
ALTER TABLE `gmatriz`
  ADD PRIMARY KEY (`IDMATRIZ`);

--
-- Indexes for table `gmenus`
--
ALTER TABLE `gmenus`
  ADD PRIMARY KEY (`IDMENU`);

--
-- Indexes for table `gpessoa`
--
ALTER TABLE `gpessoa`
  ADD PRIMARY KEY (`IPESSOA`);

--
-- Indexes for table `gtipomenu`
--
ALTER TABLE `gtipomenu`
  ADD PRIMARY KEY (`IDTIPOMENU`);

--
-- Indexes for table `gtipoutilitarios`
--
ALTER TABLE `gtipoutilitarios`
  ADD PRIMARY KEY (`IDTIPOMENU`);

--
-- Indexes for table `gutilitarios`
--
ALTER TABLE `gutilitarios`
  ADD PRIMARY KEY (`IDUTILITARIO`);

--
-- Indexes for table `yobjetos`
--
ALTER TABLE `yobjetos`
  ADD PRIMARY KEY (`IDOBJETOS`);

--
-- Indexes for table `ypemissoes`
--
ALTER TABLE `ypemissoes`
  ADD PRIMARY KEY (`IDPERMISSOES`);

--
-- Indexes for table `yperfil`
--
ALTER TABLE `yperfil`
  ADD PRIMARY KEY (`IDPERFIL`);

--
-- Indexes for table `yusuarios`
--
ALTER TABLE `yusuarios`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `acurso`
--
ALTER TABLE `acurso`
  MODIFY `IDCURSO` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `adisciplinas`
--
ALTER TABLE `adisciplinas`
  MODIFY `IDDISCIPLINA` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `amodalidadecurso`
--
ALTER TABLE `amodalidadecurso`
  MODIFY `IDMODALIDADE` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `gaplicaca`
--
ALTER TABLE `gaplicaca`
  MODIFY `IDAPLICACAO` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `gfiliais`
--
ALTER TABLE `gfiliais`
  MODIFY `IDFILIAL` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `gmatriz`
--
ALTER TABLE `gmatriz`
  MODIFY `IDMATRIZ` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `gmenus`
--
ALTER TABLE `gmenus`
  MODIFY `IDMENU` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `gpessoa`
--
ALTER TABLE `gpessoa`
  MODIFY `IPESSOA` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `gtipomenu`
--
ALTER TABLE `gtipomenu`
  MODIFY `IDTIPOMENU` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `gtipoutilitarios`
--
ALTER TABLE `gtipoutilitarios`
  MODIFY `IDTIPOMENU` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `gutilitarios`
--
ALTER TABLE `gutilitarios`
  MODIFY `IDUTILITARIO` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `yobjetos`
--
ALTER TABLE `yobjetos`
  MODIFY `IDOBJETOS` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ypemissoes`
--
ALTER TABLE `ypemissoes`
  MODIFY `IDPERMISSOES` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `yperfil`
--
ALTER TABLE `yperfil`
  MODIFY `IDPERFIL` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `yusuarios`
--
ALTER TABLE `yusuarios`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
