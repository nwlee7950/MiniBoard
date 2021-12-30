-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema miniboard
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema miniboard
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `miniboard` DEFAULT CHARACTER SET utf8 ;
USE `miniboard` ;

-- -----------------------------------------------------
-- Table `miniboard`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `miniboard`.`board` (
                                                   `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                   `user_id` VARCHAR(20) NOT NULL,
                                                   `title` VARCHAR(50) NOT NULL,
                                                   `content` VARCHAR(50) NOT NULL,
                                                   `hits` BIGINT NULL DEFAULT '0',
                                                   `date` TIMESTAMP NULL DEFAULT NULL,
                                                   `is_notice` TINYINT NULL DEFAULT NULL,
                                                   PRIMARY KEY (`id`),
                                                   UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `miniboard`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `miniboard`.`comment` (
                                                     `id` INT NOT NULL AUTO_INCREMENT,
                                                     `board_id` BIGINT NOT NULL,
                                                     `content` VARCHAR(50) NULL DEFAULT NULL,
                                                     `date` TIMESTAMP NULL DEFAULT NULL,
                                                     `user_id` VARCHAR(20) NULL DEFAULT NULL,
                                                     `parent_id` INTEGER NULL DEFAULT NULL,
                                                     `comment_group` INT NULL DEFAULT NULL,
                                                     `sequence` INT NULL DEFAULT NULL,
                                                     PRIMARY KEY (`id`),
                                                     UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `miniboard`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `miniboard`.`user` (
                                                  `id` VARCHAR(20) NOT NULL,
                                                  `password` VARCHAR(100) NOT NULL,
                                                  `role` VARCHAR(15) NULL DEFAULT NULL,
                                                  `name` VARCHAR(15) NOT NULL,
                                                  `profile_image` VARCHAR(100) NULL DEFAULT NULL,
                                                  PRIMARY KEY (`id`),
                                                  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
