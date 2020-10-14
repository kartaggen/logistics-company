-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema logistics_company
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema logistics_company
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `logistics_company` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `logistics_company` ;

-- -----------------------------------------------------
-- Table `logistics_company`.`office`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `logistics_company`.`office` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(100) NOT NULL,
  `name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = MyISAM
AUTO_INCREMENT = 33
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `logistics_company`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `logistics_company`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(30) NOT NULL,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `register_date` DATETIME NOT NULL,
  `role` VARCHAR(20) NOT NULL,
  `username` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = MyISAM
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `logistics_company`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `logistics_company`.`employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `office_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_employee_office_idx` (`office_id` ASC) VISIBLE,
  INDEX `fk_employee_user1_idx` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = MyISAM
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `logistics_company`.`packet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `logistics_company`.`packet` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(100) NOT NULL,
  `delivery_price` DOUBLE NOT NULL,
  `status` INT NOT NULL,
  `status_date` DATETIME NOT NULL,
  `weight` DOUBLE NOT NULL,
  `employee_user_id` INT NOT NULL,
  `recipient_id` INT NOT NULL,
  `sender_id` INT NOT NULL,
  `is_office` BIT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_packet_user1_idx` (`employee_user_id` ASC) VISIBLE,
  INDEX `fk_packet_user2_idx` (`recipient_id` ASC) VISIBLE,
  INDEX `fk_packet_user3_idx` (`sender_id` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = MyISAM
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
