-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 04-Jun-2018 às 16:33
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
  `NOMECURSO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `acurso`
--

INSERT INTO `acurso` (`IDCURSO`, `NOMECURSO`) VALUES
(14, 'Odotologia'),
(15, 'Direito'),
(16, 'Gastronimia');

-- --------------------------------------------------------

--
-- Estrutura da tabela `acurso_amodalidadecurso`
--

CREATE TABLE `acurso_amodalidadecurso` (
  `aCurso_IDCURSO` bigint(20) NOT NULL,
  `modalidadecursos_IDMODALIDADE` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `acurso_amodalidadecurso`
--

INSERT INTO `acurso_amodalidadecurso` (`aCurso_IDCURSO`, `modalidadecursos_IDMODALIDADE`) VALUES
(14, 1),
(15, 1),
(16, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `adisciplinas`
--

CREATE TABLE `adisciplinas` (
  `IDDISCIPLINA` bigint(20) NOT NULL,
  `EMENTA` varchar(255) DEFAULT NULL,
  `NOMEDISCIPLINA` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `adisciplinas`
--

INSERT INTO `adisciplinas` (`IDDISCIPLINA`, `EMENTA`, `NOMEDISCIPLINA`) VALUES
(1, 'Direito civil da sociedade', 'Direito civil'),
(3, 'Parte geral do direito civil', 'Direito das coisas'),
(6, 'Parte geral', 'Antibioticos'),
(7, 'Criterios de modelagem e materias', 'Modelagem de proteses e anelise de materiais');

-- --------------------------------------------------------

--
-- Estrutura da tabela `adisciplinas_acurso`
--

CREATE TABLE `adisciplinas_acurso` (
  `aDisciplinas_IDDISCIPLINA` bigint(20) NOT NULL,
  `cursos_IDCURSO` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `adisciplinas_acurso`
--

INSERT INTO `adisciplinas_acurso` (`aDisciplinas_IDDISCIPLINA`, `cursos_IDCURSO`) VALUES
(1, 15),
(3, 15),
(6, 14),
(7, 14);

-- --------------------------------------------------------

--
-- Estrutura da tabela `adisciplinas_aprojetocurso`
--

CREATE TABLE `adisciplinas_aprojetocurso` (
  `aDisciplinas_IDDISCIPLINA` bigint(20) NOT NULL,
  `projetocurso_IDPROJETOCURSO` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `adisciplinas_aprojetocurso`
--

INSERT INTO `adisciplinas_aprojetocurso` (`aDisciplinas_IDDISCIPLINA`, `projetocurso_IDPROJETOCURSO`) VALUES
(1, 9),
(3, 9),
(6, 14),
(7, 14);

-- --------------------------------------------------------

--
-- Estrutura da tabela `ahorarioprofessor`
--

CREATE TABLE `ahorarioprofessor` (
  `IDAHORARIOPROFESSOR` bigint(20) NOT NULL,
  `VALORAULA` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `ahorarioprofessor`
--

INSERT INTO `ahorarioprofessor` (`IDAHORARIOPROFESSOR`, `VALORAULA`) VALUES
(2, 300),
(3, 300);

-- --------------------------------------------------------

--
-- Estrutura da tabela `ahorarioprofessor_aplanodeaula`
--

CREATE TABLE `ahorarioprofessor_aplanodeaula` (
  `aHorarioProfessor_IDAHORARIOPROFESSOR` bigint(20) NOT NULL,
  `planoDeAulas_IDPLANODEAULA` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `ahorarioprofessor_aplanodeaula`
--

INSERT INTO `ahorarioprofessor_aplanodeaula` (`aHorarioProfessor_IDAHORARIOPROFESSOR`, `planoDeAulas_IDPLANODEAULA`) VALUES
(2, 1),
(3, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `ahorarioprofessor_aprofessor`
--

CREATE TABLE `ahorarioprofessor_aprofessor` (
  `aHorarioProfessor_IDAHORARIOPROFESSOR` bigint(20) NOT NULL,
  `professor_IDPROFESSOR` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `ahorarioprofessor_aprofessor`
--

INSERT INTO `ahorarioprofessor_aprofessor` (`aHorarioProfessor_IDAHORARIOPROFESSOR`, `professor_IDPROFESSOR`) VALUES
(2, 1),
(3, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `ahorariosaulas`
--

CREATE TABLE `ahorariosaulas` (
  `IDHORARIOSAULAS` bigint(20) NOT NULL,
  `DATAFIM` varchar(255) DEFAULT NULL,
  `DATAINICIO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `ahorariosaulas`
--

INSERT INTO `ahorariosaulas` (`IDHORARIOSAULAS`, `DATAFIM`, `DATAINICIO`) VALUES
(1, '2018-06-01', '2018-02-02'),
(2, '2018-06-01', '2018-02-02');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ahorariosaulas_aturmadisciplinas`
--

CREATE TABLE `ahorariosaulas_aturmadisciplinas` (
  `aHorariosAulas_IDHORARIOSAULAS` bigint(20) NOT NULL,
  `turmaDisciplinas_IDTURMADISCIPLINA` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `ahorariosaulas_aturmadisciplinas`
--

INSERT INTO `ahorariosaulas_aturmadisciplinas` (`aHorariosAulas_IDHORARIOSAULAS`, `turmaDisciplinas_IDTURMADISCIPLINA`) VALUES
(1, 2),
(2, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `ahorariosaulas_gturnos`
--

CREATE TABLE `ahorariosaulas_gturnos` (
  `aHorariosAulas_IDHORARIOSAULAS` bigint(20) NOT NULL,
  `turno_IDTURNO` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `ahorariosaulas_gturnos`
--

INSERT INTO `ahorariosaulas_gturnos` (`aHorariosAulas_IDHORARIOSAULAS`, `turno_IDTURNO`) VALUES
(1, 1),
(2, 1);

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
(1, 'Graduação'),
(2, 'Pós graduação'),
(3, 'Cursos livres'),
(4, 'Mestrado');

-- --------------------------------------------------------

--
-- Estrutura da tabela `aplanodeaula`
--

CREATE TABLE `aplanodeaula` (
  `IDPLANODEAULA` bigint(20) NOT NULL,
  `CONFIRMADA` tinyint(1) DEFAULT '0',
  `CONTEUDO` varchar(255) DEFAULT NULL,
  `DATAAULA` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aplanodeaula`
--

INSERT INTO `aplanodeaula` (`IDPLANODEAULA`, `CONFIRMADA`, `CONTEUDO`, `DATAAULA`) VALUES
(1, 0, 'ddddddddddddddddd', '2018-02-02'),
(2, 0, 'fdgffgfg', '2018-02-03');

-- --------------------------------------------------------

--
-- Estrutura da tabela `aplanodeaula_ahorariosaulas`
--

CREATE TABLE `aplanodeaula_ahorariosaulas` (
  `aPlanoDeAula_IDPLANODEAULA` bigint(20) NOT NULL,
  `horariosAulas_IDHORARIOSAULAS` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aplanodeaula_ahorariosaulas`
--

INSERT INTO `aplanodeaula_ahorariosaulas` (`aPlanoDeAula_IDPLANODEAULA`, `horariosAulas_IDHORARIOSAULAS`) VALUES
(1, 2),
(2, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `aplanodeaula_gturnos`
--

CREATE TABLE `aplanodeaula_gturnos` (
  `aPlanoDeAula_IDPLANODEAULA` bigint(20) NOT NULL,
  `turno_IDTURNO` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aplanodeaula_gturnos`
--

INSERT INTO `aplanodeaula_gturnos` (`aPlanoDeAula_IDPLANODEAULA`, `turno_IDTURNO`) VALUES
(1, 1),
(2, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `aprofessor`
--

CREATE TABLE `aprofessor` (
  `IDPROFESSOR` bigint(20) NOT NULL,
  `ATIVO` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aprofessor`
--

INSERT INTO `aprofessor` (`IDPROFESSOR`, `ATIVO`) VALUES
(1, 1),
(2, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `aprofessor_gpessoa`
--

CREATE TABLE `aprofessor_gpessoa` (
  `aProfessor_IDPROFESSOR` bigint(20) NOT NULL,
  `pessoas_IDPESSOA` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aprofessor_gpessoa`
--

INSERT INTO `aprofessor_gpessoa` (`aProfessor_IDPROFESSOR`, `pessoas_IDPESSOA`) VALUES
(1, 33),
(2, 34);

-- --------------------------------------------------------

--
-- Estrutura da tabela `aprojetocurso`
--

CREATE TABLE `aprojetocurso` (
  `IDPROJETOCURSO` bigint(20) NOT NULL,
  `DESCRICAOPROJETO` varchar(255) DEFAULT NULL,
  `VALORCURSO` double DEFAULT NULL,
  `DATACRIACAO` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aprojetocurso`
--

INSERT INTO `aprojetocurso` (`IDPROJETOCURSO`, `DESCRICAOPROJETO`, `VALORCURSO`, `DATACRIACAO`) VALUES
(7, 'Graduação em filosofia 2015 ', 2000, '2018-06-01'),
(8, 'Cursos preparatórios', 10000, '2018-06-01'),
(9, ' Graduação em direito  2018', 1000, '2018-06-01'),
(10, 'Curso pós graduação', 500, '2018-06-01'),
(14, 'Graduação em Odontologia 2015', 50000, '2018-01-02');

-- --------------------------------------------------------

--
-- Estrutura da tabela `aprojetocurso_amodalidadecurso`
--

CREATE TABLE `aprojetocurso_amodalidadecurso` (
  `aProjetocurso_IDPROJETOCURSO` bigint(20) NOT NULL,
  `modalidadecurso_IDMODALIDADE` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aprojetocurso_amodalidadecurso`
--

INSERT INTO `aprojetocurso_amodalidadecurso` (`aProjetocurso_IDPROJETOCURSO`, `modalidadecurso_IDMODALIDADE`) VALUES
(7, 1),
(8, 1),
(9, 1),
(10, 1),
(14, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `aprojetocurso_gfiliais`
--

CREATE TABLE `aprojetocurso_gfiliais` (
  `aProjetocurso_IDPROJETOCURSO` bigint(20) NOT NULL,
  `filial_IDFILIAL` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aprojetocurso_gfiliais`
--

INSERT INTO `aprojetocurso_gfiliais` (`aProjetocurso_IDPROJETOCURSO`, `filial_IDFILIAL`) VALUES
(7, 2),
(8, 2),
(9, 2),
(10, 2),
(14, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `aturmadisciplinas`
--

CREATE TABLE `aturmadisciplinas` (
  `IDTURMADISCIPLINA` bigint(20) NOT NULL,
  `ATIVA` tinyint(1) DEFAULT '0',
  `DATAFIM` date DEFAULT NULL,
  `DATAINICIO` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aturmadisciplinas`
--

INSERT INTO `aturmadisciplinas` (`IDTURMADISCIPLINA`, `ATIVA`, `DATAFIM`, `DATAINICIO`) VALUES
(2, 1, '2018-06-20', '2018-02-20'),
(3, 1, '2018-06-20', '2018-02-20'),
(4, 1, '2018-06-20', '2018-02-20');

-- --------------------------------------------------------

--
-- Estrutura da tabela `aturmadisciplinas_adisciplinas`
--

CREATE TABLE `aturmadisciplinas_adisciplinas` (
  `aTurmaDisciplinas_IDTURMADISCIPLINA` bigint(20) NOT NULL,
  `disciplina_IDDISCIPLINA` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aturmadisciplinas_adisciplinas`
--

INSERT INTO `aturmadisciplinas_adisciplinas` (`aTurmaDisciplinas_IDTURMADISCIPLINA`, `disciplina_IDDISCIPLINA`) VALUES
(2, 1),
(3, 3),
(4, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `aturmadisciplinas_aunidadehabiltacao`
--

CREATE TABLE `aturmadisciplinas_aunidadehabiltacao` (
  `aTurmaDisciplinas_IDTURMADISCIPLINA` bigint(20) NOT NULL,
  `unidadeHabiltacao_IDUNIDADEHABILTACAO` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aturmadisciplinas_aunidadehabiltacao`
--

INSERT INTO `aturmadisciplinas_aunidadehabiltacao` (`aTurmaDisciplinas_IDTURMADISCIPLINA`, `unidadeHabiltacao_IDUNIDADEHABILTACAO`) VALUES
(2, 3),
(3, 3),
(4, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `aturmadisciplinas_gturnos`
--

CREATE TABLE `aturmadisciplinas_gturnos` (
  `aTurmaDisciplinas_IDTURMADISCIPLINA` bigint(20) NOT NULL,
  `turno_IDTURNO` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aturmadisciplinas_gturnos`
--

INSERT INTO `aturmadisciplinas_gturnos` (`aTurmaDisciplinas_IDTURMADISCIPLINA`, `turno_IDTURNO`) VALUES
(2, 1),
(3, 1),
(4, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `aunidadehabiltacao`
--

CREATE TABLE `aunidadehabiltacao` (
  `IDUNIDADEHABILTACAO` bigint(20) NOT NULL,
  `NOMEHABILITACAO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aunidadehabiltacao`
--

INSERT INTO `aunidadehabiltacao` (`IDUNIDADEHABILTACAO`, `NOMEHABILITACAO`) VALUES
(3, 'Curiculo 2018 - 2023'),
(4, 'Curiculo 2018 - 2023');

-- --------------------------------------------------------

--
-- Estrutura da tabela `aunidadehabiltacao_acurso`
--

CREATE TABLE `aunidadehabiltacao_acurso` (
  `aUnidadeHabiltacao_IDUNIDADEHABILTACAO` bigint(20) NOT NULL,
  `curso_IDCURSO` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aunidadehabiltacao_acurso`
--

INSERT INTO `aunidadehabiltacao_acurso` (`aUnidadeHabiltacao_IDUNIDADEHABILTACAO`, `curso_IDCURSO`) VALUES
(3, 15),
(4, 14);

-- --------------------------------------------------------

--
-- Estrutura da tabela `aunidadehabiltacao_aprojetocurso`
--

CREATE TABLE `aunidadehabiltacao_aprojetocurso` (
  `aUnidadeHabiltacao_IDUNIDADEHABILTACAO` bigint(20) NOT NULL,
  `projetocursos_IDPROJETOCURSO` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aunidadehabiltacao_aprojetocurso`
--

INSERT INTO `aunidadehabiltacao_aprojetocurso` (`aUnidadeHabiltacao_IDUNIDADEHABILTACAO`, `projetocursos_IDPROJETOCURSO`) VALUES
(3, 9),
(4, 14);

-- --------------------------------------------------------

--
-- Estrutura da tabela `aunidadehabiltacao_gfiliais`
--

CREATE TABLE `aunidadehabiltacao_gfiliais` (
  `aUnidadeHabiltacao_IDUNIDADEHABILTACAO` bigint(20) NOT NULL,
  `filial_IDFILIAL` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aunidadehabiltacao_gfiliais`
--

INSERT INTO `aunidadehabiltacao_gfiliais` (`aUnidadeHabiltacao_IDUNIDADEHABILTACAO`, `filial_IDFILIAL`) VALUES
(3, 1),
(4, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `aunidadehabiltacao_gmatriz`
--

CREATE TABLE `aunidadehabiltacao_gmatriz` (
  `aUnidadeHabiltacao_IDUNIDADEHABILTACAO` bigint(20) NOT NULL,
  `matriz_IDMATRIZ` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aunidadehabiltacao_gmatriz`
--

INSERT INTO `aunidadehabiltacao_gmatriz` (`aUnidadeHabiltacao_IDUNIDADEHABILTACAO`, `matriz_IDMATRIZ`) VALUES
(3, 1),
(4, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `gaplicacao`
--

CREATE TABLE `gaplicacao` (
  `IDAPLICACAO` bigint(20) NOT NULL,
  `DESCRICAO` varchar(255) DEFAULT NULL,
  `SIMBOLO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gaplicacao`
--

INSERT INTO `gaplicacao` (`IDAPLICACAO`, `DESCRICAO`, `SIMBOLO`) VALUES
(1, 'Tabelas globais', 'G'),
(2, 'Educacional', 'A'),
(3, 'Saúde', 'B'),
(4, 'Financeiro', 'F'),
(5, '', 'C'),
(6, '', 'D'),
(7, '', 'E'),
(8, '', 'H'),
(9, '', 'I'),
(10, '', 'J'),
(11, '', 'L'),
(12, '', 'M'),
(13, '', 'N'),
(14, '', 'O'),
(15, '', 'P'),
(16, '', 'Q'),
(17, '', 'R'),
(18, '', 'S'),
(19, '', 'T'),
(20, '', 'U'),
(21, '', 'V'),
(22, '', 'X'),
(23, '', 'Z'),
(24, '', 'K'),
(25, 'Administracão do sistesma', 'Y'),
(26, '', 'W');

-- --------------------------------------------------------

--
-- Estrutura da tabela `gfiliais`
--

CREATE TABLE `gfiliais` (
  `IDFILIAL` bigint(20) NOT NULL,
  `CNPJ` varchar(255) DEFAULT NULL,
  `DESCRICAO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gfiliais`
--

INSERT INTO `gfiliais` (`IDFILIAL`, `CNPJ`, `DESCRICAO`) VALUES
(1, '12323252-5363', 'Senac faculdade'),
(2, '45544-5224/211', 'Senac comunidade');

-- --------------------------------------------------------

--
-- Estrutura da tabela `gfiliais_gmatriz`
--

CREATE TABLE `gfiliais_gmatriz` (
  `gFiliais_IDFILIAL` bigint(20) NOT NULL,
  `matrizs_IDMATRIZ` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gfiliais_gmatriz`
--

INSERT INTO `gfiliais_gmatriz` (`gFiliais_IDFILIAL`, `matrizs_IDMATRIZ`) VALUES
(1, 1),
(2, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `gfiliais_gutilitarios`
--

CREATE TABLE `gfiliais_gutilitarios` (
  `gFiliais_IDFILIAL` bigint(20) NOT NULL,
  `utilitarios_IDUTILITARIO` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gfiliais_gutilitarios`
--

INSERT INTO `gfiliais_gutilitarios` (`gFiliais_IDFILIAL`, `utilitarios_IDUTILITARIO`) VALUES
(1, 7),
(2, 7);

-- --------------------------------------------------------

--
-- Estrutura da tabela `gmatriz`
--

CREATE TABLE `gmatriz` (
  `IDMATRIZ` bigint(20) NOT NULL,
  `CNPJ` varchar(255) DEFAULT NULL,
  `DESCRICAO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gmatriz`
--

INSERT INTO `gmatriz` (`IDMATRIZ`, `CNPJ`, `DESCRICAO`) VALUES
(1, '165165-25122-/111', 'Senac');

-- --------------------------------------------------------

--
-- Estrutura da tabela `gmenus`
--

CREATE TABLE `gmenus` (
  `IDMENU` bigint(20) NOT NULL,
  `FAVORITO` tinyint(1) DEFAULT '0',
  `NOMEMENU` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gmenus`
--

INSERT INTO `gmenus` (`IDMENU`, `FAVORITO`, `NOMEMENU`) VALUES
(1, 1, 'relatorio financeiro'),
(2, 1, 'Cadastro de funcionarios'),
(3, 1, 'Cadastro de pessoas');

-- --------------------------------------------------------

--
-- Estrutura da tabela `gmenus_gtipomenu`
--

CREATE TABLE `gmenus_gtipomenu` (
  `gMenus_IDMENU` bigint(20) NOT NULL,
  `tipomenu_IDTIPOMENU` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gmenus_gtipomenu`
--

INSERT INTO `gmenus_gtipomenu` (`gMenus_IDMENU`, `tipomenu_IDTIPOMENU`) VALUES
(1, 2),
(2, 1),
(3, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `gpessoa`
--

CREATE TABLE `gpessoa` (
  `IDPESSOA` bigint(20) NOT NULL,
  `ATIVA` tinyint(1) DEFAULT '0',
  `CPF` varchar(255) DEFAULT NULL,
  `CREF` varchar(255) DEFAULT NULL,
  `NOME` varchar(255) DEFAULT NULL,
  `RG` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gpessoa`
--

INSERT INTO `gpessoa` (`IDPESSOA`, `ATIVA`, `CPF`, `CREF`, `NOME`, `RG`) VALUES
(33, 0, '23325521-54', '123-565', 'Neimar moises', '231556-562'),
(34, 0, '23665-12214', '123-256', 'Carlos da silva', '231556-562');

-- --------------------------------------------------------

--
-- Estrutura da tabela `gpessoa_gutilitarios`
--

CREATE TABLE `gpessoa_gutilitarios` (
  `gPessoa_IDPESSOA` bigint(20) NOT NULL,
  `profissao_IDUTILITARIO` bigint(20) NOT NULL,
  `tipoPessoa_IDUTILITARIO` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `gpredio`
--

CREATE TABLE `gpredio` (
  `IDPREDIO` bigint(20) NOT NULL,
  `ATIVO` tinyint(1) DEFAULT '0',
  `NOMEPREDIO` varchar(255) DEFAULT NULL,
  `QTPAVIMENTOS` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gpredio`
--

INSERT INTO `gpredio` (`IDPREDIO`, `ATIVO`, `NOMEPREDIO`, `QTPAVIMENTOS`) VALUES
(1, 1, 'Predio A', 5),
(2, 1, 'Predio B', 5),
(3, 1, 'Predio unico', 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `gpredio_gfiliais`
--

CREATE TABLE `gpredio_gfiliais` (
  `gPredio_IDPREDIO` bigint(20) NOT NULL,
  `filial_IDFILIAL` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gpredio_gfiliais`
--

INSERT INTO `gpredio_gfiliais` (`gPredio_IDPREDIO`, `filial_IDFILIAL`) VALUES
(1, 2),
(2, 2),
(3, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `gsalas`
--

CREATE TABLE `gsalas` (
  `IDSALA` bigint(20) NOT NULL,
  `ATIVA` tinyint(1) DEFAULT '0',
  `CAPACIDADE` int(11) DEFAULT NULL,
  `PAVIMENTO` varchar(255) DEFAULT NULL,
  `SALA` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gsalas`
--

INSERT INTO `gsalas` (`IDSALA`, `ATIVA`, `CAPACIDADE`, `PAVIMENTO`, `SALA`) VALUES
(1, 1, 40, 'terreo', '100'),
(2, 1, 40, 'terreo', '101'),
(3, 1, 40, 'terreo', '102'),
(4, 1, 40, 'terreo', '103'),
(5, 1, 40, 'terreo', '104');

-- --------------------------------------------------------

--
-- Estrutura da tabela `gsalas_gpredio`
--

CREATE TABLE `gsalas_gpredio` (
  `gSalas_IDSALA` bigint(20) NOT NULL,
  `predios_IDPREDIO` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gsalas_gpredio`
--

INSERT INTO `gsalas_gpredio` (`gSalas_IDSALA`, `predios_IDPREDIO`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `gsalas_gutilitarios`
--

CREATE TABLE `gsalas_gutilitarios` (
  `gSalas_IDSALA` bigint(20) NOT NULL,
  `tipoSala_IDUTILITARIO` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gsalas_gutilitarios`
--

INSERT INTO `gsalas_gutilitarios` (`gSalas_IDSALA`, `tipoSala_IDUTILITARIO`) VALUES
(1, 11),
(2, 11),
(3, 11),
(4, 11),
(5, 12);

-- --------------------------------------------------------

--
-- Estrutura da tabela `gtipomenu`
--

CREATE TABLE `gtipomenu` (
  `IDTIPOMENU` bigint(20) NOT NULL,
  `NOMEMENU` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gtipomenu`
--

INSERT INTO `gtipomenu` (`IDTIPOMENU`, `NOMEMENU`) VALUES
(1, 'Relatorios'),
(2, 'Home'),
(3, 'Financeiro');

-- --------------------------------------------------------

--
-- Estrutura da tabela `gtipoutilitarios`
--

CREATE TABLE `gtipoutilitarios` (
  `IDTIPOUTILITARIO` bigint(20) NOT NULL,
  `DESCRICAO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gtipoutilitarios`
--

INSERT INTO `gtipoutilitarios` (`IDTIPOUTILITARIO`, `DESCRICAO`) VALUES
(1, 'Tipo pessoa'),
(2, 'Profissão'),
(3, 'Tipo Perfil'),
(4, 'Tipo filial'),
(5, 'Tipo sala');

-- --------------------------------------------------------

--
-- Estrutura da tabela `gturnos`
--

CREATE TABLE `gturnos` (
  `IDTURNO` bigint(20) NOT NULL,
  `DESCRICAO` varchar(255) DEFAULT NULL,
  `HORAFIM` varchar(255) DEFAULT NULL,
  `HORAINICIO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gturnos`
--

INSERT INTO `gturnos` (`IDTURNO`, `DESCRICAO`, `HORAFIM`, `HORAINICIO`) VALUES
(1, 'Manhã', '08:00', '11:45'),
(2, 'Tarde', '14:00', '17:40'),
(3, 'Noite', '19:00', '22:40'),
(4, 'Vespertino', '15:00', '19:00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `gutilitarios`
--

CREATE TABLE `gutilitarios` (
  `IDUTILITARIO` bigint(20) NOT NULL,
  `OBS` varchar(255) DEFAULT NULL,
  `FAVORITA` tinyint(1) DEFAULT '0',
  `NOMEUTILITARIO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gutilitarios`
--

INSERT INTO `gutilitarios` (`IDUTILITARIO`, `OBS`, `FAVORITA`, `NOMEUTILITARIO`) VALUES
(1, ' ', 0, 'Cliente'),
(2, ' ', 0, 'Fornecedor'),
(3, ' ', 0, 'Programador'),
(4, ' ', 0, 'Atedente'),
(5, ' ', 0, 'Encarregado'),
(6, ' ', 0, 'Unidade'),
(7, ' ', 0, 'Matriz'),
(8, ' ', 0, 'Filial'),
(9, ' ', 0, 'Polo'),
(10, ' ', 0, 'Campus'),
(11, ' ', 0, 'Sala de aula'),
(12, ' ', 0, 'LaboratÃ³rio'),
(13, ' ', 0, 'Anfitiatro');

-- --------------------------------------------------------

--
-- Estrutura da tabela `gutilitarios_gaplicacao`
--

CREATE TABLE `gutilitarios_gaplicacao` (
  `gUtilitarios_IDUTILITARIO` bigint(20) NOT NULL,
  `aplicacao_IDAPLICACAO` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gutilitarios_gaplicacao`
--

INSERT INTO `gutilitarios_gaplicacao` (`gUtilitarios_IDUTILITARIO`, `aplicacao_IDAPLICACAO`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 1),
(11, 1),
(12, 1),
(13, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `gutilitarios_gtipoutilitarios`
--

CREATE TABLE `gutilitarios_gtipoutilitarios` (
  `gUtilitarios_IDUTILITARIO` bigint(20) NOT NULL,
  `tipoutilitario_IDTIPOUTILITARIO` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `gutilitarios_gtipoutilitarios`
--

INSERT INTO `gutilitarios_gtipoutilitarios` (`gUtilitarios_IDUTILITARIO`, `tipoutilitario_IDTIPOUTILITARIO`) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(5, 2),
(6, 4),
(7, 4),
(8, 4),
(9, 4),
(10, 4),
(11, 5),
(12, 5),
(13, 5);

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

--
-- Extraindo dados da tabela `yobjetos`
--

INSERT INTO `yobjetos` (`IDOBJETOS`, `APLICACAO`, `DESCTIPOOBJETO`, `DETALHESOBJETO`, `IDAPLICACAO`, `IDSUBGRUPO`, `IDTIPOUTILIRARIO`, `NOMEOBJETO`) VALUES
(233, 'ACURSO', 'table', NULL, NULL, NULL, NULL, 'acurso'),
(234, 'ACURSO_AMODALIDADECURSO', 'table', NULL, NULL, NULL, NULL, 'acurso_amodalidadecurso'),
(235, 'ADISCIPLINAS', 'table', NULL, NULL, NULL, NULL, 'adisciplinas'),
(236, 'ADISCIPLINAS_ACURSO', 'table', NULL, NULL, NULL, NULL, 'adisciplinas_acurso'),
(237, 'ADISCIPLINAS_APROJETOCURSO', 'table', NULL, NULL, NULL, NULL, 'adisciplinas_aprojetocurso'),
(238, 'AHORARIOPROFESSOR', 'table', NULL, NULL, NULL, NULL, 'ahorarioprofessor'),
(239, 'AHORARIOPROFESSOR_APLANODEAULA', 'table', NULL, NULL, NULL, NULL, 'ahorarioprofessor_aplanodeaula'),
(240, 'AHORARIOPROFESSOR_APROFESSOR', 'table', NULL, NULL, NULL, NULL, 'ahorarioprofessor_aprofessor'),
(241, 'AHORARIOSAULAS', 'table', NULL, NULL, NULL, NULL, 'ahorariosaulas'),
(242, 'AHORARIOSAULAS_ATURMADISCIPLINAS', 'table', NULL, NULL, NULL, NULL, 'ahorariosaulas_aturmadisciplinas'),
(243, 'AHORARIOSAULAS_GTURNOS', 'table', NULL, NULL, NULL, NULL, 'ahorariosaulas_gturnos'),
(244, 'AMODALIDADECURSO', 'table', NULL, NULL, NULL, NULL, 'amodalidadecurso'),
(245, 'APLANODEAULA', 'table', NULL, NULL, NULL, NULL, 'aplanodeaula'),
(246, 'APLANODEAULA_AHORARIOSAULAS', 'table', NULL, NULL, NULL, NULL, 'aplanodeaula_ahorariosaulas'),
(247, 'APLANODEAULA_GTURNOS', 'table', NULL, NULL, NULL, NULL, 'aplanodeaula_gturnos'),
(248, 'APROFESSOR', 'table', NULL, NULL, NULL, NULL, 'aprofessor'),
(249, 'APROFESSOR_GPESSOA', 'table', NULL, NULL, NULL, NULL, 'aprofessor_gpessoa'),
(250, 'APROJETOCURSO', 'table', NULL, NULL, NULL, NULL, 'aprojetocurso'),
(251, 'APROJETOCURSO_AMODALIDADECURSO', 'table', NULL, NULL, NULL, NULL, 'aprojetocurso_amodalidadecurso'),
(252, 'APROJETOCURSO_GFILIAIS', 'table', NULL, NULL, NULL, NULL, 'aprojetocurso_gfiliais'),
(253, 'ATURMADISCIPLINAS', 'table', NULL, NULL, NULL, NULL, 'aturmadisciplinas'),
(254, 'ATURMADISCIPLINAS_ADISCIPLINAS', 'table', NULL, NULL, NULL, NULL, 'aturmadisciplinas_adisciplinas'),
(255, 'ATURMADISCIPLINAS_AUNIDADEHABILTACAO', 'table', NULL, NULL, NULL, NULL, 'aturmadisciplinas_aunidadehabiltacao'),
(256, 'ATURMADISCIPLINAS_GTURNOS', 'table', NULL, NULL, NULL, NULL, 'aturmadisciplinas_gturnos'),
(257, 'AUNIDADEHABILTACAO', 'table', NULL, NULL, NULL, NULL, 'aunidadehabiltacao'),
(258, 'AUNIDADEHABILTACAO_ACURSO', 'table', NULL, NULL, NULL, NULL, 'aunidadehabiltacao_acurso'),
(259, 'AUNIDADEHABILTACAO_APROJETOCURSO', 'table', NULL, NULL, NULL, NULL, 'aunidadehabiltacao_aprojetocurso'),
(260, 'AUNIDADEHABILTACAO_GFILIAIS', 'table', NULL, NULL, NULL, NULL, 'aunidadehabiltacao_gfiliais'),
(261, 'AUNIDADEHABILTACAO_GMATRIZ', 'table', NULL, NULL, NULL, NULL, 'aunidadehabiltacao_gmatriz'),
(262, 'GAPLICACAO', 'table', NULL, NULL, NULL, NULL, 'gaplicacao'),
(263, 'GFILIAIS', 'table', NULL, NULL, NULL, NULL, 'gfiliais'),
(264, 'GFILIAIS_GMATRIZ', 'table', NULL, NULL, NULL, NULL, 'gfiliais_gmatriz'),
(265, 'GFILIAIS_GUTILITARIOS', 'table', NULL, NULL, NULL, NULL, 'gfiliais_gutilitarios'),
(266, 'GMATRIZ', 'table', NULL, NULL, NULL, NULL, 'gmatriz'),
(267, 'GMENUS', 'table', NULL, NULL, NULL, NULL, 'gmenus'),
(268, 'GMENUS_GTIPOMENU', 'table', NULL, NULL, NULL, NULL, 'gmenus_gtipomenu'),
(269, 'GPESSOA', 'table', NULL, NULL, NULL, NULL, 'gpessoa'),
(270, 'GPESSOA_GUTILITARIOS', 'table', NULL, NULL, NULL, NULL, 'gpessoa_gutilitarios'),
(271, 'GPREDIO', 'table', NULL, NULL, NULL, NULL, 'gpredio'),
(272, 'GPREDIO_GFILIAIS', 'table', NULL, NULL, NULL, NULL, 'gpredio_gfiliais'),
(273, 'GSALAS', 'table', NULL, NULL, NULL, NULL, 'gsalas'),
(274, 'GSALAS_GPREDIO', 'table', NULL, NULL, NULL, NULL, 'gsalas_gpredio'),
(275, 'GSALAS_GUTILITARIOS', 'table', NULL, NULL, NULL, NULL, 'gsalas_gutilitarios'),
(276, 'GTIPOMENU', 'table', NULL, NULL, NULL, NULL, 'gtipomenu'),
(277, 'GTIPOUTILITARIOS', 'table', NULL, NULL, NULL, NULL, 'gtipoutilitarios'),
(278, 'GTURNOS', 'table', NULL, NULL, NULL, NULL, 'gturnos'),
(279, 'GUTILITARIOS', 'table', NULL, NULL, NULL, NULL, 'gutilitarios'),
(280, 'GUTILITARIOS_GAPLICACAO', 'table', NULL, NULL, NULL, NULL, 'gutilitarios_gaplicacao'),
(281, 'GUTILITARIOS_GTIPOUTILITARIOS', 'table', NULL, NULL, NULL, NULL, 'gutilitarios_gtipoutilitarios'),
(282, 'YOBJETOS', 'table', NULL, NULL, NULL, NULL, 'yobjetos'),
(283, 'YPEMISSOES', 'table', NULL, NULL, NULL, NULL, 'ypemissoes'),
(284, 'YPERFIL', 'table', NULL, NULL, NULL, NULL, 'yperfil'),
(285, 'YPERFIL_GFILIAIS', 'table', NULL, NULL, NULL, NULL, 'yperfil_gfiliais'),
(286, 'YPERFIL_GUTILITARIOS', 'table', NULL, NULL, NULL, NULL, 'yperfil_gutilitarios'),
(287, 'YUSUARIOS', 'table', NULL, NULL, NULL, NULL, 'yusuarios'),
(288, 'YUSUARIOS_GPESSOA', 'table', NULL, NULL, NULL, NULL, 'yusuarios_gpessoa'),
(289, 'MOTOR', 'table', NULL, NULL, NULL, NULL, 'motor'),
(290, 'CONTA', 'table', NULL, NULL, NULL, NULL, 'conta'),
(291, 'MENUS', 'table', NULL, NULL, NULL, NULL, 'menus'),
(292, 'MOVIMENTACAOCONTA', 'table', NULL, NULL, NULL, NULL, 'movimentacaoconta'),
(293, 'PESSOA', 'table', NULL, NULL, NULL, NULL, 'pessoa'),
(294, 'PRODUTOS', 'table', NULL, NULL, NULL, NULL, 'produtos'),
(295, 'TIPOUTILITARIOS', 'table', NULL, NULL, NULL, NULL, 'tipoutilitarios'),
(296, 'USUARIOS', 'table', NULL, NULL, NULL, NULL, 'usuarios'),
(297, 'UTILITARIOS', 'table', NULL, NULL, NULL, NULL, 'utilitarios'),
(298, 'VENDAINTENS', 'table', NULL, NULL, NULL, NULL, 'vendaintens'),
(299, 'VENDAPEDIDO', 'table', NULL, NULL, NULL, NULL, 'vendapedido'),
(300, 'ACONTEUDOCURSO', 'table', NULL, NULL, NULL, NULL, 'aconteudocurso'),
(301, 'GCIDADES', 'table', NULL, NULL, NULL, NULL, 'gcidades'),
(302, 'GCONTATOS', 'table', NULL, NULL, NULL, NULL, 'gcontatos'),
(303, 'GENDERECOS', 'table', NULL, NULL, NULL, NULL, 'genderecos'),
(304, 'GUF', 'table', NULL, NULL, NULL, NULL, 'guf'),
(305, 'GTID_SLAVE_POS', 'table', NULL, NULL, NULL, NULL, 'gtid_slave_pos'),
(306, 'INNODB_INDEX_STATS', 'table', NULL, NULL, NULL, NULL, 'innodb_index_stats'),
(307, 'INNODB_TABLE_STATS', 'table', NULL, NULL, NULL, NULL, 'innodb_table_stats'),
(308, 'SLAVE_MASTER_INFO', 'table', NULL, NULL, NULL, NULL, 'slave_master_info'),
(309, 'SLAVE_RELAY_LOG_INFO', 'table', NULL, NULL, NULL, NULL, 'slave_relay_log_info'),
(310, 'SLAVE_WORKER_INFO', 'table', NULL, NULL, NULL, NULL, 'slave_worker_info'),
(311, 'PMA__BOOKMARK', 'table', NULL, NULL, NULL, NULL, 'pma__bookmark'),
(312, 'PMA__CENTRAL_COLUMNS', 'table', NULL, NULL, NULL, NULL, 'pma__central_columns'),
(313, 'PMA__COLUMN_INFO', 'table', NULL, NULL, NULL, NULL, 'pma__column_info'),
(314, 'PMA__DESIGNER_SETTINGS', 'table', NULL, NULL, NULL, NULL, 'pma__designer_settings'),
(315, 'PMA__EXPORT_TEMPLATES', 'table', NULL, NULL, NULL, NULL, 'pma__export_templates'),
(316, 'PMA__FAVORITE', 'table', NULL, NULL, NULL, NULL, 'pma__favorite'),
(317, 'PMA__HISTORY', 'table', NULL, NULL, NULL, NULL, 'pma__history'),
(318, 'PMA__NAVIGATIONHIDING', 'table', NULL, NULL, NULL, NULL, 'pma__navigationhiding'),
(319, 'PMA__PDF_PAGES', 'table', NULL, NULL, NULL, NULL, 'pma__pdf_pages'),
(320, 'PMA__RECENT', 'table', NULL, NULL, NULL, NULL, 'pma__recent'),
(321, 'PMA__RELATION', 'table', NULL, NULL, NULL, NULL, 'pma__relation'),
(322, 'PMA__SAVEDSEARCHES', 'table', NULL, NULL, NULL, NULL, 'pma__savedsearches'),
(323, 'PMA__TABLE_COORDS', 'table', NULL, NULL, NULL, NULL, 'pma__table_coords'),
(324, 'PMA__TABLE_INFO', 'table', NULL, NULL, NULL, NULL, 'pma__table_info'),
(325, 'PMA__TABLE_UIPREFS', 'table', NULL, NULL, NULL, NULL, 'pma__table_uiprefs'),
(326, 'PMA__TRACKING', 'table', NULL, NULL, NULL, NULL, 'pma__tracking'),
(327, 'PMA__USERCONFIG', 'table', NULL, NULL, NULL, NULL, 'pma__userconfig'),
(328, 'PMA__USERGROUPS', 'table', NULL, NULL, NULL, NULL, 'pma__usergroups'),
(329, 'PMA__USERS', 'table', NULL, NULL, NULL, NULL, 'pma__users'),
(330, 'CUSTOMER', 'table', NULL, NULL, NULL, NULL, 'customer'),
(331, 'DISCOUNT_CODE', 'table', NULL, NULL, NULL, NULL, 'discount_code'),
(332, 'MANUFACTURER', 'table', NULL, NULL, NULL, NULL, 'manufacturer'),
(333, 'MICRO_MARKET', 'table', NULL, NULL, NULL, NULL, 'micro_market'),
(334, 'PRODUCT', 'table', NULL, NULL, NULL, NULL, 'product'),
(335, 'PRODUCT_CODE', 'table', NULL, NULL, NULL, NULL, 'product_code'),
(336, 'PURCHASE_ORDER', 'table', NULL, NULL, NULL, NULL, 'purchase_order'),
(337, 'DBDESIGNER4', 'table', NULL, NULL, NULL, NULL, 'dbdesigner4');

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
  `DESCRICAOPERFIL` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `yperfil_gfiliais`
--

CREATE TABLE `yperfil_gfiliais` (
  `yPerfil_IDPERFIL` bigint(20) NOT NULL,
  `filial_IDFILIAL` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `yperfil_gutilitarios`
--

CREATE TABLE `yperfil_gutilitarios` (
  `yPerfil_IDPERFIL` bigint(20) NOT NULL,
  `tipoPerfil_IDUTILITARIO` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `yusuarios`
--

CREATE TABLE `yusuarios` (
  `ID` bigint(20) NOT NULL,
  `IDUSUARIO` varchar(255) DEFAULT NULL,
  `SENHA` int(11) DEFAULT NULL,
  `STATUS` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `yusuarios`
--

INSERT INTO `yusuarios` (`ID`, `IDUSUARIO`, `SENHA`, `STATUS`) VALUES
(1, 'naimar', 123456, 1),
(2, 'jsilva', 123456, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `yusuarios_gpessoa`
--

CREATE TABLE `yusuarios_gpessoa` (
  `yUsuarios_ID` bigint(20) NOT NULL,
  `pessoa_IDPESSOA` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `yusuarios_gpessoa`
--

INSERT INTO `yusuarios_gpessoa` (`yUsuarios_ID`, `pessoa_IDPESSOA`) VALUES
(1, 34),
(2, 33);

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
-- Indexes for table `adisciplinas_aprojetocurso`
--
ALTER TABLE `adisciplinas_aprojetocurso`
  ADD PRIMARY KEY (`aDisciplinas_IDDISCIPLINA`,`projetocurso_IDPROJETOCURSO`),
  ADD KEY `DISCIPLINASAPROJETOCURSOprojetocursoIDPROJETOCURSO` (`projetocurso_IDPROJETOCURSO`);

--
-- Indexes for table `ahorarioprofessor`
--
ALTER TABLE `ahorarioprofessor`
  ADD PRIMARY KEY (`IDAHORARIOPROFESSOR`);

--
-- Indexes for table `ahorarioprofessor_aplanodeaula`
--
ALTER TABLE `ahorarioprofessor_aplanodeaula`
  ADD PRIMARY KEY (`aHorarioProfessor_IDAHORARIOPROFESSOR`,`planoDeAulas_IDPLANODEAULA`),
  ADD KEY `HRRIOPROFESSORAPLANODEAULAplnoDeAulasIDPLANODEAULA` (`planoDeAulas_IDPLANODEAULA`);

--
-- Indexes for table `ahorarioprofessor_aprofessor`
--
ALTER TABLE `ahorarioprofessor_aprofessor`
  ADD PRIMARY KEY (`aHorarioProfessor_IDAHORARIOPROFESSOR`,`professor_IDPROFESSOR`),
  ADD KEY `AHORARIOPROFESSOR_APROFESSOR_professor_IDPROFESSOR` (`professor_IDPROFESSOR`);

--
-- Indexes for table `ahorariosaulas`
--
ALTER TABLE `ahorariosaulas`
  ADD PRIMARY KEY (`IDHORARIOSAULAS`);

--
-- Indexes for table `ahorariosaulas_aturmadisciplinas`
--
ALTER TABLE `ahorariosaulas_aturmadisciplinas`
  ADD PRIMARY KEY (`aHorariosAulas_IDHORARIOSAULAS`,`turmaDisciplinas_IDTURMADISCIPLINA`),
  ADD KEY `HRRSLSTURMADISCIPLINAStrmDscplnasIDTURMADISCIPLINA` (`turmaDisciplinas_IDTURMADISCIPLINA`);

--
-- Indexes for table `ahorariosaulas_gturnos`
--
ALTER TABLE `ahorariosaulas_gturnos`
  ADD PRIMARY KEY (`aHorariosAulas_IDHORARIOSAULAS`,`turno_IDTURNO`),
  ADD KEY `FK_AHORARIOSAULAS_GTURNOS_turno_IDTURNO` (`turno_IDTURNO`);

--
-- Indexes for table `amodalidadecurso`
--
ALTER TABLE `amodalidadecurso`
  ADD PRIMARY KEY (`IDMODALIDADE`);

--
-- Indexes for table `aplanodeaula`
--
ALTER TABLE `aplanodeaula`
  ADD PRIMARY KEY (`IDPLANODEAULA`);

--
-- Indexes for table `aplanodeaula_ahorariosaulas`
--
ALTER TABLE `aplanodeaula_ahorariosaulas`
  ADD PRIMARY KEY (`aPlanoDeAula_IDPLANODEAULA`,`horariosAulas_IDHORARIOSAULAS`),
  ADD KEY `PLNDEAULAAHORARIOSAULAShrrosAulasesIDHORARIOSAULAS` (`horariosAulas_IDHORARIOSAULAS`);

--
-- Indexes for table `aplanodeaula_gturnos`
--
ALTER TABLE `aplanodeaula_gturnos`
  ADD PRIMARY KEY (`aPlanoDeAula_IDPLANODEAULA`,`turno_IDTURNO`),
  ADD KEY `FK_APLANODEAULA_GTURNOS_turno_IDTURNO` (`turno_IDTURNO`);

--
-- Indexes for table `aprofessor`
--
ALTER TABLE `aprofessor`
  ADD PRIMARY KEY (`IDPROFESSOR`);

--
-- Indexes for table `aprofessor_gpessoa`
--
ALTER TABLE `aprofessor_gpessoa`
  ADD PRIMARY KEY (`aProfessor_IDPROFESSOR`,`pessoas_IDPESSOA`),
  ADD KEY `FK_APROFESSOR_GPESSOA_pessoas_IDPESSOA` (`pessoas_IDPESSOA`);

--
-- Indexes for table `aprojetocurso`
--
ALTER TABLE `aprojetocurso`
  ADD PRIMARY KEY (`IDPROJETOCURSO`);

--
-- Indexes for table `aprojetocurso_amodalidadecurso`
--
ALTER TABLE `aprojetocurso_amodalidadecurso`
  ADD PRIMARY KEY (`aProjetocurso_IDPROJETOCURSO`,`modalidadecurso_IDMODALIDADE`),
  ADD KEY `PRJTOCURSOAMODALIDADECURSOmdldadecursoIDMODALIDADE` (`modalidadecurso_IDMODALIDADE`);

--
-- Indexes for table `aprojetocurso_gfiliais`
--
ALTER TABLE `aprojetocurso_gfiliais`
  ADD PRIMARY KEY (`aProjetocurso_IDPROJETOCURSO`,`filial_IDFILIAL`),
  ADD KEY `FK_APROJETOCURSO_GFILIAIS_filial_IDFILIAL` (`filial_IDFILIAL`);

--
-- Indexes for table `aturmadisciplinas`
--
ALTER TABLE `aturmadisciplinas`
  ADD PRIMARY KEY (`IDTURMADISCIPLINA`);

--
-- Indexes for table `aturmadisciplinas_adisciplinas`
--
ALTER TABLE `aturmadisciplinas_adisciplinas`
  ADD PRIMARY KEY (`aTurmaDisciplinas_IDTURMADISCIPLINA`,`disciplina_IDDISCIPLINA`),
  ADD KEY `TURMADISCIPLINASADISCIPLINASdisciplinaIDDISCIPLINA` (`disciplina_IDDISCIPLINA`);

--
-- Indexes for table `aturmadisciplinas_aunidadehabiltacao`
--
ALTER TABLE `aturmadisciplinas_aunidadehabiltacao`
  ADD PRIMARY KEY (`aTurmaDisciplinas_IDTURMADISCIPLINA`,`unidadeHabiltacao_IDUNIDADEHABILTACAO`),
  ADD KEY `TRMDSCPLNSNDADEHABILTACAOnddHbltcDNIDADEHABILTACAO` (`unidadeHabiltacao_IDUNIDADEHABILTACAO`);

--
-- Indexes for table `aturmadisciplinas_gturnos`
--
ALTER TABLE `aturmadisciplinas_gturnos`
  ADD PRIMARY KEY (`aTurmaDisciplinas_IDTURMADISCIPLINA`,`turno_IDTURNO`),
  ADD KEY `FK_ATURMADISCIPLINAS_GTURNOS_turno_IDTURNO` (`turno_IDTURNO`);

--
-- Indexes for table `aunidadehabiltacao`
--
ALTER TABLE `aunidadehabiltacao`
  ADD PRIMARY KEY (`IDUNIDADEHABILTACAO`);

--
-- Indexes for table `aunidadehabiltacao_acurso`
--
ALTER TABLE `aunidadehabiltacao_acurso`
  ADD PRIMARY KEY (`aUnidadeHabiltacao_IDUNIDADEHABILTACAO`,`curso_IDCURSO`),
  ADD KEY `FK_AUNIDADEHABILTACAO_ACURSO_curso_IDCURSO` (`curso_IDCURSO`);

--
-- Indexes for table `aunidadehabiltacao_aprojetocurso`
--
ALTER TABLE `aunidadehabiltacao_aprojetocurso`
  ADD PRIMARY KEY (`aUnidadeHabiltacao_IDUNIDADEHABILTACAO`,`projetocursos_IDPROJETOCURSO`),
  ADD KEY `NDDHABILTACAOAPROJETOCURSOprjtcursosIDPROJETOCURSO` (`projetocursos_IDPROJETOCURSO`);

--
-- Indexes for table `aunidadehabiltacao_gfiliais`
--
ALTER TABLE `aunidadehabiltacao_gfiliais`
  ADD PRIMARY KEY (`aUnidadeHabiltacao_IDUNIDADEHABILTACAO`,`filial_IDFILIAL`),
  ADD KEY `FK_AUNIDADEHABILTACAO_GFILIAIS_filial_IDFILIAL` (`filial_IDFILIAL`);

--
-- Indexes for table `aunidadehabiltacao_gmatriz`
--
ALTER TABLE `aunidadehabiltacao_gmatriz`
  ADD PRIMARY KEY (`aUnidadeHabiltacao_IDUNIDADEHABILTACAO`,`matriz_IDMATRIZ`),
  ADD KEY `FK_AUNIDADEHABILTACAO_GMATRIZ_matriz_IDMATRIZ` (`matriz_IDMATRIZ`);

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
-- Indexes for table `gfiliais_gutilitarios`
--
ALTER TABLE `gfiliais_gutilitarios`
  ADD PRIMARY KEY (`gFiliais_IDFILIAL`,`utilitarios_IDUTILITARIO`),
  ADD KEY `FK_GFILIAIS_GUTILITARIOS_utilitarios_IDUTILITARIO` (`utilitarios_IDUTILITARIO`);

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
-- Indexes for table `gmenus_gtipomenu`
--
ALTER TABLE `gmenus_gtipomenu`
  ADD PRIMARY KEY (`gMenus_IDMENU`,`tipomenu_IDTIPOMENU`),
  ADD KEY `FK_GMENUS_GTIPOMENU_tipomenu_IDTIPOMENU` (`tipomenu_IDTIPOMENU`);

--
-- Indexes for table `gpessoa`
--
ALTER TABLE `gpessoa`
  ADD PRIMARY KEY (`IDPESSOA`);

--
-- Indexes for table `gpessoa_gutilitarios`
--
ALTER TABLE `gpessoa_gutilitarios`
  ADD PRIMARY KEY (`gPessoa_IDPESSOA`,`profissao_IDUTILITARIO`,`tipoPessoa_IDUTILITARIO`),
  ADD KEY `FK_GPESSOA_GUTILITARIOS_profissao_IDUTILITARIO` (`profissao_IDUTILITARIO`),
  ADD KEY `FK_GPESSOA_GUTILITARIOS_tipoPessoa_IDUTILITARIO` (`tipoPessoa_IDUTILITARIO`);

--
-- Indexes for table `gpredio`
--
ALTER TABLE `gpredio`
  ADD PRIMARY KEY (`IDPREDIO`);

--
-- Indexes for table `gpredio_gfiliais`
--
ALTER TABLE `gpredio_gfiliais`
  ADD PRIMARY KEY (`gPredio_IDPREDIO`,`filial_IDFILIAL`),
  ADD KEY `FK_GPREDIO_GFILIAIS_filial_IDFILIAL` (`filial_IDFILIAL`);

--
-- Indexes for table `gsalas`
--
ALTER TABLE `gsalas`
  ADD PRIMARY KEY (`IDSALA`);

--
-- Indexes for table `gsalas_gpredio`
--
ALTER TABLE `gsalas_gpredio`
  ADD PRIMARY KEY (`gSalas_IDSALA`,`predios_IDPREDIO`),
  ADD KEY `FK_GSALAS_GPREDIO_predios_IDPREDIO` (`predios_IDPREDIO`);

--
-- Indexes for table `gsalas_gutilitarios`
--
ALTER TABLE `gsalas_gutilitarios`
  ADD PRIMARY KEY (`gSalas_IDSALA`,`tipoSala_IDUTILITARIO`),
  ADD KEY `FK_GSALAS_GUTILITARIOS_utilitarios_IDUTILITARIO` (`tipoSala_IDUTILITARIO`);

--
-- Indexes for table `gtipomenu`
--
ALTER TABLE `gtipomenu`
  ADD PRIMARY KEY (`IDTIPOMENU`);

--
-- Indexes for table `gtipoutilitarios`
--
ALTER TABLE `gtipoutilitarios`
  ADD PRIMARY KEY (`IDTIPOUTILITARIO`);

--
-- Indexes for table `gturnos`
--
ALTER TABLE `gturnos`
  ADD PRIMARY KEY (`IDTURNO`);

--
-- Indexes for table `gutilitarios`
--
ALTER TABLE `gutilitarios`
  ADD PRIMARY KEY (`IDUTILITARIO`);

--
-- Indexes for table `gutilitarios_gaplicacao`
--
ALTER TABLE `gutilitarios_gaplicacao`
  ADD PRIMARY KEY (`gUtilitarios_IDUTILITARIO`,`aplicacao_IDAPLICACAO`),
  ADD KEY `FK_GUTILITARIOS_GAPLICACAO_aplicacao_IDAPLICACAO` (`aplicacao_IDAPLICACAO`);

--
-- Indexes for table `gutilitarios_gtipoutilitarios`
--
ALTER TABLE `gutilitarios_gtipoutilitarios`
  ADD PRIMARY KEY (`gUtilitarios_IDUTILITARIO`,`tipoutilitario_IDTIPOUTILITARIO`),
  ADD KEY `GTLTRIOSGTIPOUTILITARIOStptlitarioIDTIPOUTILITARIO` (`tipoutilitario_IDTIPOUTILITARIO`);

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
-- Indexes for table `yperfil_gfiliais`
--
ALTER TABLE `yperfil_gfiliais`
  ADD PRIMARY KEY (`yPerfil_IDPERFIL`,`filial_IDFILIAL`),
  ADD KEY `FK_YPERFIL_GFILIAIS_filial_IDFILIAL` (`filial_IDFILIAL`);

--
-- Indexes for table `yperfil_gutilitarios`
--
ALTER TABLE `yperfil_gutilitarios`
  ADD PRIMARY KEY (`yPerfil_IDPERFIL`,`tipoPerfil_IDUTILITARIO`),
  ADD KEY `FK_YPERFIL_GUTILITARIOS_tipoPerfil_IDUTILITARIO` (`tipoPerfil_IDUTILITARIO`);

--
-- Indexes for table `yusuarios`
--
ALTER TABLE `yusuarios`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `yusuarios_gpessoa`
--
ALTER TABLE `yusuarios_gpessoa`
  ADD PRIMARY KEY (`yUsuarios_ID`,`pessoa_IDPESSOA`),
  ADD KEY `FK_YUSUARIOS_GPESSOA_pessoa_IDPESSOA` (`pessoa_IDPESSOA`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `acurso`
--
ALTER TABLE `acurso`
  MODIFY `IDCURSO` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `adisciplinas`
--
ALTER TABLE `adisciplinas`
  MODIFY `IDDISCIPLINA` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `ahorarioprofessor`
--
ALTER TABLE `ahorarioprofessor`
  MODIFY `IDAHORARIOPROFESSOR` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `ahorariosaulas`
--
ALTER TABLE `ahorariosaulas`
  MODIFY `IDHORARIOSAULAS` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `amodalidadecurso`
--
ALTER TABLE `amodalidadecurso`
  MODIFY `IDMODALIDADE` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `aplanodeaula`
--
ALTER TABLE `aplanodeaula`
  MODIFY `IDPLANODEAULA` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `aprofessor`
--
ALTER TABLE `aprofessor`
  MODIFY `IDPROFESSOR` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `aprojetocurso`
--
ALTER TABLE `aprojetocurso`
  MODIFY `IDPROJETOCURSO` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `aturmadisciplinas`
--
ALTER TABLE `aturmadisciplinas`
  MODIFY `IDTURMADISCIPLINA` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `aunidadehabiltacao`
--
ALTER TABLE `aunidadehabiltacao`
  MODIFY `IDUNIDADEHABILTACAO` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `gaplicacao`
--
ALTER TABLE `gaplicacao`
  MODIFY `IDAPLICACAO` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `gfiliais`
--
ALTER TABLE `gfiliais`
  MODIFY `IDFILIAL` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `gmatriz`
--
ALTER TABLE `gmatriz`
  MODIFY `IDMATRIZ` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `gmenus`
--
ALTER TABLE `gmenus`
  MODIFY `IDMENU` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `gpessoa`
--
ALTER TABLE `gpessoa`
  MODIFY `IDPESSOA` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `gpredio`
--
ALTER TABLE `gpredio`
  MODIFY `IDPREDIO` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `gsalas`
--
ALTER TABLE `gsalas`
  MODIFY `IDSALA` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `gtipomenu`
--
ALTER TABLE `gtipomenu`
  MODIFY `IDTIPOMENU` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `gtipoutilitarios`
--
ALTER TABLE `gtipoutilitarios`
  MODIFY `IDTIPOUTILITARIO` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `gturnos`
--
ALTER TABLE `gturnos`
  MODIFY `IDTURNO` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `gutilitarios`
--
ALTER TABLE `gutilitarios`
  MODIFY `IDUTILITARIO` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `yobjetos`
--
ALTER TABLE `yobjetos`
  MODIFY `IDOBJETOS` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=360;

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
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
-- Limitadores para a tabela `adisciplinas_acurso`
--
ALTER TABLE `adisciplinas_acurso`
  ADD CONSTRAINT `FK_ADISCIPLINAS_ACURSO_aDisciplinas_IDDISCIPLINA` FOREIGN KEY (`aDisciplinas_IDDISCIPLINA`) REFERENCES `adisciplinas` (`IDDISCIPLINA`),
  ADD CONSTRAINT `FK_ADISCIPLINAS_ACURSO_cursos_IDCURSO` FOREIGN KEY (`cursos_IDCURSO`) REFERENCES `acurso` (`IDCURSO`);

--
-- Limitadores para a tabela `adisciplinas_aprojetocurso`
--
ALTER TABLE `adisciplinas_aprojetocurso`
  ADD CONSTRAINT `ADISCIPLINASAPROJETOCURSOaDisciplinas_IDDISCIPLINA` FOREIGN KEY (`aDisciplinas_IDDISCIPLINA`) REFERENCES `adisciplinas` (`IDDISCIPLINA`),
  ADD CONSTRAINT `DISCIPLINASAPROJETOCURSOprojetocursoIDPROJETOCURSO` FOREIGN KEY (`projetocurso_IDPROJETOCURSO`) REFERENCES `aprojetocurso` (`IDPROJETOCURSO`);

--
-- Limitadores para a tabela `ahorarioprofessor_aplanodeaula`
--
ALTER TABLE `ahorarioprofessor_aplanodeaula`
  ADD CONSTRAINT `HRRIOPROFESSORAPLANODEAULAplnoDeAulasIDPLANODEAULA` FOREIGN KEY (`planoDeAulas_IDPLANODEAULA`) REFERENCES `aplanodeaula` (`IDPLANODEAULA`),
  ADD CONSTRAINT `HRRPRFSSRAPLANODEAULAHrrPrfssorIDAHORARIOPROFESSOR` FOREIGN KEY (`aHorarioProfessor_IDAHORARIOPROFESSOR`) REFERENCES `ahorarioprofessor` (`IDAHORARIOPROFESSOR`);

--
-- Limitadores para a tabela `ahorarioprofessor_aprofessor`
--
ALTER TABLE `ahorarioprofessor_aprofessor`
  ADD CONSTRAINT `AHORARIOPROFESSOR_APROFESSOR_professor_IDPROFESSOR` FOREIGN KEY (`professor_IDPROFESSOR`) REFERENCES `aprofessor` (`IDPROFESSOR`),
  ADD CONSTRAINT `HRRPRFSSORAPROFESSORHrrPrfessorIDAHORARIOPROFESSOR` FOREIGN KEY (`aHorarioProfessor_IDAHORARIOPROFESSOR`) REFERENCES `ahorarioprofessor` (`IDAHORARIOPROFESSOR`);

--
-- Limitadores para a tabela `ahorariosaulas_aturmadisciplinas`
--
ALTER TABLE `ahorariosaulas_aturmadisciplinas`
  ADD CONSTRAINT `HRRSAULASATURMADISCIPLINASHrrsAulasIDHORARIOSAULAS` FOREIGN KEY (`aHorariosAulas_IDHORARIOSAULAS`) REFERENCES `ahorariosaulas` (`IDHORARIOSAULAS`),
  ADD CONSTRAINT `HRRSLSTURMADISCIPLINAStrmDscplnasIDTURMADISCIPLINA` FOREIGN KEY (`turmaDisciplinas_IDTURMADISCIPLINA`) REFERENCES `aturmadisciplinas` (`IDTURMADISCIPLINA`);

--
-- Limitadores para a tabela `ahorariosaulas_gturnos`
--
ALTER TABLE `ahorariosaulas_gturnos`
  ADD CONSTRAINT `AHORARIOSAULASGTURNOSaHorariosAulasIDHORARIOSAULAS` FOREIGN KEY (`aHorariosAulas_IDHORARIOSAULAS`) REFERENCES `ahorariosaulas` (`IDHORARIOSAULAS`),
  ADD CONSTRAINT `FK_AHORARIOSAULAS_GTURNOS_turno_IDTURNO` FOREIGN KEY (`turno_IDTURNO`) REFERENCES `gturnos` (`IDTURNO`);

--
-- Limitadores para a tabela `aplanodeaula_ahorariosaulas`
--
ALTER TABLE `aplanodeaula_ahorariosaulas`
  ADD CONSTRAINT `PLANODEAULAAHORARIOSAULASaPlanoDeAulaIDPLANODEAULA` FOREIGN KEY (`aPlanoDeAula_IDPLANODEAULA`) REFERENCES `aplanodeaula` (`IDPLANODEAULA`),
  ADD CONSTRAINT `PLNDEAULAAHORARIOSAULAShrrosAulasesIDHORARIOSAULAS` FOREIGN KEY (`horariosAulas_IDHORARIOSAULAS`) REFERENCES `ahorariosaulas` (`IDHORARIOSAULAS`);

--
-- Limitadores para a tabela `aplanodeaula_gturnos`
--
ALTER TABLE `aplanodeaula_gturnos`
  ADD CONSTRAINT `FK_APLANODEAULA_GTURNOS_aPlanoDeAula_IDPLANODEAULA` FOREIGN KEY (`aPlanoDeAula_IDPLANODEAULA`) REFERENCES `aplanodeaula` (`IDPLANODEAULA`),
  ADD CONSTRAINT `FK_APLANODEAULA_GTURNOS_turno_IDTURNO` FOREIGN KEY (`turno_IDTURNO`) REFERENCES `gturnos` (`IDTURNO`);

--
-- Limitadores para a tabela `aprofessor_gpessoa`
--
ALTER TABLE `aprofessor_gpessoa`
  ADD CONSTRAINT `FK_APROFESSOR_GPESSOA_aProfessor_IDPROFESSOR` FOREIGN KEY (`aProfessor_IDPROFESSOR`) REFERENCES `aprofessor` (`IDPROFESSOR`),
  ADD CONSTRAINT `FK_APROFESSOR_GPESSOA_pessoas_IDPESSOA` FOREIGN KEY (`pessoas_IDPESSOA`) REFERENCES `gpessoa` (`IDPESSOA`);

--
-- Limitadores para a tabela `aprojetocurso_amodalidadecurso`
--
ALTER TABLE `aprojetocurso_amodalidadecurso`
  ADD CONSTRAINT `PRJTOCURSOAMODALIDADECURSOPrjtocursoIDPROJETOCURSO` FOREIGN KEY (`aProjetocurso_IDPROJETOCURSO`) REFERENCES `aprojetocurso` (`IDPROJETOCURSO`),
  ADD CONSTRAINT `PRJTOCURSOAMODALIDADECURSOmdldadecursoIDMODALIDADE` FOREIGN KEY (`modalidadecurso_IDMODALIDADE`) REFERENCES `amodalidadecurso` (`IDMODALIDADE`);

--
-- Limitadores para a tabela `aprojetocurso_gfiliais`
--
ALTER TABLE `aprojetocurso_gfiliais`
  ADD CONSTRAINT `APROJETOCURSO_GFILIAISaProjetocurso_IDPROJETOCURSO` FOREIGN KEY (`aProjetocurso_IDPROJETOCURSO`) REFERENCES `aprojetocurso` (`IDPROJETOCURSO`),
  ADD CONSTRAINT `FK_APROJETOCURSO_GFILIAIS_filial_IDFILIAL` FOREIGN KEY (`filial_IDFILIAL`) REFERENCES `gfiliais` (`IDFILIAL`);

--
-- Limitadores para a tabela `aturmadisciplinas_adisciplinas`
--
ALTER TABLE `aturmadisciplinas_adisciplinas`
  ADD CONSTRAINT `TRMDSCPLNSADISCIPLINASTrmDscplnasIDTURMADISCIPLINA` FOREIGN KEY (`aTurmaDisciplinas_IDTURMADISCIPLINA`) REFERENCES `aturmadisciplinas` (`IDTURMADISCIPLINA`),
  ADD CONSTRAINT `TURMADISCIPLINASADISCIPLINASdisciplinaIDDISCIPLINA` FOREIGN KEY (`disciplina_IDDISCIPLINA`) REFERENCES `adisciplinas` (`IDDISCIPLINA`);

--
-- Limitadores para a tabela `aturmadisciplinas_aunidadehabiltacao`
--
ALTER TABLE `aturmadisciplinas_aunidadehabiltacao`
  ADD CONSTRAINT `TRMDSCPLNSNDADEHABILTACAOTrmDscplnsDTRMADISCIPLINA` FOREIGN KEY (`aTurmaDisciplinas_IDTURMADISCIPLINA`) REFERENCES `aturmadisciplinas` (`IDTURMADISCIPLINA`),
  ADD CONSTRAINT `TRMDSCPLNSNDADEHABILTACAOnddHbltcDNIDADEHABILTACAO` FOREIGN KEY (`unidadeHabiltacao_IDUNIDADEHABILTACAO`) REFERENCES `aunidadehabiltacao` (`IDUNIDADEHABILTACAO`);

--
-- Limitadores para a tabela `aturmadisciplinas_gturnos`
--
ALTER TABLE `aturmadisciplinas_gturnos`
  ADD CONSTRAINT `FK_ATURMADISCIPLINAS_GTURNOS_turno_IDTURNO` FOREIGN KEY (`turno_IDTURNO`) REFERENCES `gturnos` (`IDTURNO`),
  ADD CONSTRAINT `TRMDSCIPLINASGTURNOSTrmDsciplinasIDTURMADISCIPLINA` FOREIGN KEY (`aTurmaDisciplinas_IDTURMADISCIPLINA`) REFERENCES `aturmadisciplinas` (`IDTURMADISCIPLINA`);

--
-- Limitadores para a tabela `aunidadehabiltacao_acurso`
--
ALTER TABLE `aunidadehabiltacao_acurso`
  ADD CONSTRAINT `FK_AUNIDADEHABILTACAO_ACURSO_curso_IDCURSO` FOREIGN KEY (`curso_IDCURSO`) REFERENCES `acurso` (`IDCURSO`),
  ADD CONSTRAINT `NDDHBILTACAOACURSOnddHabiltacaoIDUNIDADEHABILTACAO` FOREIGN KEY (`aUnidadeHabiltacao_IDUNIDADEHABILTACAO`) REFERENCES `aunidadehabiltacao` (`IDUNIDADEHABILTACAO`);

--
-- Limitadores para a tabela `aunidadehabiltacao_aprojetocurso`
--
ALTER TABLE `aunidadehabiltacao_aprojetocurso`
  ADD CONSTRAINT `NDDHABILTACAOAPROJETOCURSOprjtcursosIDPROJETOCURSO` FOREIGN KEY (`projetocursos_IDPROJETOCURSO`) REFERENCES `aprojetocurso` (`IDPROJETOCURSO`),
  ADD CONSTRAINT `NDDHBLTCOAPROJETOCURSOnddHbltcoIDUNIDADEHABILTACAO` FOREIGN KEY (`aUnidadeHabiltacao_IDUNIDADEHABILTACAO`) REFERENCES `aunidadehabiltacao` (`IDUNIDADEHABILTACAO`);

--
-- Limitadores para a tabela `aunidadehabiltacao_gfiliais`
--
ALTER TABLE `aunidadehabiltacao_gfiliais`
  ADD CONSTRAINT `FK_AUNIDADEHABILTACAO_GFILIAIS_filial_IDFILIAL` FOREIGN KEY (`filial_IDFILIAL`) REFERENCES `gfiliais` (`IDFILIAL`),
  ADD CONSTRAINT `NDDHBLTACAOGFILIAISnddHbiltacaoIDUNIDADEHABILTACAO` FOREIGN KEY (`aUnidadeHabiltacao_IDUNIDADEHABILTACAO`) REFERENCES `aunidadehabiltacao` (`IDUNIDADEHABILTACAO`);

--
-- Limitadores para a tabela `aunidadehabiltacao_gmatriz`
--
ALTER TABLE `aunidadehabiltacao_gmatriz`
  ADD CONSTRAINT `FK_AUNIDADEHABILTACAO_GMATRIZ_matriz_IDMATRIZ` FOREIGN KEY (`matriz_IDMATRIZ`) REFERENCES `gmatriz` (`IDMATRIZ`),
  ADD CONSTRAINT `NDDHBILTACAOGMATRIZnddHbiltacaoIDUNIDADEHABILTACAO` FOREIGN KEY (`aUnidadeHabiltacao_IDUNIDADEHABILTACAO`) REFERENCES `aunidadehabiltacao` (`IDUNIDADEHABILTACAO`);

--
-- Limitadores para a tabela `gfiliais_gmatriz`
--
ALTER TABLE `gfiliais_gmatriz`
  ADD CONSTRAINT `FK_GFILIAIS_GMATRIZ_gFiliais_IDFILIAL` FOREIGN KEY (`gFiliais_IDFILIAL`) REFERENCES `gfiliais` (`IDFILIAL`),
  ADD CONSTRAINT `FK_GFILIAIS_GMATRIZ_matrizs_IDMATRIZ` FOREIGN KEY (`matrizs_IDMATRIZ`) REFERENCES `gmatriz` (`IDMATRIZ`);

--
-- Limitadores para a tabela `gfiliais_gutilitarios`
--
ALTER TABLE `gfiliais_gutilitarios`
  ADD CONSTRAINT `FK_GFILIAIS_GUTILITARIOS_gFiliais_IDFILIAL` FOREIGN KEY (`gFiliais_IDFILIAL`) REFERENCES `gfiliais` (`IDFILIAL`),
  ADD CONSTRAINT `FK_GFILIAIS_GUTILITARIOS_utilitarios_IDUTILITARIO` FOREIGN KEY (`utilitarios_IDUTILITARIO`) REFERENCES `gutilitarios` (`IDUTILITARIO`);

--
-- Limitadores para a tabela `gmenus_gtipomenu`
--
ALTER TABLE `gmenus_gtipomenu`
  ADD CONSTRAINT `FK_GMENUS_GTIPOMENU_gMenus_IDMENU` FOREIGN KEY (`gMenus_IDMENU`) REFERENCES `gmenus` (`IDMENU`),
  ADD CONSTRAINT `FK_GMENUS_GTIPOMENU_tipomenu_IDTIPOMENU` FOREIGN KEY (`tipomenu_IDTIPOMENU`) REFERENCES `gtipomenu` (`IDTIPOMENU`);

--
-- Limitadores para a tabela `gpessoa_gutilitarios`
--
ALTER TABLE `gpessoa_gutilitarios`
  ADD CONSTRAINT `FK_GPESSOA_GUTILITARIOS_gPessoa_IDPESSOA` FOREIGN KEY (`gPessoa_IDPESSOA`) REFERENCES `gpessoa` (`IDPESSOA`),
  ADD CONSTRAINT `FK_GPESSOA_GUTILITARIOS_profissao_IDUTILITARIO` FOREIGN KEY (`profissao_IDUTILITARIO`) REFERENCES `gutilitarios` (`IDUTILITARIO`),
  ADD CONSTRAINT `FK_GPESSOA_GUTILITARIOS_tipoPessoa_IDUTILITARIO` FOREIGN KEY (`tipoPessoa_IDUTILITARIO`) REFERENCES `gutilitarios` (`IDUTILITARIO`);

--
-- Limitadores para a tabela `gpredio_gfiliais`
--
ALTER TABLE `gpredio_gfiliais`
  ADD CONSTRAINT `FK_GPREDIO_GFILIAIS_filial_IDFILIAL` FOREIGN KEY (`filial_IDFILIAL`) REFERENCES `gfiliais` (`IDFILIAL`),
  ADD CONSTRAINT `FK_GPREDIO_GFILIAIS_gPredio_IDPREDIO` FOREIGN KEY (`gPredio_IDPREDIO`) REFERENCES `gpredio` (`IDPREDIO`);

--
-- Limitadores para a tabela `gsalas_gpredio`
--
ALTER TABLE `gsalas_gpredio`
  ADD CONSTRAINT `FK_GSALAS_GPREDIO_gSalas_IDSALA` FOREIGN KEY (`gSalas_IDSALA`) REFERENCES `gsalas` (`IDSALA`),
  ADD CONSTRAINT `FK_GSALAS_GPREDIO_predios_IDPREDIO` FOREIGN KEY (`predios_IDPREDIO`) REFERENCES `gpredio` (`IDPREDIO`);

--
-- Limitadores para a tabela `gsalas_gutilitarios`
--
ALTER TABLE `gsalas_gutilitarios`
  ADD CONSTRAINT `FK_GSALAS_GUTILITARIOS_gSalas_IDSALA` FOREIGN KEY (`gSalas_IDSALA`) REFERENCES `gsalas` (`IDSALA`),
  ADD CONSTRAINT `FK_GSALAS_GUTILITARIOS_utilitarios_IDUTILITARIO` FOREIGN KEY (`tipoSala_IDUTILITARIO`) REFERENCES `gutilitarios` (`IDUTILITARIO`);

--
-- Limitadores para a tabela `gutilitarios_gaplicacao`
--
ALTER TABLE `gutilitarios_gaplicacao`
  ADD CONSTRAINT `FK_GUTILITARIOS_GAPLICACAO_aplicacao_IDAPLICACAO` FOREIGN KEY (`aplicacao_IDAPLICACAO`) REFERENCES `gaplicacao` (`IDAPLICACAO`),
  ADD CONSTRAINT `GUTILITARIOS_GAPLICACAO_gUtilitarios_IDUTILITARIO` FOREIGN KEY (`gUtilitarios_IDUTILITARIO`) REFERENCES `gutilitarios` (`IDUTILITARIO`);

--
-- Limitadores para a tabela `gutilitarios_gtipoutilitarios`
--
ALTER TABLE `gutilitarios_gtipoutilitarios`
  ADD CONSTRAINT `GTILITARIOSGTIPOUTILITARIOSgtilitariosIDUTILITARIO` FOREIGN KEY (`gUtilitarios_IDUTILITARIO`) REFERENCES `gutilitarios` (`IDUTILITARIO`),
  ADD CONSTRAINT `GTLTRIOSGTIPOUTILITARIOStptlitarioIDTIPOUTILITARIO` FOREIGN KEY (`tipoutilitario_IDTIPOUTILITARIO`) REFERENCES `gtipoutilitarios` (`IDTIPOUTILITARIO`);

--
-- Limitadores para a tabela `yperfil_gfiliais`
--
ALTER TABLE `yperfil_gfiliais`
  ADD CONSTRAINT `FK_YPERFIL_GFILIAIS_filial_IDFILIAL` FOREIGN KEY (`filial_IDFILIAL`) REFERENCES `gfiliais` (`IDFILIAL`),
  ADD CONSTRAINT `FK_YPERFIL_GFILIAIS_yPerfil_IDPERFIL` FOREIGN KEY (`yPerfil_IDPERFIL`) REFERENCES `yperfil` (`IDPERFIL`);

--
-- Limitadores para a tabela `yperfil_gutilitarios`
--
ALTER TABLE `yperfil_gutilitarios`
  ADD CONSTRAINT `FK_YPERFIL_GUTILITARIOS_tipoPerfil_IDUTILITARIO` FOREIGN KEY (`tipoPerfil_IDUTILITARIO`) REFERENCES `gutilitarios` (`IDUTILITARIO`),
  ADD CONSTRAINT `FK_YPERFIL_GUTILITARIOS_yPerfil_IDPERFIL` FOREIGN KEY (`yPerfil_IDPERFIL`) REFERENCES `yperfil` (`IDPERFIL`);

--
-- Limitadores para a tabela `yusuarios_gpessoa`
--
ALTER TABLE `yusuarios_gpessoa`
  ADD CONSTRAINT `FK_YUSUARIOS_GPESSOA_pessoa_IDPESSOA` FOREIGN KEY (`pessoa_IDPESSOA`) REFERENCES `gpessoa` (`IDPESSOA`),
  ADD CONSTRAINT `FK_YUSUARIOS_GPESSOA_yUsuarios_ID` FOREIGN KEY (`yUsuarios_ID`) REFERENCES `yusuarios` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
