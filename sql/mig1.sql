-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema blog_lite_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema blog_lite_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `blog_lite_db` DEFAULT CHARACTER SET utf8 ;
USE `blog_lite_db` ;

-- -----------------------------------------------------
-- Table `blog_lite_db`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `blog_lite_db`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `blog_lite_db`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `blog_lite_db`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) ,
  INDEX `FK_users_role_id` (`role_id` ASC) ,
  CONSTRAINT `FK_users_role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `blog_lite_db`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `blog_lite_db`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `blog_lite_db`.`post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NOT NULL,
  `picture_url` VARCHAR(255) NULL,
  `post_date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `idx_post_date` (`post_date` ASC),
  INDEX `FK_post_user_id_idx` (`user_id` ASC),
  CONSTRAINT `FK_post_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `blog_lite_db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `blog_lite_db`.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `blog_lite_db`.`tag` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `blog_lite_db`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `blog_lite_db`.`comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `post_id` INT NOT NULL,
  `content` TEXT NOT NULL,
  `user_id` INT NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `FK_comment_user_id_idx` (`user_id` ASC),
  INDEX `FK_comment_post_id_idx` (`post_id` ASC),
  INDEX `idx_comment_date` (`date` ASC),
  CONSTRAINT `FK_comment_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `blog_lite_db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_comment_post_id`
    FOREIGN KEY (`post_id`)
    REFERENCES `blog_lite_db`.`post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `blog_lite_db`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `blog_lite_db`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `price` DECIMAL(12,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `blog_lite_db`.`purchase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `blog_lite_db`.`purchase` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `buyer_user_id` INT NOT NULL,
  `purchase_date` DATETIME NOT NULL,
  `delivered` TINYINT NOT NULL,
  `comment` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `FK_purchase_user_id_idx` (`buyer_user_id` ASC),
  INDEX `FK_purchase_product_id_idx` (`product_id` ASC),
  INDEX `idx_date` (`purchase_date` ASC),
  CONSTRAINT `FK_purchase_user_id`
    FOREIGN KEY (`buyer_user_id`)
    REFERENCES `blog_lite_db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_purchase_product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `blog_lite_db`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `blog_lite_db`.`purchased_posts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `blog_lite_db`.`purchased_posts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `purchase_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `FK_purchased_posts_post_id_idx` (`post_id` ASC),
  INDEX `FK_purchased_posts_purchaset_id_idx` (`purchase_id` ASC),
  CONSTRAINT `FK_purchased_posts_post_id`
    FOREIGN KEY (`post_id`)
    REFERENCES `blog_lite_db`.`post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_purchased_posts_purchaset_id`
    FOREIGN KEY (`purchase_id`)
    REFERENCES `blog_lite_db`.`purchase` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `blog_lite_db`.`tags_for_posts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `blog_lite_db`.`tags_for_posts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tag_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `FK_tags_for_posts_tag_id_idx` (`tag_id` ASC),
  INDEX `FK_tags_for_posts_post_id_idx` (`post_id` ASC),
  CONSTRAINT `FK_tags_for_posts_tag_id`
    FOREIGN KEY (`tag_id`)
    REFERENCES `blog_lite_db`.`tag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_tags_for_posts_post_id`
    FOREIGN KEY (`post_id`)
    REFERENCES `blog_lite_db`.`post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `blog_lite_db`.`subscriptions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `blog_lite_db`.`subscriptions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `tag_id` INT NOT NULL,
  `start_date` DATETIME NOT NULL,
  `end_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `FK_subscriptions_user_id_idx` (`user_id` ASC),
  INDEX `idx_date` (`start_date` ASC),
  INDEX `FK_subscriptions_tag_id_idx` (`tag_id` ASC),
  INDEX `idx_end_date` (`end_date` ASC),
  CONSTRAINT `FK_subscriptions_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `blog_lite_db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_subscriptions_tag_id`
    FOREIGN KEY (`tag_id`)
    REFERENCES `blog_lite_db`.`tag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
