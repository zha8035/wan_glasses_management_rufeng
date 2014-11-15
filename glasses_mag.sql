-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
SHOW WARNINGS;
-- -----------------------------------------------------
-- Schema glasses_mag
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `glasses_mag` ;

-- -----------------------------------------------------
-- Schema glasses_mag
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `glasses_mag` ;
SHOW WARNINGS;
USE `glasses_mag` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `user` (
  `Username` VARCHAR(20) NOT NULL,
  `Password` VARCHAR(20) NOT NULL,
  `Role` INT(1) NOT NULL,
  PRIMARY KEY (`Username`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `Username` ON `user` (`Username` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `userInfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `userInfo` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `userInfo` (
  `Username` VARCHAR(20) NOT NULL,
  `ID_card` VARCHAR(20) NOT NULL,
  `Realname` VARCHAR(20) NULL,
  `userInfocol` VARCHAR(45) NULL,
  `sex` VARCHAR(2) NULL,
  `Reg_time` DATE NULL,
  `Phone` VARCHAR(12) NULL,
  `Address` VARCHAR(100) NULL,
  `Email` VARCHAR(30) NULL,
  `Birth_year` VARCHAR(4) NULL,
  `Birth_month` VARCHAR(2) NULL,
  PRIMARY KEY (`Username`),
  CONSTRAINT `userInfo_ibfk_1`
    FOREIGN KEY (`Username`)
    REFERENCES `user` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `ID_card` ON `userInfo` (`ID_card` ASC);

SHOW WARNINGS;
CREATE INDEX `Username` ON `userInfo` (`Username` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `glassesParameter`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `glassesParameter` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `glassesParameter` (
  `Username` VARCHAR(20) NOT NULL,
  `ID_card` VARCHAR(100) NOT NULL,
  `Course_of_treatment` INT(1) NULL,
  `Reference_code` INT(1) NULL,
  `Luminosity_and_which_eye` INT(1) NULL,
  `Naked_eyes_vision` VARCHAR(10) NULL,
  `Spherical_mirror` VARCHAR(20) NULL,
  `Cylinder_mirror` VARCHAR(20) NULL,
  `axial` VARCHAR(20) NULL,
  `Triple_prism` VARCHAR(20) NULL,
  `Bottom_side` VARCHAR(20) NULL,
  `Triple_prism1` VARCHAR(20) NULL,
  `Bottom_side1` VARCHAR(20) NULL,
  `CVA` VARCHAR(20) NULL,
  `PD` VARCHAR(20) NULL,
  `Remark` VARCHAR(20) NULL,
  `Priscribe_time` VARCHAR(10) NULL,
  PRIMARY KEY (`Username`),
  CONSTRAINT `glassesParameter_ibfk_1`
    FOREIGN KEY (`ID_card`)
    REFERENCES `userInfo` (`ID_card`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `glassesParameter_ibfk_2`
    FOREIGN KEY (`Username`)
    REFERENCES `user` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `ID_card` ON `glassesParameter` (`ID_card` ASC);

SHOW WARNINGS;
CREATE INDEX `Username` ON `glassesParameter` (`Username` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `imageStorage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `imageStorage` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `imageStorage` (
  `Username` VARCHAR(20) NOT NULL,
  `ID_card` VARCHAR(10) NOT NULL,
  `Scanal_image_Name` VARCHAR(100) NULL,
  `Scanal_time` VARCHAR(20) NULL,
  PRIMARY KEY (`Username`),
  CONSTRAINT `imageStorage_ibfk_1`
    FOREIGN KEY (`ID_card`)
    REFERENCES `userInfo` (`ID_card`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `imageStorage_ibfk_2`
    FOREIGN KEY (`Username`)
    REFERENCES `user` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `Username` ON `imageStorage` (`Username` ASC);

SHOW WARNINGS;
CREATE INDEX `ID_card` ON `imageStorage` (`ID_card` ASC);

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
