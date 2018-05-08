-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 03-Maio-2018 às 13:49
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
  `IDPROJETOCURSO` bigint(20) DEFAULT NULL,
  `NOMECURSO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `acurso_amodalidadecurso`
--

CREATE TABLE `acurso_amodalidadecurso` (
  `aCurso_IDCURSO` bigint(20) NOT NULL,
  `modalidadecursos_IDMODALIDADE` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `acurso_aprojetocurso`
--

CREATE TABLE `acurso_aprojetocurso` (
  `aCurso_IDCURSO` bigint(20) NOT NULL,
  `projetocursos_IDPROJETOCURSO` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
-- Estrutura da tabela `adisciplinas_acurso`
--

CREATE TABLE `adisciplinas_acurso` (
  `aDisciplinas_IDDISCIPLINA` bigint(20) NOT NULL,
  `cursos_IDCURSO` bigint(20) NOT NULL
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
(1, 'Odontologia');

-- --------------------------------------------------------

--
-- Estrutura da tabela `aprojetocurso`
--

CREATE TABLE `aprojetocurso` (
  `IDPROJETOCURSO` bigint(20) NOT NULL,
  `DATAFIM` longblob,
  `DATAINICIO` longblob,
  `IDFILIAL` bigint(20) DEFAULT NULL,
  `IDMODALIDADE` bigint(20) DEFAULT NULL,
  `VALORCURSO` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `aprojetocurso_amodalidadecurso`
--

CREATE TABLE `aprojetocurso_amodalidadecurso` (
  `aProjetocurso_IDPROJETOCURSO` bigint(20) NOT NULL,
  `modalidadecursos_IDMODALIDADE` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `aprojetocurso_gfiliais`
--

CREATE TABLE `aprojetocurso_gfiliais` (
  `aProjetocurso_IDPROJETOCURSO` bigint(20) NOT NULL,
  `filiaises_IDFILIAL` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `gaplicacao`
--

CREATE TABLE `gaplicacao` (
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
-- Estrutura da tabela `gfiliais_gmatriz`
--

CREATE TABLE `gfiliais_gmatriz` (
  `gFiliais_IDFILIAL` bigint(20) NOT NULL,
  `matrizs_IDMATRIZ` bigint(20) NOT NULL
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
-- Estrutura da tabela `gmenus_gmenus`
--

CREATE TABLE `gmenus_gmenus` (
  `gMenus_IDMENU` bigint(20) NOT NULL,
  `menuses_IDMENU` bigint(20) NOT NULL
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
-- Estrutura da tabela `gpessoa_gutilitarios`
--

CREATE TABLE `gpessoa_gutilitarios` (
  `gPessoa_IPESSOA` bigint(20) NOT NULL,
  `utilitarioses_IDUTILITARIO` bigint(20) NOT NULL,
  `utilitarioses1_IDUTILITARIO` bigint(20) NOT NULL
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
-- Estrutura da tabela `gutilitarios_gaplicacao`
--

CREATE TABLE `gutilitarios_gaplicacao` (
  `gUtilitarios_IDUTILITARIO` bigint(20) NOT NULL,
  `aplicacaos_IDAPLICACAO` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `gutilitarios_gutilitarios`
--

CREATE TABLE `gutilitarios_gutilitarios` (
  `gUtilitarios_IDUTILITARIO` bigint(20) NOT NULL,
  `utilitarioses_IDUTILITARIO` bigint(20) NOT NULL
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

-- --------------------------------------------------------

--
-- Estrutura da tabela `yusuarios_gpessoa`
--

CREATE TABLE `yusuarios_gpessoa` (
  `yUsuarios_ID` bigint(20) NOT NULL,
  `pessoas_IPESSOA` bigint(20) NOT NULL
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
-- Indexes for table `acurso_amodalidadecurso`
--
ALTER TABLE `acurso_amodalidadecurso`
  ADD PRIMARY KEY (`aCurso_IDCURSO`,`modalidadecursos_IDMODALIDADE`),
  ADD KEY `ACURSOAMODALIDADECURSOmodalidadecursosIDMODALIDADE` (`modalidadecursos_IDMODALIDADE`);

--
-- Indexes for table `acurso_aprojetocurso`
--
ALTER TABLE `acurso_aprojetocurso`
  ADD PRIMARY KEY (`aCurso_IDCURSO`,`projetocursos_IDPROJETOCURSO`),
  ADD KEY `ACURSO_APROJETOCURSO_projetocursos_IDPROJETOCURSO` (`projetocursos_IDPROJETOCURSO`);

--
-- Indexes for table `adisciplinas`
--
ALTER TABLE `adisciplinas`
  ADD PRIMARY KEY (`IDDISCIPLINA`);

--
-- Indexes for table `adisciplinas_acurso`
--
ALTER TABLE `adisciplinas_acurso`
  ADD PRIMARY KEY (`aDisciplinas_IDDISCIPLINA`,`cursos_IDCURSO`),
  ADD KEY `FK_ADISCIPLINAS_ACURSO_cursos_IDCURSO` (`cursos_IDCURSO`);

--
-- Indexes for table `amodalidadecurso`
--
ALTER TABLE `amodalidadecurso`
  ADD PRIMARY KEY (`IDMODALIDADE`);

--
-- Indexes for table `aprojetocurso`
--
ALTER TABLE `aprojetocurso`
  ADD PRIMARY KEY (`IDPROJETOCURSO`);

--
-- Indexes for table `aprojetocurso_amodalidadecurso`
--
ALTER TABLE `aprojetocurso_amodalidadecurso`
  ADD PRIMARY KEY (`aProjetocurso_IDPROJETOCURSO`,`modalidadecursos_IDMODALIDADE`),
  ADD KEY `PRJTCURSOAMODALIDADECURSOmdldadecursosIDMODALIDADE` (`modalidadecursos_IDMODALIDADE`);

--
-- Indexes for table `aprojetocurso_gfiliais`
--
ALTER TABLE `aprojetocurso_gfiliais`
  ADD PRIMARY KEY (`aProjetocurso_IDPROJETOCURSO`,`filiaises_IDFILIAL`),
  ADD KEY `FK_APROJETOCURSO_GFILIAIS_filiaises_IDFILIAL` (`filiaises_IDFILIAL`);

--
-- Indexes for table `gaplicacao`
--
ALTER TABLE `gaplicacao`
  ADD PRIMARY KEY (`IDAPLICACAO`);

--
-- Indexes for table `gfiliais`
--
ALTER TABLE `gfiliais`
  ADD PRIMARY KEY (`IDFILIAL`);

--
-- Indexes for table `gfiliais_gmatriz`
--
ALTER TABLE `gfiliais_gmatriz`
  ADD PRIMARY KEY (`gFiliais_IDFILIAL`,`matrizs_IDMATRIZ`),
  ADD KEY `FK_GFILIAIS_GMATRIZ_matrizs_IDMATRIZ` (`matrizs_IDMATRIZ`);

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
-- Indexes for table `gmenus_gmenus`
--
ALTER TABLE `gmenus_gmenus`
  ADD PRIMARY KEY (`gMenus_IDMENU`,`menuses_IDMENU`),
  ADD KEY `FK_GMENUS_GMENUS_menuses_IDMENU` (`menuses_IDMENU`);

--
-- Indexes for table `gpessoa`
--
ALTER TABLE `gpessoa`
  ADD PRIMARY KEY (`IPESSOA`);

--
-- Indexes for table `gpessoa_gutilitarios`
--
ALTER TABLE `gpessoa_gutilitarios`
  ADD PRIMARY KEY (`gPessoa_IPESSOA`,`utilitarioses_IDUTILITARIO`,`utilitarioses1_IDUTILITARIO`),
  ADD KEY `FK_GPESSOA_GUTILITARIOS_utilitarioses_IDUTILITARIO` (`utilitarioses_IDUTILITARIO`),
  ADD KEY `GPESSOA_GUTILITARIOS_utilitarioses1_IDUTILITARIO` (`utilitarioses1_IDUTILITARIO`);

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
-- Indexes for table `gutilitarios_gaplicacao`
--
ALTER TABLE `gutilitarios_gaplicacao`
  ADD PRIMARY KEY (`gUtilitarios_IDUTILITARIO`,`aplicacaos_IDAPLICACAO`),
  ADD KEY `FK_GUTILITARIOS_GAPLICACAO_aplicacaos_IDAPLICACAO` (`aplicacaos_IDAPLICACAO`);

--
-- Indexes for table `gutilitarios_gutilitarios`
--
ALTER TABLE `gutilitarios_gutilitarios`
  ADD PRIMARY KEY (`gUtilitarios_IDUTILITARIO`,`utilitarioses_IDUTILITARIO`),
  ADD KEY `GUTILITARIOSGUTILITARIOSutilitarioses_IDUTILITARIO` (`utilitarioses_IDUTILITARIO`);

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
-- Indexes for table `yusuarios_gpessoa`
--
ALTER TABLE `yusuarios_gpessoa`
  ADD PRIMARY KEY (`yUsuarios_ID`,`pessoas_IPESSOA`),
  ADD KEY `FK_YUSUARIOS_GPESSOA_pessoas_IPESSOA` (`pessoas_IPESSOA`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `acurso`
--
ALTER TABLE `acurso`
  MODIFY `IDCURSO` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `adisciplinas`
--
ALTER TABLE `adisciplinas`
  MODIFY `IDDISCIPLINA` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `amodalidadecurso`
--
ALTER TABLE `amodalidadecurso`
  MODIFY `IDMODALIDADE` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `aprojetocurso`
--
ALTER TABLE `aprojetocurso`
  MODIFY `IDPROJETOCURSO` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `gaplicacao`
--
ALTER TABLE `gaplicacao`
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

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `acurso_amodalidadecurso`
--
ALTER TABLE `acurso_amodalidadecurso`
  ADD CONSTRAINT `ACURSOAMODALIDADECURSOmodalidadecursosIDMODALIDADE` FOREIGN KEY (`modalidadecursos_IDMODALIDADE`) REFERENCES `amodalidadecurso` (`IDMODALIDADE`),
  ADD CONSTRAINT `FK_ACURSO_AMODALIDADECURSO_aCurso_IDCURSO` FOREIGN KEY (`aCurso_IDCURSO`) REFERENCES `acurso` (`IDCURSO`);

--
-- Limitadores para a tabela `acurso_aprojetocurso`
--
ALTER TABLE `acurso_aprojetocurso`
  ADD CONSTRAINT `ACURSO_APROJETOCURSO_projetocursos_IDPROJETOCURSO` FOREIGN KEY (`projetocursos_IDPROJETOCURSO`) REFERENCES `aprojetocurso` (`IDPROJETOCURSO`),
  ADD CONSTRAINT `FK_ACURSO_APROJETOCURSO_aCurso_IDCURSO` FOREIGN KEY (`aCurso_IDCURSO`) REFERENCES `acurso` (`IDCURSO`);

--
-- Limitadores para a tabela `adisciplinas_acurso`
--
ALTER TABLE `adisciplinas_acurso`
  ADD CONSTRAINT `FK_ADISCIPLINAS_ACURSO_aDisciplinas_IDDISCIPLINA` FOREIGN KEY (`aDisciplinas_IDDISCIPLINA`) REFERENCES `adisciplinas` (`IDDISCIPLINA`),
  ADD CONSTRAINT `FK_ADISCIPLINAS_ACURSO_cursos_IDCURSO` FOREIGN KEY (`cursos_IDCURSO`) REFERENCES `acurso` (`IDCURSO`);

--
-- Limitadores para a tabela `aprojetocurso_amodalidadecurso`
--
ALTER TABLE `aprojetocurso_amodalidadecurso`
  ADD CONSTRAINT `PRJTCURSOAMODALIDADECURSOmdldadecursosIDMODALIDADE` FOREIGN KEY (`modalidadecursos_IDMODALIDADE`) REFERENCES `amodalidadecurso` (`IDMODALIDADE`),
  ADD CONSTRAINT `PRJTOCURSOAMODALIDADECURSOPrjtocursoIDPROJETOCURSO` FOREIGN KEY (`aProjetocurso_IDPROJETOCURSO`) REFERENCES `aprojetocurso` (`IDPROJETOCURSO`);

--
-- Limitadores para a tabela `aprojetocurso_gfiliais`
--
ALTER TABLE `aprojetocurso_gfiliais`
  ADD CONSTRAINT `APROJETOCURSO_GFILIAISaProjetocurso_IDPROJETOCURSO` FOREIGN KEY (`aProjetocurso_IDPROJETOCURSO`) REFERENCES `aprojetocurso` (`IDPROJETOCURSO`),
  ADD CONSTRAINT `FK_APROJETOCURSO_GFILIAIS_filiaises_IDFILIAL` FOREIGN KEY (`filiaises_IDFILIAL`) REFERENCES `gfiliais` (`IDFILIAL`);

--
-- Limitadores para a tabela `gfiliais_gmatriz`
--
ALTER TABLE `gfiliais_gmatriz`
  ADD CONSTRAINT `FK_GFILIAIS_GMATRIZ_gFiliais_IDFILIAL` FOREIGN KEY (`gFiliais_IDFILIAL`) REFERENCES `gfiliais` (`IDFILIAL`),
  ADD CONSTRAINT `FK_GFILIAIS_GMATRIZ_matrizs_IDMATRIZ` FOREIGN KEY (`matrizs_IDMATRIZ`) REFERENCES `gmatriz` (`IDMATRIZ`);

--
-- Limitadores para a tabela `gmenus_gmenus`
--
ALTER TABLE `gmenus_gmenus`
  ADD CONSTRAINT `FK_GMENUS_GMENUS_gMenus_IDMENU` FOREIGN KEY (`gMenus_IDMENU`) REFERENCES `gmenus` (`IDMENU`),
  ADD CONSTRAINT `FK_GMENUS_GMENUS_menuses_IDMENU` FOREIGN KEY (`menuses_IDMENU`) REFERENCES `gmenus` (`IDMENU`);

--
-- Limitadores para a tabela `gpessoa_gutilitarios`
--
ALTER TABLE `gpessoa_gutilitarios`
  ADD CONSTRAINT `FK_GPESSOA_GUTILITARIOS_gPessoa_IPESSOA` FOREIGN KEY (`gPessoa_IPESSOA`) REFERENCES `gpessoa` (`IPESSOA`),
  ADD CONSTRAINT `FK_GPESSOA_GUTILITARIOS_utilitarioses_IDUTILITARIO` FOREIGN KEY (`utilitarioses_IDUTILITARIO`) REFERENCES `gutilitarios` (`IDUTILITARIO`),
  ADD CONSTRAINT `GPESSOA_GUTILITARIOS_utilitarioses1_IDUTILITARIO` FOREIGN KEY (`utilitarioses1_IDUTILITARIO`) REFERENCES `gutilitarios` (`IDUTILITARIO`);

--
-- Limitadores para a tabela `gutilitarios_gaplicacao`
--
ALTER TABLE `gutilitarios_gaplicacao`
  ADD CONSTRAINT `FK_GUTILITARIOS_GAPLICACAO_aplicacaos_IDAPLICACAO` FOREIGN KEY (`aplicacaos_IDAPLICACAO`) REFERENCES `gaplicacao` (`IDAPLICACAO`),
  ADD CONSTRAINT `GUTILITARIOS_GAPLICACAO_gUtilitarios_IDUTILITARIO` FOREIGN KEY (`gUtilitarios_IDUTILITARIO`) REFERENCES `gutilitarios` (`IDUTILITARIO`);

--
-- Limitadores para a tabela `gutilitarios_gutilitarios`
--
ALTER TABLE `gutilitarios_gutilitarios`
  ADD CONSTRAINT `GUTILITARIOSGUTILITARIOSutilitarioses_IDUTILITARIO` FOREIGN KEY (`utilitarioses_IDUTILITARIO`) REFERENCES `gutilitarios` (`IDUTILITARIO`),
  ADD CONSTRAINT `GUTILITARIOS_GUTILITARIOSgUtilitarios_IDUTILITARIO` FOREIGN KEY (`gUtilitarios_IDUTILITARIO`) REFERENCES `gutilitarios` (`IDUTILITARIO`);

--
-- Limitadores para a tabela `yusuarios_gpessoa`
--
ALTER TABLE `yusuarios_gpessoa`
  ADD CONSTRAINT `FK_YUSUARIOS_GPESSOA_pessoas_IPESSOA` FOREIGN KEY (`pessoas_IPESSOA`) REFERENCES `gpessoa` (`IPESSOA`),
  ADD CONSTRAINT `FK_YUSUARIOS_GPESSOA_yUsuarios_ID` FOREIGN KEY (`yUsuarios_ID`) REFERENCES `yusuarios` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
