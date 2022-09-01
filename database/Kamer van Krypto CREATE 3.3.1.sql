-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema KamerVanKrypto
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema KamerVanKrypto
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `KamerVanKrypto` DEFAULT CHARACTER SET utf8 ;
USE `KamerVanKrypto` ;

-- -----------------------------------------------------
-- Table `KamerVanKrypto`.`Trader`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KamerVanKrypto`.`Trader` (
  `idTrader` INT NOT NULL AUTO_INCREMENT,
  `Password` VARCHAR(256) NOT NULL,
  `Salt` VARCHAR(256) NULL,
  `FirstName` VARCHAR(45) NULL,
  `Prefix` VARCHAR(45) NULL,
  `Name` VARCHAR(45) NOT NULL,
  `BSN` INT NULL,
  `Birthdate` VARCHAR(45) NULL,
  `Adress` VARCHAR(45) NOT NULL,
  `Number` VARCHAR(5) NOT NULL,
  `PostalCode` VARCHAR(8) NOT NULL,
  `City` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Active` TINYINT NOT NULL,
  PRIMARY KEY (`idTrader`),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE,
  UNIQUE INDEX `idUser_UNIQUE` (`idTrader` ASC) VISIBLE,
  UNIQUE INDEX `Salt_UNIQUE` (`Salt` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KamerVanKrypto`.`Asset`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KamerVanKrypto`.`Asset` (
  `Ticker` VARCHAR(5) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Ticker`),
  UNIQUE INDEX `Abbreviation_UNIQUE` (`Ticker` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KamerVanKrypto`.`Wallet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KamerVanKrypto`.`Wallet` (
  `idTrader` INT NOT NULL,
  `Ticker` VARCHAR(5) NOT NULL,
  `Amount` DOUBLE NOT NULL,
  INDEX `Contains` (`Ticker` ASC) VISIBLE,
  PRIMARY KEY (`idTrader`, `Ticker`),
  CONSTRAINT `Owns_asset`
    FOREIGN KEY (`idTrader`)
    REFERENCES `KamerVanKrypto`.`Trader` (`idTrader`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `Contains`
    FOREIGN KEY (`Ticker`)
    REFERENCES `KamerVanKrypto`.`Asset` (`Ticker`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KamerVanKrypto`.`Rate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KamerVanKrypto`.`Rate` (
  `RateDateTime` VARCHAR(45) NOT NULL,
  `Ticker` VARCHAR(5) NOT NULL,
  `Rate` DOUBLE NOT NULL,
  UNIQUE INDEX `Rate_UNIQUE` (`Rate` ASC) VISIBLE,
  PRIMARY KEY (`RateDateTime`, `Ticker`),
  INDEX `Valued_at` (`Ticker` ASC) VISIBLE,
  CONSTRAINT `Valued_at`
    FOREIGN KEY (`Ticker`)
    REFERENCES `KamerVanKrypto`.`Asset` (`Ticker`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KamerVanKrypto`.`Transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KamerVanKrypto`.`Transaction` (
  `idTransaction` INT NOT NULL AUTO_INCREMENT,
  `Amount1` DOUBLE NOT NULL,
  `TransactionFee` DOUBLE NOT NULL,
  `TransactionDateTime` VARCHAR(45) NOT NULL,
  `idBuyer` INT NOT NULL,
  `idSeller` INT NOT NULL,
  `Ticker` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`idTransaction`),
  UNIQUE INDEX `idTransaction_UNIQUE` (`idTransaction` ASC) VISIBLE,
  INDEX `Buys` (`idBuyer` ASC) VISIBLE,
  INDEX `Sells` (`idSeller` ASC) VISIBLE,
  INDEX `Traded_in` (`Ticker` ASC) VISIBLE,
  CONSTRAINT `Buys`
    FOREIGN KEY (`idBuyer`)
    REFERENCES `KamerVanKrypto`.`Trader` (`idTrader`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `Sells`
    FOREIGN KEY (`idSeller`)
    REFERENCES `KamerVanKrypto`.`Trader` (`idTrader`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `Traded_in`
    FOREIGN KEY (`Ticker`)
    REFERENCES `KamerVanKrypto`.`Asset` (`Ticker`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KamerVanKrypto`.`BankFee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KamerVanKrypto`.`BankFee` (
  `FeePercentage` DOUBLE NOT NULL,
  PRIMARY KEY (`FeePercentage`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KamerVanKrypto`.`BankAccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KamerVanKrypto`.`BankAccount` (
  `idTrader` INT NOT NULL,
  `Saldo` DOUBLE NOT NULL,
  `SaldoDateTime` VARCHAR(45) NOT NULL,
  `IBAN` VARCHAR(18) NOT NULL,
  PRIMARY KEY (`idTrader`, `SaldoDateTime`),
  CONSTRAINT `Owns_Bank_Account`
    FOREIGN KEY (`idTrader`)
    REFERENCES `KamerVanKrypto`.`Trader` (`idTrader`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KamerVanKrypto`.`Token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KamerVanKrypto`.`Token` (
  `idToken` INT NOT NULL AUTO_INCREMENT,
  `idTrader` INT NOT NULL,
  `Token` VARCHAR(256) NOT NULL,
  `ValidUntil` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idToken`),
  UNIQUE INDEX `idToken_UNIQUE` (`idToken` ASC) VISIBLE,
  INDEX `Trader` (`idTrader` ASC) VISIBLE,
  UNIQUE INDEX `Token_UNIQUE` (`Token` ASC) VISIBLE,
  CONSTRAINT `Trader`
    FOREIGN KEY (`idTrader`)
    REFERENCES `KamerVanKrypto`.`Trader` (`idTrader`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

CREATE USER 'userKVK'@'localhost' IDENTIFIED BY 'pwKVK';
GRANT ALL PRIVILEGES ON KamerVanKrypto.* TO 'userKVK'@'localhost';
