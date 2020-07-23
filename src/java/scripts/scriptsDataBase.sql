/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Camilla Bim <camillabim@hotmail.com.br>
 * Created: 21/07/2020
 */

-- MySQL Workbench Synchronization
-- Generated: 2020-07-21 22:44
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: camil
-- MySQL Workbench Synchronization
-- Generated: 2020-07-21 23:11
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: camil

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `estaciov5` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `estaciov5`.`pessoa_juridica` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `nome_fantasia` VARCHAR(100) NOT NULL,
  `razao_social` VARCHAR(100) NOT NULL,
  `cnpj` VARCHAR(14) NOT NULL,
  `ie` VARCHAR(45) NULL DEFAULT NULL,
  `im` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `estaciov5`.`endereco` (
  `cep` INT(11) NOT NULL,
  `logradouro` VARCHAR(45) NOT NULL,
  `numero` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `tipo_endereco` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`cep`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `estaciov5`.`pj_end` (
  `endereco_cep` INT(11) NOT NULL,
  `pessoa_juridica_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`endereco_cep`, `pessoa_juridica_codigo`),
  INDEX `fk_endereco_has_pessoa_juridica_pessoa_juridica1_idx` (`pessoa_juridica_codigo` ASC) VISIBLE,
  INDEX `fk_endereco_has_pessoa_juridica_endereco_idx` (`endereco_cep` ASC) VISIBLE,
  CONSTRAINT `fk_endereco_has_pessoa_juridica_endereco`
    FOREIGN KEY (`endereco_cep`)
    REFERENCES `estaciov5`.`endereco` (`cep`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_endereco_has_pessoa_juridica_pessoa_juridica1`
    FOREIGN KEY (`pessoa_juridica_codigo`)
    REFERENCES `estaciov5`.`pessoa_juridica` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `estaciov5`.`pessoa_fisica` (
  `codigo` VARCHAR(45) NOT NULL,
  `nomeCompleto` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `rg` VARCHAR(10) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `salario` FLOAT(11) NULL DEFAULT NULL,
  `funcao` VARCHAR(45) NULL DEFAULT NULL,
  `departamento` VARCHAR(45) NULL DEFAULT NULL,
  `telefone1` VARCHAR(11) NULL DEFAULT NULL,
  `telefone2` VARCHAR(11) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `index2` (`codigo` ASC, `cpf` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `estaciov5`.`pf_end` (
  `pessoa_fisica_codigo` VARCHAR(45) NOT NULL,
  `endereco_cep` INT(11) NOT NULL,
  PRIMARY KEY (`pessoa_fisica_codigo`, `endereco_cep`),
  INDEX `fk_pessoa_fisica_has_endereco_endereco1_idx` (`endereco_cep` ASC) VISIBLE,
  INDEX `fk_pessoa_fisica_has_endereco_pessoa_fisica1_idx` (`pessoa_fisica_codigo` ASC) VISIBLE,
  CONSTRAINT `fk_pessoa_fisica_has_endereco_pessoa_fisica1`
    FOREIGN KEY (`pessoa_fisica_codigo`)
    REFERENCES `estaciov5`.`pessoa_fisica` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pessoa_fisica_has_endereco_endereco1`
    FOREIGN KEY (`endereco_cep`)
    REFERENCES `estaciov5`.`endereco` (`cep`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
